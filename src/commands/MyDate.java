package commands;

import java.util.*;
import java.text.*;
import java.util.Date;

public class MyDate  extends Thread{
  public void run() {
    f();
  }

  private void f(){
    Date today;
    String dateOut;
    DateFormat dateFormatter;
    Locale currentLocale = new Locale("US"); //change to location if not in U.S. 

    dateFormatter = DateFormat.getDateInstance(DateFormat.LONG, currentLocale);
    today = new Date();
    dateOut = dateFormatter.format(today);
    String s = dateOut + " " + currentLocale.toString();
    System.out.println(s.subSequence(0, s.length()-3));
  }
}

