package commands;

public class MyExit  extends Thread{
	  /* Command:
	           exit
	           Action:
	           terminate the program.
	  */

	  public void run() {
	    f();
	  }

	  private void f(){
	    System.exit(0);
	  }
	}
