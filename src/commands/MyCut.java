package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MyCut extends Thread {
	public MyCut(String s) {
		try {
			f(s);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No such file.");
		}
	}
	
	private void f(String s) throws FileNotFoundException {
		StringTokenizer x = new StringTokenizer(s);
		if(x.nextToken().equals("-c")) {
			char[] col = x.nextToken().toCharArray();
			String start = "";
			String stop = "";
			
			boolean minus = false;
			for(int i = 0;i< col.length;i++) {
				if(col[i] == '-') {
					minus = true;
					i++;
					for(;i<col.length;i++) {
						stop = stop + col[i];
					}
					break;
				}
				else {
					start = start + col[i];
				}
				
				
			}		
			if(minus) {
				String path = Mypwd.get_pwd() + "\\" +  x.nextToken();
				File f = new File(path);
				Scanner sc = new Scanner(f);
				while(sc.hasNextLine()) {
					int m = Integer.parseInt(start);
					int n = Integer.parseInt(stop);
					if( m > n) {
						System.out.println("cut: invalid decreasing range");
						break;
					}
					String output = "";
					
					char[] line = sc.nextLine().toCharArray();
					for(int i = 0;i<line.length;i++) {
						if(i + 1 == m) {
							output = output + line[i];
							m++;
							if(m > n) {
								break;
							}
						}
						
					}
					System.out.println(output);
				}
			}
			else {
				System.out.println("cut: invalid syntax");
			}
			
		}
		else {
			System.out.println("cut: Missing operand: '-c'");
		}
	}
	

}
