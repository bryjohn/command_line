package commands;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

class Main { 
    public static void main(String[] args) throws Exception {
      Scanner keyboard = new Scanner(System.in);
      boolean run = true;
      resethistory();
      intilizePath();
      String command = "";
      while(run) {
          try {
              while(true){
                
                if(command.equals("")) {
                	System.out.print("Prompt> ");
                	command = keyboard.nextLine();
                    command = command.trim();
                }
                
                StringTokenizer tok = new StringTokenizer(command);
                if (tok.hasMoreTokens()) {
                  Token token = new Token(tok.nextToken());
                  String s = "";
                  while (tok.hasMoreTokens())
                    s = s + " " + tok.nextToken();
                  s = s.trim();
                  addtohistory(command);
                  Thread t = null;
                  switch (token.kind) {
                  case Token.SEND:t = new MySend(s);command = "";break;   //>
                  case Token.CAL: t = new MyCalendar();command = ""; break;        
                  case Token.DATE: t = new MyDate();command = "";break;          
                  case Token.ATTRIB: t = new Myattrib(s);command = "";break;      
                  case Token.CP: t = new Mycp(s);command = "";break;          
                  case Token.CUT: t = new MyCut(s);command = "";break;        //cut                 
                  case Token.ECHO: t = new MyEcho(s);command = "";break; 
                  case Token.EXIT: t = new MyExit();command = "";break;           
                  case Token.FILE:t = new MyFile(s);command = ""; break;   
                  case Token.FIND: t = new MyFind(s);command = "";break;       //find
                  case Token.GREP:t = new Mygrep(s);command = "";break;     //grep
                  case Token.HEAD:t = new MyHead(s);command = "";break;     //head
                  case Token.LINK:t = new Mylink(s);command = "";break;       //link
                  case Token.DIR:t = new Directory();command = "";break;         //dir
                  case Token.MAN:t = new Man(s);command = ""; break;       //man
                  case Token.MKDIR:t = new Mkdir(s);command = "";break;     //mkdir
                  case Token.MV:t = new Mymv(s);command = "";break;        //mv
                  case Token.PWD:t = new Mypwd();command = "";break;              //pwd
                  case Token.RM:t = new MyRm(s);command = "";break;       	 //rm
                  case Token.RMDIR:t = new Myrmdir(s);command = "";break;   //rmdir
                  case Token.TAIL:t = new MyTail(s);command = "";break;     //tail
                  case Token.U: command = u();break;         //u
                  case Token.CAT:t = new MyCat(s);command = "";break;       //cat
                  case Token.WC:t = new Mywc(s);command = "";break;    //wc      
                  case Token.CD:t = new MyCD(s);command = "";break;         //cd
                  default:
                      System.out.println("Wrong command.");
                      t = null;
                      command = "";
                  }
                  if(command == "") {
                	  t.start();
                      try {t.join();}
                      catch(Exception e) {}
                  }
                }
              }
          
          }
          
          catch(Exception NullPointerException ) {
        	  command = "";
          } 
      }

   }
    //directoy.txt is used to store the current path
    public static void intilizePath() throws Exception {
		String user = System.getenv("USERNAME");
		Mypwd.PATH = "C:\\Users\\" + user;
    }
    //when the program is started the history of the previous run is cleared
    public static void resethistory() throws Exception{
    	FileOutputStream out = new FileOutputStream("history.txt");
    	String content = "";
		 byte[] strToBytes = content.getBytes();
		    out.write(strToBytes);
		 
		    out.close();
    }
    //history.txt is used to store command history
    public static void addtohistory(String s) throws Exception {
    	if(!s.equals("u")) {
    		String filename= "history.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            fw.write(s + "\n");//appends the string to the file
            fw.close();
    	}
    	
    	
    }
    //u command is used to navigate history
    //if user hits enter than selected history will run
    //user also has option to add to selected history
    //but cannot delete 
    public static String u() throws Exception{
    	boolean run = true;
		ArrayList<String> hist = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		Scanner fin = new Scanner(new File("history.txt"));
		
		while(fin.hasNext()) {
			hist.add(fin.nextLine());
		}
		if(hist.isEmpty()) {return "";}
		int i = hist.size() - 1;
		System.out.print("prompt> " + hist.get(i));
		while(run) {
			String input = sc.nextLine();
			switch(input) {
			case"u":
				if(i >= 0) {
					i--;
					System.out.print(hist.get(i));
					
				}
				break;
			case"d":
				if(i < hist.size() - 1) {
					i++;
					System.out.print(hist.get(i));
					
				}
				break;
			default:
				run = false;
				return hist.get(i) + input;
				
			}
		}
		return "";
		
	
		
		
	}
}
