package webdriver;

import java.util.Date;

public class timestapm {

	public static void main(String[] args) {

	    	System.out.println(getTimeStam());
	    	sleepSecond(2);
	    	System.out.println(getTimeStam());
	    	
	    }

	public static String getTimeStam() {
		Date date = new Date();
		return date.toString();
		
	}
	
	public static void sleepSecond(long timeSleep) {
		try {
			Thread.sleep(timeSleep*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
