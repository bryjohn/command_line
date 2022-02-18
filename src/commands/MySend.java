package commands;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.StringTokenizer;

public class MySend extends Thread{
	public MySend(String s) {
		try {f(s);} 
		catch (IOException e) {}
	}
	private void f(String s) throws IOException {
		// 'java class > file'
		StringTokenizer st = new StringTokenizer(s);
		if(st.nextToken().equals("java")) {
			String class_name = st.nextToken();
			String file_name = st.nextToken();
			File f = new File(Mypwd.get_pwd() + "\\" + file_name) ;
			File clas = new File(Mypwd.get_pwd() + "\\" + class_name);
			//replace 'C:\Users\brjoh\eclipse-workspace\Command_line\\' with local project file
			File eclipse = new File("C:\\Users\\brjoh\\eclipse-workspace\\Command_line\\" + class_name);
			if(f.exists() && class_name.contains(".class")) {
				Files.copy(clas.toPath(), eclipse.toPath());
				class_name = class_name.replace(".class", "");
				try {
					
					Process process = Runtime.getRuntime().exec("java " + class_name );
					InputStream inputStream = process.getInputStream();
					InputStreamReader isr = new InputStreamReader(inputStream);
					
					

					int n1;
					char[] c1 = new char[1024];
					StringBuffer standardOutput = new StringBuffer();
					while ((n1 = isr.read(c1)) > 0) {
						standardOutput.append(c1, 0, n1);
					}
					String output = standardOutput.toString();
					if(f.exists() && file_name.contains(".txt")) {
						FileOutputStream out = new FileOutputStream(f);
						 byte[] strToBytes = output.getBytes();
						    out.write(strToBytes);
						 
						    out.close();
					}
					else {
						System.out.println("Send: Error: '" + file_name + "' is not a Text file.");
					}
				} 
				catch (IOException e) {}
				Files.delete(eclipse.toPath());
			}
			else {
				System.out.println("Send: Error: '" + class_name + "' is not a java class.");
			}
		}
		else {
			System.out.println("Send: Error: missing operand: 'java'");
		}
		
	}
	
}
