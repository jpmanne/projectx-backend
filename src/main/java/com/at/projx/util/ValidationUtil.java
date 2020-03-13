/**
* @author  Jaya Prakash Manne
* @version 1.0
* @since   01-Nov-2019 
*/
package com.at.projx.util;

import com.at.projx.common.Constants;

public class ValidationUtil {
	
	private static ValidationUtil instance = null;

	// ========================================================================

	private ValidationUtil() { }

	// ========================================================================

	public static synchronized ValidationUtil getInstance() {
		if (instance == null) {
			instance = new ValidationUtil();
		}
		return instance;
	}

	// ========================================================================
	
	public String validateField(String fieldName, String fieldValue, boolean notNull, int maxLength) {
		
		if(notNull) {
			if(fieldValue != null && fieldValue.trim().length() > 0) {
				if(fieldValue.length() > maxLength) {
					return fieldName + " data exceeds max length ("+maxLength+")";
				}
			} else {
				return fieldName + " cannot be null";
			}
		} else {
			if(fieldValue != null && fieldValue.trim().length() > 0) {
				if(fieldValue.trim().length() > maxLength) {
					return fieldName + " data exceeds max length ("+maxLength+")";
				}
			}
		}
		return Constants.SUCCESS;
	}
	
	// ========================================================================
	
	
	
}