package commands;

import java.io.File;

public class MyFile extends Thread {
	public MyFile(String s) {
		f(s);
	}

	private void f(String x) {
		File f = new File(Mypwd.get_pwd() + "\\" + x);
		if(f.exists()) {
			if(f.isDirectory()) {
				System.out.println(f.getName() + ": Directory");
			}
			else {
				String extension = f.getName().substring(f.getName().lastIndexOf("."),f.getName().length());
				System.out.println(f.getName() + ": " + file_type(extension));
			}
		}
		else {
			System.out.println(x + ": cannot open '" + x + "' (No such file or directoy)");
		}
		
	}
	private String file_type(String ext) {
		String type;
		switch (ext) {
		case ".txt": type = "Text Document";break;
		case ".docx": type = "Microsoft Word Document"; break;
		case ".java": type = "Java Source File"; break;
		case ".class": type = "Java class File"; break;
		case ".cpp": type = "C++ Source File"; break;
		case ".c": type = "C/C++ Soures File"; break;
		case ".mp3": type = "MP3 Audio File"; break;
		case ".py": type = "Python Script"; break;
		case ".pdf": type = "Portable Document Format File"; break;
		case ".ppt": type = "PowerPoint Slide Show"; break;
		case ".jpg": type = "JPEG Image"; break;
		case ".png": type = "Portable Network Graphic"; break;
		case ".exe": type = "Excutable File"; break;
		
		default: type = ext;break;
		}
		return type;
			
	}

}
