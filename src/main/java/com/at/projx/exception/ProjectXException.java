/**
* @author  Jaya Prakash Manne
* @version 1.0
* @since   01-Nov-2019 
*/
package com.at.projx.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.at.projx.dao.model.ExceptionDetails;
import com.at.projx.email.EmailUtil;
import com.at.projx.model.AuthorizationDetails;
import com.at.projx.repository.ExceptionRepository;

public class ProjectXException extends Exception {
	private static final long serialVersionUID = 2056817234546797042L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectXException.class);
	

	//=========================================================================
	
	public ProjectXException(ExceptionRepository exceptionRepository, String activity, String message, Throwable cause, AuthorizationDetails authorizationDetails) {
		super();
		processException(exceptionRepository, activity, message, cause, authorizationDetails); 
	} 
	
	//=========================================================================
	
	private void processException(ExceptionRepository exceptionRepository, String activity, String message, Throwable cause, AuthorizationDetails authorizationDetails) {
		String logTag = "processException() :";
		LOGGER.info(logTag + "START of the method");
		String exceptionType = null;
		String exceptionMessage = null;
		String exceptionCause = null;
		ExceptionDetails exceptionDetails = new ExceptionDetails();
		
		try {
			StringWriter exceptionCauseStringWriter = new StringWriter();
			cause.printStackTrace(new PrintWriter(exceptionCauseStringWriter));
			exceptionType = cause.getClass().getSimpleName();
			exceptionMessage = message != null ? message :"";
			exceptionCause  = exceptionCauseStringWriter.toString();
			
			if(exceptionType != null && exceptionType.length() > 254) {
	    		exceptionType = exceptionType.substring(0, 254);
	    	}
			exceptionDetails.setExceptionType(exceptionType != null ? exceptionType:"");
			exceptionDetails.setExceptionMessage(exceptionMessage != null ? exceptionMessage:"");
			exceptionDetails.setStackTrace(exceptionCause != null ? exceptionCause:"");
			
			/*
			 * if(exceptionMessage.contains(":")){ String[] splittedExceptionMessage =
			 * exceptionMessage.split(":"); if(splittedExceptionMessage != null &&
			 * splittedExceptionMessage.length >= 1 ){ activity =
			 * splittedExceptionMessage[0]; } }
			 */
			if(activity != null && activity.length() > 254) {
	    		activity = activity.substring(0, 254);
	    	}
			exceptionDetails.setActivity(activity != null ? activity:"");
			exceptionDetails.setCreatedAt(new Date()); 
			if(authorizationDetails != null) {
				exceptionDetails.setUserDetailsId(authorizationDetails.getUserDetailsId()); 
			}
			exceptionDetails = exceptionRepository.save(exceptionDetails);
			
			if(exceptionDetails != null && exceptionDetails.getExceptionDetailsId() != null) {
				EmailUtil.getInstance().notifyException(exceptionDetails.getExceptionMessage(), exceptionCause); 
			}
		} catch (Exception e) {
			LOGGER.error(logTag + " Problem saving the exception details. "+e);
		}
		LOGGER.info(logTag + "END of the method");
	}
	
	//=========================================================================
	
	public void notifyException() {
		
	}
	
	//=========================================================================
}
