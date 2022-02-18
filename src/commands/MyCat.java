package commands;
import java.io.*;
import java.util.Scanner;

public class MyCat extends Thread{
	public MyCat(String s) {
		f(s);
	}

	private void f(String x)  {
		try {
			File f = new File(Mypwd.get_pwd() + "\\" + x);
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
			sc.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}
}
