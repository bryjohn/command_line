package commands;
import java.io.*;
public class Myrmdir extends Thread {
	public Myrmdir(String s) {
		f(s);
	}
	private void f(String s) {
		if(s == "") {
			System.out.println("rmdir: Missing Operand ");
		}
		else {
			String path = Mypwd.get_pwd() + "\\" + s;
			File file = new File(path);
			if(file.exists()) {
				if(file.list().length > 0) {
					System.out.println("rmdir: failed to remove '" + s + "' : Directory not empty");
				}
				else {
					file.delete();
				}
			}
			else {
				System.out.println("rmdir: failed to remove '" + s + "' : No such file or directory" );
			}
		}		
	}

}
