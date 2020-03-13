package com.at.projx.util;

import java.util.Date;

public class DateTimeUtil {
	
	//=========================================================================
	
	public static String getHoursAndMinutes(int totalTimeInMinutes) {
		String hoursAndMinutes = "";
		if(totalTimeInMinutes > 0) {
			int hours = totalTimeInMinutes / 60; //since both are ints, you get an int
			int minutes = totalTimeInMinutes % 60;
			
			if(hours > 0) {
				hoursAndMinutes = hours + " hours";
			}
			if(minutes > 0) {
				hoursAndMinutes = hoursAndMinutes + minutes + " mins";
			}
		}
		return hoursAndMinutes;
	}
	
	//=========================================================================
	
	public static String getDateRange(Date sDate, Date eDate) {
		String dateRange = null;
		String[]  sDates = sDate.toString().split(" ");
		String[]  eDates = eDate.toString().split(" ");
		if(sDates != null && sDates.length > 1 && eDates != null && eDates.length > 1) {
			dateRange = sDates[2] + " "+ sDates[1] + " to " +eDates[2] + " "+ eDates[1];
		}
		return dateRange;
	}
	
	//=========================================================================
}
