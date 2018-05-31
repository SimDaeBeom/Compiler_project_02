/**
 * @Copyrighted Lenny_kong. (20133950 / °øÂùÇü)
 * Email : dadasa13@naver.com
 * Git_repository : github.com/syndra/Compiler_project_02
 * 
 * Please Check comment in each java file.
 */
package c0_computation;

/*
 * Basic object for production rule.
 */
public class Production_rule {
	
	public char start;			// Start symbol.
	public StringBuffer rule;	// Lefthand rule.
	
	/*
	 * Constructor
	 * 
	 * Divide into start and rule with input String rule.
	 */
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
