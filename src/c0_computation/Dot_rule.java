package c0_computation;

public class Dot_rule{

	public char start;
	public StringBuffer rule;
	public int dot_index;
	
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

	public char get_char_next_dot() 
	{
		if(dot_index == rule.length())
			return '>';
		return this.rule.charAt(this.dot_index);
	}
	
	public void dot_move_right() 
	{
		if(dot_index != rule.length())
			dot_index++;
	}
	
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
	
	public void print_dot_rule() 
	{
		System.out.println(this.start + ">" + this.get_dot_string());
	}
	
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
