package com.at.projx.util;

import java.util.ArrayList;
import java.util.List;

import com.at.projx.dao.model.UserDetails;
import com.at.projx.model.OptionDetails;

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
}
