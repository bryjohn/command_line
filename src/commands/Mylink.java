package commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.StringTokenizer;

public class Mylink extends Thread {
	public Mylink(String s) {
		f(s);
	}

	private void f(String s) {
		StringTokenizer st = new StringTokenizer(s);
		if(st.countTokens() == 2) {
			String og = st.nextToken();
			String link = st.nextToken();
			File f = new File(Mypwd.get_pwd() + "\\" + og);
			File c = new File(Mypwd.get_pwd() + "\\" + link);
			if(f.exists() && f.isFile() && !c.exists()) {
				try {
					Files.createLink(c.toPath(), f.toPath());
				} catch (IOException e) {
					
				}	
			}
			else if(c.exists()) {
				System.out.println("link: cannot create link '" + link + "' to '" + og + "': File exists");
			}
			else {
				System.out.println("link: cannot create link '" + link + "' to '" + og + "': No such file or directory");
			}
		}
		else if(st.countTokens() == 1) {
			System.out.println("link: missing destination operand after " + s);
		}
		else if(st.countTokens() > 2 ) {
			System.out.println("link: extra operand(s)");
		}
		else {
			System.out.println("link: Missing opperand");
		}
	}

}
