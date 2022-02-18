package commands;
public class Token{
	  public int kind;

	  public Token(String spelling){
	    kind = -1;
	    for(int k = SEND; k <= WC; k++) {
	      if(spelling.equals(spellings[k])){
	        this.kind = k;
	         break;
	      }
	    }
	  }

	  public final static byte
	    SEND        = 0,        //>
	    CAL         = 1,        //cal
	    DATE        = 2,        //date
	    ATTRIB      = 3,        //attrib
	    CP          = 4,        //cp
	    CUT         = 5,        //cut
	    D           = 6,        //d
	    ECHO        = 7,        //echo
	    EXIT        = 8,        //exit
	    FILE        = 9,        //file
	    FIND        = 10,       //find
	    GREP        = 11,       //grep
	    HEAD        = 12,       //head
	    LINK        = 13,       //link
	    DIR         = 14,       //dir
	    MAN         = 15,       //man
	    MKDIR       = 16,       //mkdir
	    MV          = 17,       //mv
	    PWD         = 18,       //pwd
	    RM          = 19,       //rm
	    RMDIR       = 20,       //rmdir
	    TAIL        = 21,       //tail
	    U           = 22,       //u
	    CAT         = 23,       //cat
	    CD 			= 24,	    //cd
	    WC          = 25;       //wc
	    

	  private final static String[] spellings = {
	      "send",
	      "cal",
	      "date",
	      "attrib",
	      "cp",
	      "cut",
	      "d",
	      "echo",
	      "exit",
	      "file",
	      "find",
	      "grep",
	      "head",
	      "link",
	      "dir",
	      "man",
	      "mkdir",
	      "mv",
	      "pwd",
	      "rm",
	      "rmdir",
	      "tail",
	      "u",
	      "cat",
	      "cd",
	      "wc",
	      
	  };
	}

