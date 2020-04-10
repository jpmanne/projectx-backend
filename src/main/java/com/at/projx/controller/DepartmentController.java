/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.projx.common.Constants;
import com.at.projx.common.URLConstants;
import com.at.projx.dao.model.DepartmentDetails;
import com.at.projx.dao.model.OrganizationDetails;
import com.at.projx.exception.ProjectXException;
import com.at.projx.model.AuthorizationDetails;
import com.at.projx.model.Response;
import com.at.projx.repository.DepartmentRepository;
import com.at.projx.response.model.WebDepartmentDetails;
import com.at.projx.util.AppUtil;
import com.at.projx.util.ValidationUtil;

@RestController
@RequestMapping(URLConstants.Department.API_BASE)
public class DepartmentController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	private static final String DEPARTMENT_ALREADY_EXISTS = "Department already exists.";
	@Autowired
	DepartmentRepository departmentRepository;

	//=========================================================================

	@GetMapping(URLConstants.Department.IS_DEPARTMENT_EXISTS)
	public ResponseEntity<Response> isDepartmentExists(@RequestParam("authCode") String authCode, @RequestParam("department") String department) throws ProjectXException {
		String logTag = "isDepartmentExists() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		Response response = null;
		List<DepartmentDetails> existingDepartments = null;
		AuthorizationDetails authorizationDetails = null;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			
			if(!authorizationDetails.isValidAuthCode()) {
				return getInvalidAuthCodeResponseEntity(authCode);
			}
			if(!authorizationDetails.isValidAccess()) {
				return getUnauthorizedAccessResponseEntity();
			}
			
			existingDepartments = departmentRepository.findByDepartment(department, getOrganizationDetailsId(authorizationDetails.getUserDetailsId()));
			
			if(existingDepartments != null && !existingDepartments.isEmpty()) {
				response = new Response(DEPARTMENT_ALREADY_EXISTS, null);
			} else {
				response = new Response("Department available.", null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while isDepartmentExists";
			handleException(LOGGER, logTag, exceptionMessage, e, null); 
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}

	//=========================================================================
	
	@GetMapping(URLConstants.Department.GET_ALL_DEPARTMENTS)
	public ResponseEntity<Response> getDepartments(@RequestParam("authCode") String authCode, @RequestParam("status") String status) throws ProjectXException {
		String logTag = "getDepartments() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		AuthorizationDetails authorizationDetails = null;
		List<DepartmentDetails> departments = null; 
		Response response = null;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			if(!authorizationDetails.isValidAuthCode()) {
				return getInvalidAuthCodeResponseEntity(authCode);
			}
			if(!authorizationDetails.isValidAccess()) {
				return getUnauthorizedAccessResponseEntity();
			}
			departments = departmentRepository.getDepartments(getOrganizationDetailsId(authorizationDetails.getUserDetailsId()), status);
			if(departments != null && !departments.isEmpty()) {
				List<WebDepartmentDetails> webDepartments = new ArrayList<>();
				for(DepartmentDetails dd : departments) {
					webDepartments.add(dd.getWebDepartmentDetails());
				}
				response = new Response("departments", webDepartments);
			} else {
				response = new Response("departments not found", null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while retrieving all the departments";
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails); 
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}

	//=========================================================================
	
	@PostMapping(URLConstants.Department.ADD_DEPARTMENT)
	public ResponseEntity<Response> addDepartment(@RequestBody DepartmentDetails departmentDetails, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "addDepartment() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		AuthorizationDetails authorizationDetails = null;
		Response response = null;
		List<DepartmentDetails> departments = null;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			
			if(!authorizationDetails.isValidAuthCode()) {
				return getInvalidAuthCodeResponseEntity(authCode);
			}
			if(!authorizationDetails.isValidAccess()) {
				return getUnauthorizedAccessResponseEntity();
			}
			String validationResult = ValidationUtil.getInstance().validateAddDepartmentRequest(departmentDetails);
			
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return getInvalidInputResponseEntity(validationResult);
			} 
			OrganizationDetails organizationDetails = getOrganizationDetails(authorizationDetails.getUserDetailsId());
			departments = departmentRepository.findByDepartment(departmentDetails.getDepartment(), organizationDetails.getOrganizationDetailsId());
			
			if(departments != null && !departments.isEmpty()) {
				return getInvalidInputResponseEntity(DEPARTMENT_ALREADY_EXISTS);
			} 
			departmentDetails.setOrganizationDetails(organizationDetails);
			departmentDetails.setStatus(Constants.ACTIVE);
			DepartmentDetails dd = departmentRepository.save(departmentDetails);
			if(dd.getDepartmentDetailsId() != null) {
				response = new Response("Department added successfully", dd.getWebDepartmentDetails());
			} else {
				response = new Response("Department adding failure", null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while adding the Department ";
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}
	
	//=========================================================================

	@GetMapping(URLConstants.Department.GET_DEPARTMENT)
	public ResponseEntity<Response> getDepartment(@PathVariable(value = "departmentDetailsId") Long departmentDetailsId, @RequestParam("authCode") String authCode) throws ProjectXException {
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
			Optional<DepartmentDetails> departmentDetails = departmentRepository.findById(departmentDetailsId);
			
			if(departmentDetails.isPresent()) {
				DepartmentDetails existingUserDetails = departmentDetails.get();
			    response = new Response("Department Details", existingUserDetails.getWebDepartmentDetails());
			} else {
				response = new Response("Department not found with the departmentDetailsId :"+departmentDetailsId, null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while retrieving the department, "+departmentDetailsId;
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}
	
	//=========================================================================

	@PutMapping(URLConstants.Department.DEACTIVATE_DEPARTMENT)
	public ResponseEntity<Response> deactivateDepartment(@PathVariable(value = "departmentDetailsId") Long departmentDetailsId, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "deactivateDepartment() :";
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
			Optional<DepartmentDetails> departmentDetails = departmentRepository.findById(departmentDetailsId);
			if(departmentDetails.isPresent()) {
				DepartmentDetails existingUserDetails = departmentDetails.get();
				existingUserDetails.setStatus(Constants.INACTIVE);
				departmentRepository.save(existingUserDetails);
			    response = new Response("Deactivate department successful", null);
			} else {
				response = new Response("Department not found with the departmentDetailsId :"+departmentDetailsId, null);
			}
			
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while deactivting the department "+departmentDetailsId;
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}
	
	//=========================================================================
	
	@PutMapping(URLConstants.Department.ACTIVATE_DEPARTMENT)
	public ResponseEntity<Response> activateDepartment(@PathVariable(value = "departmentDetailsId") Long departmentDetailsId, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "activateDepartment() :";
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
			Optional<DepartmentDetails> departmentDetails = departmentRepository.findById(departmentDetailsId);
			
			if(departmentDetails.isPresent()) {
				DepartmentDetails existingUserDetails = departmentDetails.get();
				existingUserDetails.setStatus(Constants.ACTIVE);
				departmentRepository.save(existingUserDetails);
			    response = new Response("Activate department successful", null);
			} else {
				response = new Response("Department not found with the departmentDetailsId :"+departmentDetailsId, null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while activting the department "+departmentDetailsId;
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}
	
	//=========================================================================
	
	@PutMapping(URLConstants.Department.UPDATE_DEPARTMENT)
	public ResponseEntity<Response> updateDepartment(@RequestBody DepartmentDetails departmentDetails, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "updateDepartment() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		AuthorizationDetails authorizationDetails = null;
		Response response = null;
		Long departmentDetailsId = null;
		boolean isDepartmentExists = false;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			
			if(!authorizationDetails.isValidAuthCode()) {
				return getInvalidAuthCodeResponseEntity(authCode);
			}
			if(!authorizationDetails.isValidAccess()) {
				return getUnauthorizedAccessResponseEntity();
			}
			String validationResult = ValidationUtil.getInstance().validateUpdateDepartmentRequest(departmentDetails);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return getInvalidInputResponseEntity(validationResult);
			}
			departmentDetailsId = departmentDetails.getDepartmentDetailsId();
			OrganizationDetails organizationDetails = getOrganizationDetails(authorizationDetails.getUserDetailsId());
			
			List<DepartmentDetails> departments = departmentRepository.findByDepartment(departmentDetails.getDepartment(), organizationDetails.getOrganizationDetailsId());
			if(departments != null && !departments.isEmpty()) {
				for(DepartmentDetails details : departments) {
					if(details.getDepartmentDetailsId().compareTo(departmentDetailsId) != 0 && details.getDepartment().equalsIgnoreCase(departmentDetails.getDepartment())) {
						isDepartmentExists = true;
						break;
					}
				}
			} 
			
			if(isDepartmentExists) {
				response = new Response(DEPARTMENT_ALREADY_EXISTS, null);
			} else {
				Optional<DepartmentDetails> department = departmentRepository.findById(departmentDetailsId);
				
				if(department.isPresent()) {
					DepartmentDetails existingDepartment = department.get();
					existingDepartment.setDepartment(departmentDetails.getDepartment());
					existingDepartment.setDescription(departmentDetails.getDescription());
					DepartmentDetails updatedDepartmentDetails = departmentRepository.save(existingDepartment);
					response = new Response("Update department successful", updatedDepartmentDetails.getWebDepartmentDetails());
				} else {
					new Response("Department not found with the departmentDetailsId :"+departmentDetailsId, null);
				}
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while updating the Department, "+departmentDetailsId;
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}

	//=========================================================================
	
}
