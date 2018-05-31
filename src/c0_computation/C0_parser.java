/**
 * @Copyrighted Lenny_kong. (20133950 / °øÂùÇü)
 * Email : dadasa13@naver.com
 * Git_repository : github.com/syndra/Compiler_project_02
 * 
 * Please Check comment in each java file.
 */

package c0_computation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Parser unit. It should be instanciate with String filepath.
 * After instanciate, all features in this class also set.
 * To save into file, call member method write_to_file(String filename).
 */
public class C0_parser {
	
	/*
	 * Internal used properties.
	 * Please check comments upon each declaration.
	 */
	private List<Production_rule> rule_list;			// Contains production rules. <Start> : E, <rule> : E+T
	private int rule_cnt;								// Number of rules in file.
	private List<Dot_rule> all_possible_dot_list;		// Contains rules with dot.
	private List<Iterator_c0> c0_i_list;				// Contains Iterator_c0, list of rules with dot.
	
	/*
	 * Basic constructor.
	 * 
	 * Initiates all lists to be used, read files and comvert to Production_rule format.
	 * After convertion, compute all possible dot list of rules to compute closure conveniently.
	 * Finally, with that computation, compute C0 and fill them to c0_i_list.
	 */
	public C0_parser(String filePath) 
	{
		// Initiate all Lists.
		this.rule_list = new ArrayList<Production_rule>();
		this.all_possible_dot_list = new ArrayList<Dot_rule>();
		this.c0_i_list = new ArrayList<Iterator_c0>();
		
		file_reader(filePath);				// Process work with file and convert them to production rule for
		compute_all_possible_dot_list();	// Compute all possible dot list.
		c0_compute();						// Compute C0.
	}
	
	/*
	 * Simply, write C0 to file.
	 * 
	 * This should be called after successful instanciate.
	 * I thought it needs no comments anymore.
	 */
	public void	write_to_file(String filename) throws IOException 
	{
		File save_file = new File(filename);
		
		int cnt = 0;
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(save_file));
		
		//Write to file.
		for (Iterator_c0 iterator : this.c0_i_list) {
			bw.write("I" + Integer.toString(cnt));
			bw.newLine();
			cnt++;
			for(Dot_rule elem : iterator.dot_rule_list) 
			{
				bw.write("[" + elem.start + "->" + elem.get_dot_string() + "]");
			}
			bw.newLine();
		}
		bw.close();
	}
	
	/*
	 * File processing module.
	 * 
	 * Read grammar from file, and set a raw_format list.
	 * And convert them to production rule format.
	 * Finally, add augmented rule, S->E to list.
	 */
	private void file_reader(String filePath)
	{
		/*
		 * File processing.
		 */
		List<String> list = new ArrayList<String>();	// Internal list to contains String temporarilly..
		String tmp; // to read a line in txt file
		try {
			BufferedReader file = new BufferedReader(new FileReader(filePath));
			while ((tmp = file.readLine()) != null) {
				list.add(tmp);
			}
			file.close();
		} catch (IOException e) {
			System.err.println(e); // if it occur error, print.
			System.exit(1);
		}
		
		/*
		 * Converting.
		 */
		this.rule_cnt = list.size() / 2;										// Get real number of rules.
		
		for(int i = 0; i < this.rule_cnt; i++) 
		{
			rule_list.add(new Production_rule(list.get(i*2 + 1)));				// Make it production rule format, add it to list.
		}
		
		rule_list.add(0, new Production_rule("S>" + rule_list.get(0).start));	// Add Augmented rule. [S->E]
		this.rule_cnt++;
	}
	
	/*
	 * Compute all possible dot list.
	 * S->E => S->.E  ... 
	 * Compute all start symbols to easy-compute closure.
	 * Additionally, it automatically get non-terminals, which in left of production rule.
	 */
	private void compute_all_possible_dot_list() 
	{
		for(int i = 0; i < this.rule_cnt; i++) 
		{
			this.all_possible_dot_list.add(new Dot_rule(this.rule_list.get(i).start ,this.rule_list.get(i).rule.toString(), 0));
		}
	}
	
	/*
	 * Compute all C0.
	 * First, get i0, closure([S->.E]).
	 * During loop for Iterator_c0 list, construct new Iterator_c0 if list do not contains exactly same Iterator_c0.
	 * In loop iteration, compute goto(all non-terminal can be computed, dot_rule in list of current Iterator_c0)
	 * and check if is already constructed.
	 * Loop is finished when there is no construction whole iterations.
	 * 
	 * There is some console outputs to show computation process.
	 * If you want see how this goes, just unblock comments.
	 */
	private void c0_compute() {

		this.c0_i_list.add(new Iterator_c0(get_closure(this.all_possible_dot_list.get(0))));	// Get I0.

		/*
		 * Internal temporary list to computation.
		 */
		List<Character> char_list;
		Iterator_c0 temp;
		List<Dot_rule> cur_dot_list;

		for (int i = 0; i < this.c0_i_list.size(); i++) 
		{
			cur_dot_list = this.c0_i_list.get(i).dot_rule_list;									// Get current Iterator_c0's dot_rules.
			char_list = this.get_char_next_dot_list(this.c0_i_list.get(i).dot_rule_list);		// Get current Iterator_c0's computable symbols.
			
			//System.out.println("---Compute goto I" + i + "  with" + char_list);					// To show compuation process.
			
			this.print_string_in_dot_list(cur_dot_list);
		
			for (int j = 0; j < char_list.size(); j++) 											// Loop for current Iterator_c0's computable symbols.
			{
				temp = new Iterator_c0(this.get_goto(char_list.get(j), cur_dot_list));			// Make goto.
				
				if (!is_exist(temp))															// Check if goto already exist.
				{
					//System.out.println("GOTO (" + i + "," + this.get_char_next_dot_list(cur_dot_list).get(j) + ") = I " + this.c0_i_list.size());

					this.c0_i_list.add(temp);													// Add it to list.

					//this.print_string_in_dot_list(temp.dot_rule_list);
				}
			}
		}
		System.out.println("Iteration is done. There is no change.");
	}
	
	/*
	 * Compute closure of rule.
	 * E->.T*F => [E->.T*F], [T->...] ... 
	 * To find null string, I use character '>', which would not used in rules, because '>' is means rule operator.
	 * Each iteration, add dot_rules in all_possible_dot_list which start is character next dot.
	 * Loop goes until there is no change.
	 */
	private List<Dot_rule> get_closure(Dot_rule rule)
	{
		boolean changed = true;
		
		/*
		 * Internal list.
		 */
		List<Character> char_list = new ArrayList<Character>();
		List<Dot_rule> list = new ArrayList<Dot_rule>();
		
		list.add(rule);															// Add initial rule.
		if(rule.get_char_next_dot() == '>')										// Return if dot is in last of rule.
			return list;
		
		char_list.add(rule.get_char_next_dot());								// Add current rule's character next dot in character list.

		while(changed) {
			changed = false;
			for(Dot_rule elem : this.all_possible_dot_list) 
			{
				if(char_list.contains(elem.start)) 								// If computation of current char, do it.
				{
					if(!list.contains(elem)) 									// If it compute already, out.
					{
						if(!char_list.contains(elem.get_char_next_dot()))
							char_list.add(elem.get_char_next_dot());
						list.add(elem);
						changed = true;											// Compute once more.
					}
				}
			}
		}
		return list;
	}
	
	/*
	 * Compute goto of Iterator_c0 and symbol.
	 * In every dot_rule in list, check if current dot_rule's symbol next to compute.
	 * If it is same with input symbol, move dot right, and compute closure, add it to list.
	 */
	public List<Dot_rule> get_goto(char symbol, List<Dot_rule> rule_list)
	{
		/*
		 * Internal list.
		 */
		List<Dot_rule> list = new ArrayList<Dot_rule>();
		
		for(Dot_rule elem : rule_list) 
		{
			if(elem.get_char_next_dot() == symbol) 												// Check if can be computed.
			{
				Dot_rule temp = new Dot_rule(elem.start, elem.rule.toString(), elem.dot_index);
				temp.dot_move_right();
				list = union(list, get_closure(temp));											// Get closure dot rule moved, add it to list. Do not add if it already exist.
			}
		}
		return list;
	}
	
	/*
	 * Module for list union.
	 * To do not add it if could be overlaped.
	 */
	private List<Dot_rule> union(List<Dot_rule> target, List<Dot_rule> source)
	{
		for(Dot_rule elem : source) 
		{
			if(!target.contains(elem))
				target.add(elem);
		}
		return target;
	}
	
	/*
	 * Print method for show process.
	 * NO Description in report.
	 */
	public void print_string_in_dot_list(List<Dot_rule> list) 
	{
		for(Dot_rule elem : list) 
		{
			elem.print_dot_rule();
		}
	}
	
	/*
	 * Literally, get characters next in dot_rule list.
	 */
	public List<Character> get_char_next_dot_list(List<Dot_rule> rule_list) 
	{
		List<Character> char_list = new ArrayList<Character>();
		
		for(Dot_rule elem : rule_list) 
		{
			if(!char_list.contains(elem.get_char_next_dot())) 
			{
				if(elem.get_char_next_dot() != '>')
					char_list.add(elem.get_char_next_dot());
			}
		}
		return char_list;
	}
	
	/*
	 * Return true, if this Iterator_c0 list already contain operand Iterator_c0.
	 */
	public boolean is_exist(Iterator_c0 operand) 
	{
		for(Iterator_c0 elem : this.c0_i_list) 
		{
			if(elem.is_equal(operand))
				return true;
		}
		return false;	
	}
}
