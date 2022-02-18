package commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Mymv extends Thread{
	
	public Mymv(String s) {
		f(s);
	}
	private void f(String s){
		StringTokenizer st = new StringTokenizer(s);
		if(st.countTokens() == 2) {
			String og = st.nextToken();
			String dest = st.nextToken();
			File f = new File(Mypwd.get_pwd() + "\\" + og);
			File c = new File(Mypwd.get_pwd() + "\\" + dest);
			try {
				if(f.exists() && !c.exists() ) {
					Files.move(f.toPath(), c.toPath());	
				}
				else if(f.exists() && c.isFile()) {
					Path path = c.toPath();
					c.delete();
					Files.move(f.toPath(), path);
					
				}
				else if(f.exists() && c.isDirectory()) {
					File dir = new File(Mypwd.get_pwd() + "\\" + dest + "\\" + og);
					Files.move(f.toPath(), dir.toPath());
				}
				else {System.out.println("mv: cannot stat '" + og + "': No such file or directory");}
			}
			catch(IOException e) {}	
		}
		else if(st.countTokens() > 2) {
			List<String> files = new ArrayList<String>();
			List<String> dir = new ArrayList<String>();
			while(st.hasMoreTokens()) {
				String arg = st.nextToken();
				File f = new File(Mypwd.get_pwd() + "\\" + arg);
				if(f.isDirectory()) {
					dir.add(arg);
				}
				else {
					files.add(arg);
				}
			}
			System.out.println(dir.size());	
			
		}
		else if(st.countTokens() == 1) {System.out.println("mv: missing destination file opperand after: '" + s + "'");}
		
		else {System.out.println("mv: missing file opperand");}
	}
}
