/**
 * @Copyrighted Lenny_kong. (20133950 / °øÂùÇü)
 * Email : dadasa13@naver.com
 * Git_repository : github.com/syndra/Compiler_project_02
 * 
 * Please Check comment in each java file.
 */
package c0_computation;

import java.util.List;

/*
 * Basic object for Iterator of C0 computation.
 */
public class Iterator_c0 {

	public List<Dot_rule> dot_rule_list;	// Dot_rule list.
	
	/*
	 * Constructor.
	 */
	public Iterator_c0(List<Dot_rule> dot_rule_list) 
	{
		this.dot_rule_list = dot_rule_list;
	}
	
	/*
	 * Return true if this Iterator_c0's list have same list of operand.
	 */
	public boolean is_equal(Iterator_c0 operand) 
	{		
		if(operand.dot_rule_list.size() != this.dot_rule_list.size())
			return false;
		for(Dot_rule elem : operand.dot_rule_list) 
		{
			if(!this.is_contain(elem))
				return false;
		}
		return true;
	}
	
	/*
	 * Return true if this Iterator_c0's list already have operand rule.
	 */
	public boolean is_contain(Dot_rule rule) 
	{
		for(Dot_rule elem : this.dot_rule_list) 
		{
			if(elem.is_equal(rule)) 
				return true;
		}
		return false;
	}
}
