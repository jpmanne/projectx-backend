/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.projx.common.Constants;
import com.at.projx.common.URLConstants;
import com.at.projx.dao.model.DepartmentDetails;
import com.at.projx.dao.model.DesignationDetails;
import com.at.projx.dao.model.EmploymentDetails;
import com.at.projx.dao.model.UserDetails;
import com.at.projx.exception.ProjectXException;
import com.at.projx.model.AuthorizationDetails;
import com.at.projx.model.Response;
import com.at.projx.repository.EmploymentRepository;
import com.at.projx.request.model.EmploymentDetailsRequest;
import com.at.projx.util.AppUtil;
import com.at.projx.util.ValidationUtil;

@RestController
@RequestMapping(URLConstants.Employment.API_BASE)
public class EmploymentController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmploymentController.class);
	
	@Autowired
	EmploymentRepository employmentRepository;

	//=========================================================================

	@PostMapping(URLConstants.Employment.SAVE_EMPLOYMENT)
	public ResponseEntity<Response> saveEmployemnt(@RequestBody EmploymentDetailsRequest reqObj, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "saveEmployemnt() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		AuthorizationDetails authorizationDetails = null;
		Response response = null;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			
			if(!authorizationDetails.isValidAuthCode()) {
				return getInvalidAuthCodeResponseEntity(authCode);
			}
			if(!authorizationDetails.isValidAccess()) {
				return getUnauthorizedAccessResponseEntity();
			}
			String validationResult = ValidationUtil.getInstance().validateEmploymentDetails(reqObj);
			
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return getInvalidInputResponseEntity(validationResult);
			} 
			String employeeId = reqObj.getEmployeeId().trim().toLowerCase();
			List<EmploymentDetails> employmentsByEmployeeId = employmentRepository.findByEmployeeId(employeeId, getOrganizationDetails(authorizationDetails.getUserDetailsId()).getOrganizationDetailsId());
			if(employmentsByEmployeeId != null && !employmentsByEmployeeId.isEmpty()) {
				return getInvalidInputResponseEntity("Employee Id already exists");
			}
			EmploymentDetails employmentDetails = populateEmploymentDetails(reqObj);
			employmentDetails.setEmployeeId(employeeId);
			EmploymentDetails ed = employmentRepository.save(employmentDetails);
			
			if(ed.getEmploymentDetailsId() != null) {
				reqObj.setEmploymentDetailsId(ed.getEmploymentDetailsId()); 
				response = new Response("Employment Details saved successfully", reqObj);
			} else {
				response = new Response("Employment Details saving failure", null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while saving the Employment ";
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}
	
	//=========================================================================
	
	@PostMapping(URLConstants.Employment.UPDATE_EMPLOYMENT)
	public ResponseEntity<Response> updateEmployemnt(@RequestBody EmploymentDetailsRequest reqObj, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "updateEmployemnt() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		AuthorizationDetails authorizationDetails = null;
		Response response = null;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			
			if(!authorizationDetails.isValidAuthCode()) {
				return getInvalidAuthCodeResponseEntity(authCode);
			}
			if(!authorizationDetails.isValidAccess()) {
				return getUnauthorizedAccessResponseEntity();
			}
			
			String validationResult = ValidationUtil.getInstance().validateEmploymentDetails(reqObj);
			
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return getInvalidInputResponseEntity(validationResult);
			} 
			
			if(reqObj.getEmploymentDetailsId() == null || reqObj.getReportingToUserDetailsId().longValue() <= 0) {
				return getInvalidInputResponseEntity("employmentDetailsId cannot be null or empty");
			}
			
			String employeeId = reqObj.getEmployeeId().trim().toLowerCase();
			List<EmploymentDetails> employmentsByEmployeeId = employmentRepository.findByEmployeeId(employeeId, getOrganizationDetails(authorizationDetails.getUserDetailsId()).getOrganizationDetailsId());
			if(employmentsByEmployeeId != null && !employmentsByEmployeeId.isEmpty()) {
				for(EmploymentDetails details : employmentsByEmployeeId) {
					if(reqObj.getEmploymentDetailsId().compareTo(details.getEmploymentDetailsId()) != 0 && employeeId.equalsIgnoreCase(details.getEmployeeId())) {
						return getInvalidInputResponseEntity("Employee Id already exists");
					}
				}
			}
			EmploymentDetails employmentDetails = populateEmploymentDetails(reqObj);
			employmentDetails.setEmployeeId(employeeId);
			EmploymentDetails ed = employmentRepository.save(employmentDetails);
			
			if(ed.getEmploymentDetailsId() != null) {
				reqObj.setEmploymentDetailsId(ed.getEmploymentDetailsId()); 
				response = new Response("Employment Details updated successfully", reqObj);
			} else {
				response = new Response("Employment Details updating failure", null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while updating the Employment ";
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}
	
	//=========================================================================
	
	private EmploymentDetails populateEmploymentDetails(EmploymentDetailsRequest reqObj) {
		EmploymentDetails employmentDetails = new EmploymentDetails();
		
		if(reqObj.getEmploymentDetailsId() != null && reqObj.getEmploymentDetailsId().longValue() > 0) {
			employmentDetails.setEmploymentDetailsId(reqObj.getEmploymentDetailsId()); 
		}
		employmentDetails.setEmployeeId(reqObj.getEmployeeId());
		employmentDetails.setDateOfJoining(reqObj.getDateOfJoining());
		DepartmentDetails departmentDetails = new DepartmentDetails();
		departmentDetails.setDepartmentDetailsId(reqObj.getDepartmentDetailsId());
		employmentDetails.setDepartmentDetails(departmentDetails);
		DesignationDetails designationDetails = new DesignationDetails();
		designationDetails.setDesignationDetailsId(reqObj.getDesignationDetailsId());
		employmentDetails.setDesignationDetails(designationDetails);
		UserDetails reportingToUserDetails = new UserDetails();
		reportingToUserDetails.setUserDetailsId(reqObj.getReportingToUserDetailsId());
		employmentDetails.setReportingToUserDetails(reportingToUserDetails);
		UserDetails userDetails = new UserDetails();
		userDetails.setUserDetailsId(reqObj.getUserDetailsId());
		employmentDetails.setUserDetails(userDetails);
		return employmentDetails;
	}
	//=========================================================================

	@GetMapping(URLConstants.Employment.GET_EMPLOYMENT)
	public ResponseEntity<Response> getDepartment(@PathVariable(value = "employmentDetailsId") Long employmentDetailsId, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "getDepartment() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		AuthorizationDetails authorizationDetails = null;
		Response response = null;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			if(!authorizationDetails.isValidAuthCode()) {
				return getInvalidAuthCodeResponseEntity(authCode);
			}
			if(!authorizationDetails.isValidAccess()) {
				return getUnauthorizedAccessResponseEntity();
			}
			//Update case
			Optional<EmploymentDetails> optionalEmploymentDetails = employmentRepository.findById(employmentDetailsId);
			if(optionalEmploymentDetails.isPresent()) {
				response = new Response("Employment Details", optionalEmploymentDetails.get().getWebEmploymentDetails());
			} else {
				response = new Response("Employment Details not found with the departmentDetailsId :"+employmentDetailsId, null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while retrieving the Employment Details, "+employmentDetailsId;
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}
	
	//=========================================================================
	
}