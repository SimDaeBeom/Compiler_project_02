/**
 * @Copyrighted Lenny_kong. (20133950 / °øÂùÇü)
 * Email : dadasa13@naver.com
 * Git_repository : github.com/syndra/Compiler_project_02
 * 
 * Please Check comment in each java file.
 */
package c0_computation;

/*
 * Basic object for production rule with dot.
 */
public class Dot_rule{

	public char start;			// Start Non-terminal.
	public StringBuffer rule;	// Righthand rule.
	public int dot_index;		// Index of dot location.
	
	/*
	 * Constructor.
	 */
	public Dot_rule(char start, String rule, int dot_index) {
		this.start = start;
		this.rule = new StringBuffer();
		for(int i = 0; i < rule.length(); i++) 
		{
			char current_char = rule.charAt(i);
			this.rule.append(current_char);
		}
		this.dot_index = dot_index;
	}
	
	/*
	 * Get character after dot.
	 * E->.T => return T.
	 */
	public char get_char_next_dot() 
	{
		if(dot_index == rule.length())
			return '>';
		return this.rule.charAt(this.dot_index);
	}
	
	/*
	 * If, dot can be moved, move it right.
	 */
	public void dot_move_right() 
	{
		if(dot_index != rule.length())
			dot_index++;
	}
	
	/*
	 * Return rule String contains dot.
	 */
	public String get_dot_string() 
	{
		StringBuffer rule = new StringBuffer();
		for(int i = 0; i<this.rule.length() ;i++) 
		{
			if(i == this.dot_index)
				rule.append('.');
			rule.append(this.rule.charAt(i));
		}
		if(this.dot_index == this.rule.length())
			rule.append('.');
		return rule.toString();
	}
	
	/*
	 * Print method to show process.
	 */
	public void print_dot_rule() 
	{
		System.out.println(this.start + ">" + this.get_dot_string());
	}
	
	/*
	 * Return true, if this Dot_rule is exactly same with operand.
	 */
	public boolean is_equal(Dot_rule operand) 
	{
		int strComp = operand.rule.toString().compareTo(operand.rule.toString()); 
		if(this.dot_index == operand.dot_index && 
				this.start == operand.start &&
				strComp == 0
				) 			
			return true;
		return false;
	}	
}
