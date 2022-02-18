package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyTail extends Thread{
	public MyTail(String s) {
		f(s);
	}

	private void f(String x) {		
			try {
				File f = new File(Mypwd.get_pwd() + "\\" + x);
				Scanner sc = new Scanner(f);
				Scanner scan = new Scanner(f);
				int line_count = 0;
				int last;
				
				while(sc.hasNextLine()){
					sc.nextLine();
					line_count++;
				}
				sc.close();
				if(line_count > 10) {
					last = line_count - 10;
					line_count = 0;
					while(scan.hasNextLine()) {
						if(line_count == last) {
							System.out.println(scan.nextLine());
							line_count++;
							last++;
						}
						else {
							scan.nextLine();
							line_count++;
							
						}
					}
					scan.close();
				}
				else {
					while(scan.hasNextLine()){
					System.out.println(scan.nextLine());
					}
					scan.close();
				}
			} 
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("File not found.");


			}
	}

}
