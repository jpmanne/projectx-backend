/**
* @author  Jaya Prakash Manne
* @version 1.0
* @since   01-Nov-2019 
*/
package com.at.projx.util;

import java.util.Date;

import com.at.projx.common.Constants;
import com.at.projx.dao.model.DepartmentDetails;
import com.at.projx.dao.model.DesignationDetails;
import com.at.projx.request.model.BasicSetupRequest;
import com.at.projx.request.model.EducationDetailsRequest;
import com.at.projx.request.model.EmploymentDetailsRequest;
import com.at.projx.request.model.ExperienceDetailsRequest;
import com.at.projx.request.model.ProfileDetailsRequest;
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
	
	public String validateField(String fieldName, Date fieldValue) {
		if(fieldValue != null && fieldValue.getTime() > 0) {
			return Constants.SUCCESS;
		} else {
			return fieldName + " cannot be null";
		}
	}
	
	// ========================================================================
	
	public String validateBasicStepDetails(BasicSetupRequest details) {
		if(details != null) {
			String validateFieldResult = validateField("noOfEmployees", details.getNoOfEmployees(), true, 10);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("workEmail", details.getWorkEmail(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("firstName", details.getFirstName(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("lastName", details.getLastName(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("organizationName", details.getOrganizationName(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("workPhone", details.getWorkPhone(), true, 15);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
		} else {
			return "BasicSetupRequest cannot be null";
		}
		return Constants.SUCCESS;
	}
	
	// ========================================================================
	
	public String validateAddDepartmentRequest(DepartmentDetails details) {
		if(details != null) {
			String validateFieldResult = validateField("department", details.getDepartment(), true, 50);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("description", details.getDescription(), false, 255);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
		} else {
			return "Department Details cannot be null";
		}
		return Constants.SUCCESS;
	}
	
	// ========================================================================
	
	public String validateUpdateDepartmentRequest(DepartmentDetails details) {
		if(details != null) {
			String validateFieldResult = validateField("departmentDetailsId", details.getDepartmentDetailsId());
			
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("department", details.getDepartment(), true, 50);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("description", details.getDescription(), false, 255);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
		} else {
			return "DepartmentDetails cannot be null";
		}
		return Constants.SUCCESS;
	}
	
	// ========================================================================
	
	public String validateAddDesignationRequest(DesignationDetails details) {
		if(details != null) {
			String validateFieldResult = validateField("designation", details.getDesignation(), true, 50);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("description", details.getDescription(), false, 255);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
		} else {
			return "DepartmentDetails cannot be null";
		}
		return Constants.SUCCESS;
	}
	
	// ========================================================================
	
	public String validateUpdateDesignationRequest(DesignationDetails details) {
		if(details != null) {
			String validateFieldResult = validateField("designationDetailsId", details.getDesignationDetailsId());
			
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("designation", details.getDesignation(), true, 50);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("description", details.getDescription(), false, 255);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
		} else {
			return "DepartmentDetails cannot be null";
		}
		return Constants.SUCCESS;
	}
	
	// ========================================================================
	
	public String validateAddUserRequest(UserDetailsRequest details) {
		if(details != null) {
			String validateFieldResult = validateField("roleId", details.getRoleId());
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("email", details.getEmail(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("firstName", details.getFirstName(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("lastName", details.getLastName(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("phoneNumber", details.getPhoneNumber(), false, 15);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
		} else {
			return "User Details cannot be null";
		}
		return Constants.SUCCESS;
	}
	
	// ========================================================================
	
	public String validateEmploymentDetails(EmploymentDetailsRequest details) {
		if(details != null) {
			String validateFieldResult = validateField("userDetailsId", details.getUserDetailsId());
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("employeeId", details.getEmployeeId(), true, 15);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("dateOfJoining", details.getDateOfJoining());
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("departmentDetailsId", details.getDepartmentDetailsId());
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("designationDetailsId", details.getDesignationDetailsId());
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("reportingToUserDetailsId", details.getReportingToUserDetailsId());
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
		} else {
			return "Employment Details cannot be null";
		}
		return Constants.SUCCESS;
	}
	
	// ========================================================================
	
	public String validateProfileDetailsRequest(ProfileDetailsRequest details) {
		if(details != null) {
			String validationResult = null;
			validationResult = validateField("gender", details.getGender(), true, 1);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			
			validationResult = validateField("dateOfBirth", details.getDateOfBirth());
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			
			validationResult = validateField("maritalStatus", details.getMaritalStatus(), true, 1);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			
			validationResult = validateField("religion", details.getReligion(), false, 100);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			
			validationResult = validateField("nationality", details.getNationality(), false, 100);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			//Contact Details
			validationResult = validateField("address", details.getAddress(), true, 255);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			validationResult = validateField("city", details.getCity(), true, 100);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			validationResult = validateField("state", details.getState(), true, 100);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			validationResult = validateField("country", details.getCountry(), true, 100);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			validationResult = validateField("pinCode", details.getPinCode(), false, 15);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			validationResult = validateField("primaryContactName", details.getPrimaryContactName(), false, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			
			validationResult = validateField("primaryContactRelationship", details.getPrimaryContactRelationship(), false, 50);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			validationResult = validateField("primaryContactPhone", details.getPrimaryContactPhone(), false, 15);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			validationResult = validateField("secondaryContactName", details.getSecondaryContactName(), false, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			validationResult = validateField("secondaryContactRelationship", details.getSecondaryContactRelationship(), false, 50);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			validationResult = validateField("secondaryContactPhone", details.getSecondaryContactPhone(), false, 15);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			//Bank Details
			validationResult = validateField("bankAccountName", details.getBankAccountName(), false, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			validationResult = validateField("bankAccountNumber", details.getBankAccountNumber(), false, 50);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			validationResult = validateField("ifscCode", details.getIfscCode(), false, 15);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			//Authorization Details
			validationResult = validateField("passportNo", details.getPassportNo(), false, 15);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			validationResult = validateField("panNo", details.getPanNo(), false, 15);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			validationResult = validateField("aadharNo", details.getAadharNo(), false, 15);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			validationResult = validateField("ssn", details.getSsn(), false, 15);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
			validationResult = validateField("userDetailsId", details.getUserDetailsId());
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return validationResult;
			}
		} else {
			return "Profile Details cannot be null";
		}
		return Constants.SUCCESS;
	}
	
	// ========================================================================
	
	public String validateEducationDetails(EducationDetailsRequest details) {
		if(details != null) {
			String validateFieldResult = validateField("degree", details.getDegree(), true, 100);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("subject", details.getSubject(), true, 100);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("institution", details.getInstitution(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("startingDate", details.getStartingDate());
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("completionDate", details.getCompletionDate());
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("userDetailsId", details.getUserDetailsId());
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("status", details.getStatus(), true, 1);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
		} else {
			return "Education Details cannot be null";
		}
		return Constants.SUCCESS;
	}
	
	// ========================================================================
	
	public String validateExperienceDetails(ExperienceDetailsRequest details) {
		if(details != null) {
			String validateFieldResult = validateField("companyName", details.getCompanyName(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("jobPosition", details.getJobPosition(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("location", details.getLocation(), true, 150);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("periodFrom", details.getPeriodTo());
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("periodTo", details.getPeriodTo());
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			validateFieldResult = validateField("userDetailsId", details.getUserDetailsId());
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
			
			validateFieldResult = validateField("status", details.getStatus(), true, 1);
			if(!Constants.SUCCESS.equalsIgnoreCase(validateFieldResult)) {
				return validateFieldResult;
			}
		} else {
			return "Experience Details cannot be null";
		}
		return Constants.SUCCESS;
	}
	
	// ========================================================================
	
}