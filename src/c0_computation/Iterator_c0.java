package c0_computation;

import java.util.List;

public class Iterator_c0 {

	public static int iterator_cnt = 0;
	
	public int id;
	
	public List<Dot_rule> dot_rule_list;
	
	public Iterator_c0(List<Dot_rule> dot_rule_list) 
	{
		this.dot_rule_list = dot_rule_list;
	}
	
	public Iterator_c0(List<Dot_rule> dot_rule_list, int id) 
	{
		this.dot_rule_list = dot_rule_list;
		this.id = id;
	}
	
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
