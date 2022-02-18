package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mygrep extends Thread {
	public Mygrep(String s) {
		f(s);// TODO Auto-generated constructor stub
	}
	
	private void f(String x) {
		String word = x.substring(x.indexOf("\""), x.lastIndexOf(" "));
		word = word.replace("\"", "");
		String file_name = x.substring(x.lastIndexOf(" "), x.length());
		file_name = file_name.trim();
		String line;
		File f = new File(Mypwd.get_pwd() + "\\" + file_name );
		if(f.exists()) {
			Scanner in;
			try {
				in = new Scanner(f);
				while(in.hasNextLine()) {
					line = in.nextLine();
					if(line.contains(word)) {System.out.println(line);}
				}
				in.close();
			} 
			catch (FileNotFoundException e) {}			
		}
		else {
			System.out.println("grep: " + file_name + ": No such file or directory");
		}
		
		
			
		
		
			
		

	} 

}


