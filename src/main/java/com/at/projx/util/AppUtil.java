package com.at.projx.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.at.projx.dao.model.UserDetails;
import com.at.projx.model.OptionDetails;
import java.util.TimeZone;
public class AppUtil {

	//=========================================================================
	
	public static String getUserFullName(List<UserDetails> users, long userDetailsId) {
		for(UserDetails ud  : users) {
			if(userDetailsId == ud.getUserDetailsId()) {
				String fullName = ud.getFirstName();
				/*if(ud.getMiddleName() != null && ud.getMiddleName().trim().length() > 0) {
					fullName = fullName.concat(" ").concat(ud.getMiddleName());
				}*/
				fullName = fullName.concat(" ").concat(ud.getLastName());
				return fullName;
			}
		}
		return "";
	}
	
	//=========================================================================
	
	public static String getStartMethodMessage(String logTag) {
		return logTag + "START of the method";
	}
	
	//=========================================================================
	
	public static String getEndMethodMessage(String logTag) {
		return logTag + "END of the method";
	}
	
	//=========================================================================
	
	public static List<OptionDetails> getOptions(String[] stringArray) {
		List<OptionDetails> options = new ArrayList<OptionDetails>();
		int index = 1;
		if(stringArray != null) {
			for(String str : stringArray) {
				options.add(new OptionDetails(index++, str));
			}
		}
		return options;
	}
	
	//=========================================================================
	
	/*private static final String timeZone = "America/New_York";
	
	public static void main(String[] args) {
		System.out.println(">>>>>>>>>>>> In the main method >>>>>>>>>.");
		SimpleDateFormat defaultDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		defaultDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));

		java.util.Date now = new java.util.Date();
		String requestTime = defaultDateFormat.format(now);
		System.out.println(">>>>>>>>>>>>>requestTime1 : "+requestTime);
		
		//SimpleDateFormat defaultDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		SimpleDateFormat fileNameDateFormat = new SimpleDateFormat("yyyy.MM.dd");

		defaultDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
		fileNameDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
		
		System.out.println(">>>>>>>>>>>>>requestTime2 : "+requestTime);
		System.out.println(">>>>>>>>>>>>>requestTime2 : "+requestTime);

		//java.util.Date now = new java.util.Date();
		requestTime = defaultDateFormat.format(now);
		
		System.out.println(">>>>>>>>>>>>>requestTime2 : "+requestTime);
		System.out.println(">>>>>>>>>>>>>fileNameDateFormat : "+fileNameDateFormat.format(now));
		String[] availableIDs = TimeZone.getAvailableIDs();
		for (int i = 0; i < availableIDs.length; i++) {
			System.out.println(availableIDs[i]); 
		}
		
		
	}*/
}
