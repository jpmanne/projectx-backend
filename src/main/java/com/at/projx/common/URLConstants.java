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
		public static final String DEACTIVATE_USER = "/deactivate/{userDetailsId}";
		public static final String ACTIVATE_USER = "/activate/{userDetailsId}";
		public static final String IS_EMAIL_EXISTS = "/email/exists";
		public static final String UPDATE_USER = "/update/{userDetailsId}";
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
	
	public class Department {
		public static final String API_BASE = "/api/department";
		public static final String GET_ALL_DEPARTMENTS = "/all";
		public static final String GET_DEPARTMENT = "/{departmentDetailsId}";
		public static final String ADD_DEPARTMENT = "/add";
		public static final String UPDATE_DEPARTMENT = "/update";
		public static final String IS_DEPARTMENT_EXISTS = "/exists";
		public static final String DEACTIVATE_DEPARTMENT = "/deactivate/{departmentDetailsId}";
		public static final String ACTIVATE_DEPARTMENT = "/activate/{departmentDetailsId}";
	}
	
	//=========================================================================
	
	public class Designation {
		public static final String API_BASE = "/api/designation";
		public static final String GET_ALL_DESIGNATIONS = "/all";
		public static final String GET_DESIGNATION = "/{designationDetailsId}";
		public static final String ADD_DESIGNATION = "/add";
		public static final String UPDATE_DESIGNATION = "/update";
		public static final String IS_DESIGNATION_EXISTS = "/exists";
		public static final String DEACTIVATE_DESIGNATION = "/deactivate/{designationDetailsId}";
		public static final String ACTIVATE_DESIGNATION = "/activate/{designationDetailsId}";
	}
	
	//=========================================================================
	
	public class Employment {
		public static final String API_BASE = "/api/employment";
		public static final String SAVE_EMPLOYMENT = "/save";
		public static final String GET_EMPLOYMENT = "/{employmentDetailsId}";
		public static final String UPDATE_EMPLOYMENT = "/update";
	}
	
	//=========================================================================
	
	public class Profile {
		public static final String API_BASE = "/api/profile";
		public static final String GET_PROFILE = "/{userDetailsId}";
		public static final String SAVE_PROFILE = "/save";
		public static final String GET_EDUCATIONS = "/educations/{userDetailsId}";
		public static final String DELETE_EDUCATION = "/education/delete/{educationDetailsId}";
		public static final String SAVE_EDUCATIONS = "/educations/save";
		public static final String GET_EXPERIENCES = "/experiences/{userDetailsId}";
		public static final String DELETE_EXPERIENCE = "/experience/delete/{experienceDetailsId}";
		public static final String SAVE_EXPERIENCES = "/experiences/save";
	}
	
	//=========================================================================
}
