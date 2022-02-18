package commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.StringTokenizer;

public class Mycp extends Thread {
	public Mycp(String s) {
		f(s);
	}
	
	private void f(String s) {
		StringTokenizer st = new StringTokenizer(s);
		if(st.countTokens() == 2) {
			String og = st.nextToken();
			String copy = st.nextToken();
			File f = new File(Mypwd.get_pwd() + "\\" + og);
			File c = new File(Mypwd.get_pwd() + "\\" + copy);
			if(f.exists() && f.isFile()) {
				try {
					Files.copy(f.toPath(), c.toPath());
				} catch (IOException e) {
					
					
				}
				
			}
			else {
				System.out.println("cp: cannot stat '" + og + "': No such file");
			}
		}
		else if(st.countTokens() == 1) {
			System.out.println("cp: missing destination file operand after " + s);
		}

	}

}
