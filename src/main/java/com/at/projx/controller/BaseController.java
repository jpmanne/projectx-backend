/**
* @author  Jaya Prakash Manne
* @version 1.0
* @since   01-Nov-2019 
*/
package com.at.projx.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.at.projx.common.Constants;
import com.at.projx.dao.model.AuthCodeDetails;
import com.at.projx.dao.model.OrganizationDetails;
import com.at.projx.dao.model.UserDetails;
import com.at.projx.exception.ProjectXException;
import com.at.projx.model.AuthorizationDetails;
import com.at.projx.model.Response;
import com.at.projx.repository.AuthCodeRepository;
import com.at.projx.repository.ExceptionRepository;
import com.at.projx.repository.UserRepository;

@RestController
public abstract class BaseController {
	
	@Autowired
	AuthCodeRepository authCodeRepository;
	
	@Autowired
	ExceptionRepository exceptionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
	
	//=========================================================================
	
	public AuthorizationDetails validateAuthorization(String authCode) {
		String logTag = "validateAuthorization() : ";
		LOGGER.info(logTag + "START of the method");
		AuthorizationDetails authorizationDetails = new AuthorizationDetails();
		
		try {
			List<AuthCodeDetails> authCodes = authCodeRepository.getAuthCodeDetailsByAuthCode(authCode); 
			if(authCodes != null && !authCodes.isEmpty()) { 
				if(authCodes.size() > 1) {
					LOGGER.error(logTag + "Found multiple entries for the authCode, "+authCode); 
					
				} else {
					AuthCodeDetails authCodeDetails = authCodes.get(0);
					LOGGER.info(logTag + "AuthCode: " + authCodeDetails.getAuthCode());
					authorizationDetails.setAuthCode(authCode);
					authorizationDetails.setUserDetailsId(authCodeDetails.getUserDetails().getUserDetailsId()); 
					
					if(Constants.ACTIVE.equalsIgnoreCase(authCodeDetails.getStatus())) {
						authorizationDetails.setValidAuthCode(true);
						
						//TODO: Need to check whether the user is valid authorization to access this api
						authorizationDetails.setValidAccess(true); //This is temporary
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(logTag + "Exception while validating the authorization, "+authCode, e);  
		}
		
		return authorizationDetails;
	}
	
	//=========================================================================
	
	public ResponseEntity<Response> getInvalidDataResponseEntity(String validationResult) {
		return new ResponseEntity<Response>(new Response(validationResult, null), HttpStatus.BAD_REQUEST);
	}
	
	//=========================================================================
	
	public ResponseEntity<Response> getInvalidAuthCodeResponseEntity(String authCode) {
		return new ResponseEntity<Response>(new Response("Invalid AuthCode ["+authCode+"]", null), HttpStatus.OK);
	}
	
	//=========================================================================
	
	public ResponseEntity<Response> getUnauthorizedAccessResponseEntity() {
		return new ResponseEntity<Response>(new Response("Unauthorized Access", null), HttpStatus.UNAUTHORIZED);
	}
	
	//=========================================================================
	
	public ResponseEntity<Response> getOKResponseEntity(String message) {
		return new ResponseEntity<Response>(new Response(message, null), HttpStatus.OK);
	}
	
	//=========================================================================
	
	public ResponseEntity<Response> getInvalidInputResponseEntity(String message) {
		return new ResponseEntity<Response>(new Response(message, null), HttpStatus.OK);
	}
	
	//=========================================================================
	
	public void handleException(Logger LOGGER,  String logTag, String exceptionMessage, Throwable e, AuthorizationDetails authorizationDetails) throws ProjectXException {
		LOGGER.error(logTag + exceptionMessage, e);  
		throw new ProjectXException(exceptionRepository, logTag, exceptionMessage, e, authorizationDetails);
	}
	
	//=========================================================================
	
	//TODO: Need to remove this
	public Response getInvalidAuthCodeRespose(String authCode) {
		return new Response("Invalid AuthCode ["+authCode+"]", null);
	}
	
	//=========================================================================
	
	//TODO: Need to remove this
	public Response getUnAuthorizedAccessRespose() {
		return new Response("Unauthorized Access", null);
	}
	
	//=========================================================================
	
	//TODO: Need to remove this
	public Response getInvalidInputRespose() {
		return new Response("Invalid Input", null);
	}
	
	//=========================================================================
	
	//TODO: Need to remove this
	public Response getRespose(String message) {
		return new Response(message, null);
	}
	
	//=========================================================================
	
	public Long getOrganizationDetailsId(Long userDetailsId) {
		UserDetails ud = userRepository.findByUserDetailsId(userDetailsId);
		return ud.getOrganizationDetails().getOrganizationDetailsId();
	}
	
	//=========================================================================
	
	public OrganizationDetails getOrganizationDetails(Long userDetailsId) {
		UserDetails ud = userRepository.findByUserDetailsId(userDetailsId);
		return ud.getOrganizationDetails();
	}
	
	//=========================================================================
}
