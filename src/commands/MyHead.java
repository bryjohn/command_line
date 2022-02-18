package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyHead extends Thread {
	public MyHead(String s) {
		f(s);
	}
	private void f(String x)  {	
		try {
			File f = new File(Mypwd.get_pwd() + "\\" + x);
			Scanner sc;
			
			sc = new Scanner(f);
			for(int i = 0; i < 10; i++) {
				System.out.println(sc.nextLine());
			}
			sc.close();

		} 
			
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}

			
			
		

				
	}
}
