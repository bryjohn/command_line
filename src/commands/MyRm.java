package commands;

import java.io.File;

public class MyRm extends Thread {
	public MyRm(String s) {
		f(s);
	}

	private void f(String x) {
		if(x == "") {
			System.out.println("rm: missing operand");
		}
		else {
			File f = new File(Mypwd.get_pwd() + "\\" + x);
			if(f.exists()) {
				if(f.isFile()) {
					f.delete();
				}
				else {
					System.out.println("rm: cannot remove '" + x + "': Is a directory");
				}
			}
			else {
				System.out.println("rm: cannot remove '" + x + "': No such file or directory");
			}
		}
		
	}

}
