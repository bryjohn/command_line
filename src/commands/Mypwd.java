package commands;
public class Mypwd extends Thread {
	public static String PATH = "";
	public void run() {
		System.out.println(get_pwd());
	
	}
	public static String get_pwd() {
		return PATH; 	 
	}
	public static void set_pwd(String p) {
		PATH = p;
	}

}


