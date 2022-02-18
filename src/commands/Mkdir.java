package commands;
import java.io.File;
import java.io.IOException;
public class Mkdir extends Thread {
	public Mkdir(String s) {
		f(s);
	}


	private void f(String s) {
		if(s == "") {
			System.out.println("mkdir: Missing Operand ");
		}
		else {
			String path = Mypwd.get_pwd() + "\\" + s;
			File file = new File(path);
			if(!file.exists()) {
				file.mkdir();
			}
			else {
				System.out.println("mkdir: cannot create directory '" + s + "' : File exists" );
			}
		}		
	}

}
