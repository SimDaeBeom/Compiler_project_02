package c0_computation;

import java.util.List;

public class Iterator_c0 {

	public static int iterator_cnt = 0;
	public List<Dot_rule> dot_rule_list;
	
	public Iterator_c0(List<Dot_rule> dot_rule_list) 
	{
		this.dot_rule_list = dot_rule_list;
		Iterator_c0.iterator_cnt++;
	}
	
	public boolean is_same(List<Dot_rule> dot_rule) 
	{
		return this.dot_rule_list.containsAll(dot_rule);
	}
}
