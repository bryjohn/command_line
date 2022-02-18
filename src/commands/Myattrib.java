package commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.DosFileAttributes;

public class Myattrib extends Thread {
	public Myattrib(String s) {
		f(s);
	}

	private void f(String s) {
		try {
			File dir = new File(Mypwd.get_pwd());  		
			File[] content = dir.listFiles(); 
			
			if(s == "") { //for display attributes to console
				for(File object : content) {
					String out = "  %s%n";
					DosFileAttributes f = Files.readAttributes(object.toPath(), DosFileAttributes.class);
					if (object.isFile()) {
						if(f.isHidden()) {out = " H" + out;}
						else {out = "  " + out;}
						
						if(f.isSystem()) {out = " S" + out;}
						else {out = "  " + out;}
						
						if(f.isArchive()) {out = " A" + out;}
						else {out = "  " + out;}
						
						if(f.isReadOnly()) {out = " R" + out;}
						else {out = "  " + out;}
						
						System.out.format("\t" + out , object.getName());
					}
				}
			}
			else if(!s.startsWith("-") && !s.startsWith("+")) {
				File file = new File(Mypwd.get_pwd() + "\\" + s);
				DosFileAttributes f = Files.readAttributes(file.toPath(), DosFileAttributes.class);
				String out = "  %s%n";
				if(file.isFile()) {
					if(f.isHidden()) {out = " H" + out;}
					else {out = "  " + out;}
					
					if(f.isSystem()) {out = " S" + out;}
					else {out = "  " + out;}
					
					if(f.isArchive()) {out = " A" + out;}
					else {out = "  " + out;}
					
					if(f.isReadOnly()) {out = " R" + out;}
					else {out = "  " + out;}
					
					System.out.format("\t" + out , file.getName());
				}
				else {System.out.println("attrib: " + file.getName() + ": No such file");}
			}
			else {// for modifying attributes
				char[] st = s.toCharArray();
				boolean correct = true;
				if(st.length == 2) { //modifying file attributes for entire dir
					if(st[0] == '-') {
						File[] content1 = dir.listFiles(); 
						for(File object1 : content1) {
							if(object1.isFile() && correct) {
								switch(st[1]) {
								case 'r': Files.setAttribute(object1.toPath(), "dos:readonly", false,LinkOption.NOFOLLOW_LINKS); break;
								case 'a': Files.setAttribute(object1.toPath(), "dos:archive", false,LinkOption.NOFOLLOW_LINKS); break;
								case 's': Files.setAttribute(object1.toPath(), "dos:system", false,LinkOption.NOFOLLOW_LINKS); break;
								case 'h': Files.setAttribute(object1.toPath(), "dos:hidden", false,LinkOption.NOFOLLOW_LINKS); break;
								default: 
									System.out.println("attrib: " + st[1] + ": Invalid Operand");
									correct = false;
								}
							}
						}
					}
					else if(st[0] == '+') {
						File[] content1 = dir.listFiles(); 
						for(File object1 : content1) {
							if(object1.isFile() && correct) {
								switch(st[1]) {
								case 'r': Files.setAttribute(object1.toPath(), "dos:readonly", true,LinkOption.NOFOLLOW_LINKS); break;
								case 'a': Files.setAttribute(object1.toPath(), "dos:archive", true,LinkOption.NOFOLLOW_LINKS); break;
								case 's': Files.setAttribute(object1.toPath(), "dos:system", true,LinkOption.NOFOLLOW_LINKS); break;
								case 'h': Files.setAttribute(object1.toPath(), "dos:hidden", true,LinkOption.NOFOLLOW_LINKS); break;
								default: 
									System.out.println("attrib: " + st[1] + ": Invalid Operand");
									correct = false;
								}
							}
						}
					}
					
					else {System.out.println("attrib: Invalid Switch");}
				}
				else {//modifying single file
					String path = Mypwd.get_pwd() + "\\" ;
					char[] cmd = s.toCharArray();
					for(int i = 3; i < cmd.length; i++) {
						path = path + cmd[i];
					}
					File file = new File(path);
					if(file.isFile()) {
						if(cmd[0] == '-') {
							switch(st[1]) {
							case 'r': Files.setAttribute(file.toPath(), "dos:readonly", false,LinkOption.NOFOLLOW_LINKS); break;
							case 'a': Files.setAttribute(file.toPath(), "dos:archive", false,LinkOption.NOFOLLOW_LINKS); break;
							case 's': Files.setAttribute(file.toPath(), "dos:system", false,LinkOption.NOFOLLOW_LINKS); break;
							case 'h': Files.setAttribute(file.toPath(), "dos:hidden", false,LinkOption.NOFOLLOW_LINKS); break;
							default: System.out.println("attrib: " + st[1] + ": Invalid Operand");
							}
						}
						else if(cmd[0] == '+') {
							switch(st[1]) {
							case 'r': Files.setAttribute(file.toPath(), "dos:readonly", true,LinkOption.NOFOLLOW_LINKS); break;
							case 'a': Files.setAttribute(file.toPath(), "dos:archive", true,LinkOption.NOFOLLOW_LINKS); break;
							case 's': Files.setAttribute(file.toPath(), "dos:system", true,LinkOption.NOFOLLOW_LINKS); break;
							case 'h': Files.setAttribute(file.toPath(), "dos:hidden", true,LinkOption.NOFOLLOW_LINKS); break;
							default: System.out.println("attrib: " + st[1] + ": Invalid Operand");
							}
						}
						else{System.out.println("attrib: Invalid Switch");}	
						
					}
					else {System.out.println("attrib: " + file.getName() + ": No such file");}
				}
			}
		}
		catch (IOException e) {}

	}
}
