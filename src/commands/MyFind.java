package commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MyFind extends Thread {
	/*Options 
	 * 		
	 * 		find and list files in specific dir : find 'dir'
	 * 		find empty dir or files: find -empty
	 * 		find file by name: find -name
	 * 		find file type: find -name *.extension
	 * 		find and exc:
	 * 			rm: find [file] -exec rm
	 * 
	 * 
	 */
	
	public MyFind(String s) {
		f(s);
	}
	public String parent = Mypwd.get_pwd();
	
	private void f(String x) {
		StringTokenizer s = new StringTokenizer(x);
		String file = null;
		String option = null;
		String search = "";
		boolean exec = false;
		String remove = null;
		List<String> r = new ArrayList<String>();
		
		if(s.hasMoreTokens()) {
			 file = s.nextToken();
		}
		if(s.hasMoreTokens()) {
			//-empty or -name
			 option = s.nextToken();
		}
		if(s.hasMoreTokens()) {
			//file,directory or extension
			while(s.hasMoreTokens()) {
				r.add(s.nextToken());
			}
		
			for(int i = 0;i < r.size();i++) {
				if(!r.get(i).equals("-exec")) {
					search = search + r.get(i) + " ";
				}
				else {
					exec = true;
					remove = r.get(i+1);
					break;
				}
			}
			search = search.trim();
		}

		//String file = x;
		if(file != null) {
			File f = new File(Mypwd.get_pwd() + "\\" + file);
			if(f.isDirectory()) {
				if(option == null) {
					System.out.println(f.getPath().replace(parent, ""));
					print(f);
			
				}
				else {
					switch(option) {
					case "-name":
						if(search != "" && !exec) {
							if(search.contains("*.")) {
								search = search.replace("*", "");
								find_ext(f,search);
							}
							else {find(f,search);}
						}
						else if(search != "" && exec) {
							if(remove.equals("rm")) {
								if(search.contains("*.")) {
									search = search.replace("*", "");
									Scanner in = new Scanner(System.in);
									System.out.println("find: confirm you want to delete all file(s) w/ ext: " + search + " : Y/N");
									String input = in.next();
									input = input.toLowerCase();
									if(input.equals("y")) {
										rm_ext(f,search);
									}
									
								}
								else {rm_name(f,search);}
							}
						}
						else {System.out.println("find: missing operand after '-name'");}
						break;
					case "-empty":
						isempty(f);
						break;
					default:
						System.out.println("find: option: '" + option + "': invalid option");
					}
				}
			}
			else {
				System.out.println("find: '" + file +"': No such directory");
			}
			
		}
		else {
			System.out.println("find: Invalid syntax");
		}
		
		
		
		
	}
	private void print(File f) {
		
		File[] content = f.listFiles(); 
		for(File object : content) {
			if (object.isDirectory() && !object.isHidden()) {
				File dir = new File(f.toPath() + "\\" + object.getName());
				System.out.println(object.getPath().replace(parent, ""));
				print(dir);
			}
			else if (object.isFile() && !object.isHidden()) {
				System.out.println(object.getPath().replace(parent, ""));
			}
		}
	}
	
	private void find(File f , String name) {
		File[] content = f.listFiles(); 
		for(File object : content) {
			if (object.isDirectory() && !object.isHidden()) {
				File dir = new File(f.toPath() + "\\" + object.getName());
				if(object.getName().equals(name)) {
					System.out.println(object.getPath().replace(parent, ""));
					break;
				}
				else {
					find(dir,name);
				}
				
			}
			else if (object.isFile() && !object.isHidden() ) {
				String n = object.getName();
				if(n.equals(name)) {
					System.out.println(object.getPath().replace(parent, ""));
					break;
				}
				
			}
		}
	}

	private void isempty(File f) {
		File[] content = f.listFiles(); 
		for(File object : content) {
			if (object.isDirectory() && !object.isHidden()) {
				File dir = new File(f.toPath() + "\\" + object.getName());
				if(dir.listFiles().length == 0) {
					System.out.println(object.getPath().replace(parent, ""));
				}
				else {
					isempty(dir);
				}
				
			}
			else if (object.isFile() && !object.isHidden()) {
				if(object.length() == 0) {
					System.out.println(object.getPath().replace(parent, ""));
				}
			}
		}
	}
	
	private void rm_name(File f,String name) {
		Scanner sc = new Scanner(System.in);
		File[] content = f.listFiles(); 
		for(File object : content) {
			if (object.isDirectory() && !object.isHidden()) {
				File dir = new File(f.toPath() + "\\" + object.getName());
				rm_name(dir,name);				
			}
			else if (object.isFile() && !object.isHidden() ) {
				
				if(object.getName().equals(name)) {
					
					System.out.println("find: confirm you want to delete file: Y/N");
					String input = sc.next();
					input = input.toLowerCase();
					if(input.equals("y")) {
						object.delete();
					}	
				}
				
			}
		}
	}

	private void rm_ext(File f,String ext) {
		Scanner sc = new Scanner(System.in);
		File[] content = f.listFiles(); 
		for(File object : content) {
			if (object.isDirectory() && !object.isHidden()) {
				File dir = new File(f.toPath() + "\\" + object.getName());
				rm_ext(dir,ext);				
			}
			else if (object.isFile() && !object.isHidden() ) {
				
				if(get_ext(object).equals(ext)) {object.delete();}
					
			}
		}
	}
	private void find_ext(File f,String ext) {
		File[] content = f.listFiles(); 
		for(File object : content) {
			if (object.isDirectory() && !object.isHidden()) {
				File dir = new File(f.toPath() + "\\" + object.getName());
				find_ext(dir,ext);				
			}
			else if (object.isFile() && !object.isHidden() ) {
				
				if(get_ext(object).equals(ext)) {
					System.out.println(object.getPath().replace(parent, ""));
				}
				
			}
		}
		
	}
	private String get_ext(File f) {

		if(f.exists()) {
			String extension = f.getName().substring(f.getName().lastIndexOf("."),f.getName().length());
			return extension;
		}
		return null;
	}
}

