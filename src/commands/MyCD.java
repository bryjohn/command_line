package commands;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
//allows for navigating through directories
public class MyCD extends Thread {
	public MyCD(String s) {
		f(s);
	}
	private void f(String s) {
		String path;
		try {
			File d = new File(s);			
			if(s == "") {
				path = Mypwd.get_pwd();
				char[] old_path = path.toCharArray(); 
				for(int i = path.length()- 1; i > 0; i--) {
					if(old_path[i] == '\\' ){ 
						path = "";
						for(int j = 0; j < i; j++) {
							path = path + old_path[j];
						}
						i = 0;
					}
				}
				Mypwd.set_pwd(path);
				//delete if works 
				FileOutputStream out = new FileOutputStream("Directory.txt");
				 byte[] strToBytes = path.getBytes();
				    out.write(strToBytes);
				 
				    out.close();
			}
			else if(d.exists()) {
				Mypwd.set_pwd(s);
				//delete if works 
				FileOutputStream out = new FileOutputStream("Directory.txt");
				 byte[] strToBytes = s.getBytes();
				    out.write(strToBytes);
				 
				    out.close();
			}
			else {
				path = Mypwd.get_pwd() + "\\" + s;
				File p = new File(path);
				if(p.isDirectory()) {
					Mypwd.set_pwd(path);
					//delete if works 
					FileOutputStream out = new FileOutputStream("Directory.txt");
					 byte[] strToBytes = path.getBytes();
					    out.write(strToBytes);
					 
					    out.close();
				}
				else if(p.isFile()) {
					System.out.println("bash: cd: "+ s + ": Not a directory");
				}
				else {
					System.out.println("bash: cd: '" + s + "': No such file or directory");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
