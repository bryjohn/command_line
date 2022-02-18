package commands;
import java.util.*;

public class MyCalendar extends Thread {
	public void run() {
		f();
	}
	private int day(int month, int day, int year) {
        int y = year - (14 - month) / 12;
        int x = y + y/4 - y/100 + y/400;
        int m = month + 12 * ((14 - month) / 12) - 2;
        int d = (day + x + (31*m)/12) % 7;
        return d;
    }
	
    private void f() {
    	Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);    // Jan = 0 , Dec = 11

        // months[i] = name of month i
        String[] months = {
        	" ",
            "January", "February", "March",
            "April", "May", "June",
            "July", "August", "September",
            "October", "November", "December"
        };

        // days[i] = number of days in month i
        int[] days = {
           0,31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };


        // print calendar header
        System.out.println("   " + months[month] + " " + year);
        System.out.println(" S  M Tu  W Th  F  S");

        // starting day
        int d = day(month, 1, year);

        // print the calendar
        for (int i = 0; i < d; i++)
            System.out.print("   ");
        for (int i = 1; i <= days[month]; i++) {
            System.out.printf("%2d ", i);
            if (((i + d) % 7 == 0) || (i == days[month])) {
            	System.out.println();
            }
        }

    }
}



