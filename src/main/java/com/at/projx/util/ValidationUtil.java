/**
* @author  Jaya Prakash Manne
* @version 1.0
* @since   01-Nov-2019 
*/
package com.at.projx.util;

import com.at.projx.common.Constants;
import com.at.projx.dao.model.DepartmentDetails;
import com.at.projx.dao.model.DesignationDetails;
import com.at.projx.request.model.BasicSetupRequest;
import com.at.projx.request.model.UserDetailsRequest;

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
	
	public String validateField(String fieldName, Long fieldValue) {
		if(fieldValue != null && fieldValue.longValue() > 0) {
			return Constants.SUCCESS;
		} else {
			return fieldName + " cannot be null";
		}
	}
	
	// ========================================================================
	
	public String validateBasicStepDetails(BasicSetupRequest details) {
		if(details != null) {
			String validateFieldResult1 = validateField("noOfEmployees", details.getNoOfEmployees(), true, 10);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult1)) {
				return validateFieldResult1;
			}
			
			String validateFieldResult2 = validateField("workEmail", details.getWorkEmail(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult2)) {
				return validateFieldResult2;
			}
			
			String validateFieldResult3 = validateField("firstName", details.getFirstName(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult3)) {
				return validateFieldResult3;
			}
			
			String validateFieldResult4 = validateField("lastName", details.getLastName(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult4)) {
				return validateFieldResult4;
			}
			
			String validateFieldResult5 = validateField("organizationName", details.getOrganizationName(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult5)) {
				return validateFieldResult5;
			}
			
			String validateFieldResult6 = validateField("workPhone", details.getWorkPhone(), true, 15);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult6)) {
				return validateFieldResult6;
			}
		} else {
			return "BasicSetupRequest cannot be null";
		}
		return Constants.SUCCESS;
	}
	
	// ========================================================================
	
	public String validateAddDepartmentRequest(DepartmentDetails details) {
		if(details != null) {
			String validateFieldResult1 = validateField("department", details.getDepartment(), true, 50);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult1)) {
				return validateFieldResult1;
			}
			
			String validateFieldResult2 = validateField("description", details.getDescription(), false, 255);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult2)) {
				return validateFieldResult2;
			}
		} else {
			return "DepartmentDetails cannot be null";
		}
		return Constants.SUCCESS;
	}
	
	// ========================================================================
	
	public String validateUpdateDepartmentRequest(DepartmentDetails details) {
		if(details != null) {
			String validateFieldResult1 = validateField("departmentDetailsId", details.getDepartmentDetailsId());
			
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult1)) {
				return validateFieldResult1;
			}
			
			String validateFieldResult2 = validateField("department", details.getDepartment(), true, 50);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult2)) {
				return validateFieldResult2;
			}
			
			String validateFieldResult3 = validateField("description", details.getDescription(), false, 255);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult3)) {
				return validateFieldResult3;
			}
		} else {
			return "DepartmentDetails cannot be null";
		}
		return Constants.SUCCESS;
	}
	
	// ========================================================================
	
	public String validateAddDesignationRequest(DesignationDetails details) {
		if(details != null) {
			String validateFieldResult1 = validateField("designation", details.getDesignation(), true, 50);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult1)) {
				return validateFieldResult1;
			}
			
			String validateFieldResult2 = validateField("description", details.getDescription(), false, 255);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult2)) {
				return validateFieldResult2;
			}
		} else {
			return "DepartmentDetails cannot be null";
		}
		return Constants.SUCCESS;
	}
	
	// ========================================================================
	
	public String validateUpdateDesignationRequest(DesignationDetails details) {
		if(details != null) {
			String validateFieldResult1 = validateField("designationDetailsId", details.getDesignationDetailsId());
			
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult1)) {
				return validateFieldResult1;
			}
			
			String validateFieldResult2 = validateField("designation", details.getDesignation(), true, 50);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult2)) {
				return validateFieldResult2;
			}
			
			String validateFieldResult3 = validateField("description", details.getDescription(), false, 255);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult3)) {
				return validateFieldResult3;
			}
		} else {
			return "DepartmentDetails cannot be null";
		}
		return Constants.SUCCESS;
	}
	
	// ========================================================================
	
	public String validateAddUserRequest(UserDetailsRequest details) {
		if(details != null) {
			String validateFieldResult1 = validateField("roleId", details.getRoleId());
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult1)) {
				return validateFieldResult1;
			}
			
			String validateFieldResult2 = validateField("email", details.getEmail(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult2)) {
				return validateFieldResult2;
			}
			
			String validateFieldResult3 = validateField("firstName", details.getFirstName(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult3)) {
				return validateFieldResult3;
			}
			
			String validateFieldResult4 = validateField("lastName", details.getLastName(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult4)) {
				return validateFieldResult4;
			}
			
			String validateFieldResult5 = validateField("phoneNumber", details.getPhoneNumber(), false, 15);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult5)) {
				return validateFieldResult5;
			}
		} else {
			return "User Details cannot be null";
		}
		return Constants.SUCCESS;
	}
	
	// ========================================================================
	
}