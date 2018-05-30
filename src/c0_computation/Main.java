/**
 * @Copyrighted Lenny_kong. (20133950 / °øÂùÇü)
 * Email : dadasa13@naver.com
 * Git_repository : github.com/syndra/Compiler_project_02
 * 
 * Please Check comment in each java file.
 */
package c0_computation;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException
	{
		C0_parser parser = new C0_parser("rule.txt");	// Make instance of parser with "rule.txt".
		parser.write_to_file("output.txt");				// Save same directory with filename "output.txt".
	}
}
