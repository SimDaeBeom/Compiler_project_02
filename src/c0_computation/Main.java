package c0_computation;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException
	{
		C0_parser parser = new C0_parser("rule.txt");
		parser.write_to_file();
	}
}
