package commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mywc extends Thread {
	public Mywc(String s) {
		try {
			f(s);
		} 
		catch (IOException e) {}
	}


	
	private void f(String s) throws IOException {
		char[] x = s.toCharArray();
		
		if(x[0] == '-') {
			String option = "-" + x[1];
			String file = "";
			for(int i = 3; i < x.length;i++) {
				file += x[i];
			}
			File f = new File(Mypwd.get_pwd() + "\\" + file);
			if(f.isFile()) {
				FileInputStream fileStream = new FileInputStream(f); 
		        InputStreamReader input = new InputStreamReader(fileStream); 
		        BufferedReader reader = new BufferedReader(input); 
		        String line;
		        
		        int lines = 0;
		        int chars = 0;
		        int words = 0;
		        
		      
				while((line = reader.readLine()) != null) {
					lines++;
					if(!(line.equals(""))){ 
						chars = chars + line.length();
						String[] wordList = line.split("\\s+");
						words = wordList.length;
					}
				}
				
				
				switch(option) {
				case "-l": //lines
					System.out.println(lines + " " + file);
					break;
				case "-m": //chars
					System.out.println(chars + " " + file);
					
					break;
				case "-w": //words
					System.out.println(words + " " + file);
					break;
				default: 
					System.out.println("wc: " + option + ": Invalid option");
				}
			}
			else {System.out.println("wc: "+ file + ": No such file" );}
		}
		else {
			File f = new File(Mypwd.get_pwd() + "\\" + s);
			if(f.isFile()) {
				FileInputStream fileStream = new FileInputStream(f); 
		        InputStreamReader input = new InputStreamReader(fileStream); 
		        BufferedReader reader = new BufferedReader(input); 
		        String line;
		        
		        int lines = 0;
		        int words = 0;
		        long bytes = fileStream.getChannel().size();
		        
		        while((line = reader.readLine()) != null) {
		        	lines++;
		        	if(!(line.equals(""))){ 
		        		String[] wordList = line.split("\\s+");
		        		words = wordList.length;
		        	}
		        }
		        System.out.println(lines + " " + words + " " + bytes + " " + s);
		    }
			else {System.out.println("wc: " + s + ": No such file" );}
		}
		
	}

}
