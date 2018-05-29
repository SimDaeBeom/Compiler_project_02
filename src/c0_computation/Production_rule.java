package c0_computation;

public class Production_rule {
	
	public char start;
	public StringBuffer rule;
	
	public Production_rule(String rule) 
	{
		this.rule = new StringBuffer();
		start =rule.charAt(0);
		for(int i = 1; i < rule.length(); i++) 
		{
			char current_char = rule.charAt(i);
			if(current_char == '>') 
			{
				continue;
			}
			else
			{
				this.rule.append(current_char);
			}
		}
	}
	
}
