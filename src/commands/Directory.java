package commands;
import java.io.*;

public class Directory extends Thread {
	public void run() {
		f();
	}
	private void f() {
		File dir = new File(Mypwd.get_pwd());   //user
		File[] content = dir.listFiles(); 
		for(File object : content) {
			if (object.isDirectory() && !object.isHidden()) {
				System.out.format("\t" + "<dir> %s%n" , object.getName());
			}
			else if (object.isFile() && !object.isHidden()) {
				System.out.format("\t" + "      %s%n" , object.getName());
			}
			
		}
		
		
	} 
}
