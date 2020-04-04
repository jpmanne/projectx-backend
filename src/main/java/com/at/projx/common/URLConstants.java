/**
* @author  Jaya Prakash Manne
* @version 1.0
* @since   01-Nov-2019 
*/
package com.at.projx.common;

public class URLConstants {

	public class User {
		public static final String API_BASE = "/api/user";
		public static final String GET_ALL_USERS = "/all";
		public static final String GET_USER = "/{userDetailsId}";
		public static final String ADD_USER = "/add";
		public static final String UPDATE_USER = "/update/{userDetailsId}";
		public static final String DELETE_USER = "/{userDetailsId}";
		public static final String IS_EMAIL_EXISTS = "/email/exists";
	}
	
	//=========================================================================
	
	public class Login {
		public static final String API_BASE = "/api/user";
		public static final String LOGIN_USER = "/login";
		public static final String LOGOUT_USER = "/logout";
	}
	
	//=========================================================================
	
	public class Registration {
		public static final String API_BASE = "/api/registration";
		public static final String BASIC_SETUP = "/basic/setup";
	}
	
	//=========================================================================
	
}
