package commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Man  extends Thread {
	public Man(String s){
		try {f(s);} 
		catch (FileNotFoundException e) {} 
		catch (IOException e) {}
	}

	private void f(String s) throws IOException {
		s = s.toLowerCase();
		boolean correct = false;
		for(int i = 0; i < spellings.length;i++) {
			if(s.equals(spellings[i])) {
				String file = s + ".txt";
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				correct = true;
				break;
			}
		}
		if(!correct) {System.out.println("man: invalid command.");}
		
	}
	  private final static String[] spellings = {
			  "send",
		      "cal",
		      "date",
		      "attrib",
		      "cp",
		      "cut",
		      "d",
		      "echo",
		      "exit",
		      "file",
		      "find",
		      "grep",
		      "head",
		      "link",
		      "dir",
		      "mkdir",
		      "mv",
		      "pwd",
		      "rm",
		      "rmdir",
		      "tail",
		      "u",
		      "cat",
		      "cd",
		      "wc",
		      
		  };
}
