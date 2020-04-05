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
import org.springframework.http.HttpStatus;
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
import com.at.projx.dao.model.DesignationDetails;
import com.at.projx.dao.model.OrganizationDetails;
import com.at.projx.exception.ProjectXException;
import com.at.projx.model.AuthorizationDetails;
import com.at.projx.model.Response;
import com.at.projx.repository.DesignationRepository;
import com.at.projx.response.model.WebDesignationDetails;
import com.at.projx.util.AppUtil;
import com.at.projx.util.ValidationUtil;

@RestController
@RequestMapping(URLConstants.Designation.API_BASE)
public class DesignationController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DesignationController.class);
	private static final String DESIGNATION_ALREADY_EXISTS = "Designation already exists.";

	@Autowired
	DesignationRepository designationRepository;

	//=========================================================================

	@GetMapping(URLConstants.Designation.IS_DESIGNATION_EXISTS)
	public ResponseEntity<Response> isDesignationExists(@RequestParam("authCode") String authCode, @RequestParam("designation") String designation) throws ProjectXException {
		String logTag = "isDesignationExists() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		Response response = null;
		List<DesignationDetails> existingDesignations = null;
		AuthorizationDetails authorizationDetails = null;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			
			if(!authorizationDetails.isValidAuthCode()) {
				return getInvalidAuthCodeResponseEntity(authCode);
			}
			if(!authorizationDetails.isValidAccess()) {
				return getUnauthorizedAccessResponseEntity();
			}
			
			existingDesignations = designationRepository.findByDesignation(designation, getOrganizationDetailsId(authorizationDetails.getUserDetailsId()));
			
			if(existingDesignations != null && !existingDesignations.isEmpty()) {
				response = new Response(DESIGNATION_ALREADY_EXISTS, null);
			} else {
				response = new Response("Designation available.", null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while isDesignationExists";
			handleException(LOGGER, logTag, exceptionMessage, e, null); 
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	//=========================================================================
	
	@GetMapping(URLConstants.Designation.GET_ALL_DESIGNATIONS)
	public ResponseEntity<Response> getDesignations(@RequestParam("authCode") String authCode, @RequestParam("status") String status) throws ProjectXException {
		String logTag = "getDesignations() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		AuthorizationDetails authorizationDetails = null;
		List<DesignationDetails> designations = null; 
		Response response = null;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			if(!authorizationDetails.isValidAuthCode()) {
				return getInvalidAuthCodeResponseEntity(authCode);
			}
			if(!authorizationDetails.isValidAccess()) {
				return getUnauthorizedAccessResponseEntity();
			}
			designations = designationRepository.getDesignations(getOrganizationDetailsId(authorizationDetails.getUserDetailsId()), status);
			if(designations != null && !designations.isEmpty()) {
				List<WebDesignationDetails> webDesignations = new ArrayList<>();
				for(DesignationDetails dd : designations) {
					webDesignations.add(dd.getWebDesignationDetails());
				}
				response = new Response("designations", webDesignations);
			} else {
				response = new Response("designations not found", null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while retrieving all the designations";
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails); 
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	//=========================================================================
	
	@PostMapping(URLConstants.Designation.ADD_DESIGNATION)
	public ResponseEntity<Response> addDesignation(@RequestBody DesignationDetails designationDetails, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "addDesignation() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		AuthorizationDetails authorizationDetails = null;
		Response response = null;
		List<DesignationDetails> designations = null;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			
			if(!authorizationDetails.isValidAuthCode()) {
				return getInvalidAuthCodeResponseEntity(authCode);
			}
			if(!authorizationDetails.isValidAccess()) {
				return getUnauthorizedAccessResponseEntity();
			}
			String validationResult = ValidationUtil.getInstance().validateAddDesignationRequest(designationDetails);
			
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return getInvalidInputResponseEntity(validationResult);
			} 
			OrganizationDetails organizationDetails = getOrganizationDetails(authorizationDetails.getUserDetailsId());
			designations = designationRepository.findByDesignation(designationDetails.getDesignation(), organizationDetails.getOrganizationDetailsId());
			
			if(designations != null && !designations.isEmpty()) {
				return getInvalidInputResponseEntity(DESIGNATION_ALREADY_EXISTS);
			} 
			designationDetails.setOrganizationDetails(organizationDetails);
			designationDetails.setStatus(Constants.ACTIVE);
			DesignationDetails dd = designationRepository.save(designationDetails);
			if(dd.getDesignationDetailsId() != null) {
				response = new Response("Designation added successfully", dd.getWebDesignationDetails());
			} else {
				response = new Response("Designation adding failure", null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while adding the Designation ";
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	//=========================================================================

	@GetMapping(URLConstants.Designation.GET_DESIGNATION)
	public ResponseEntity<Response> getDesignation(@PathVariable(value = "designationDetailsId") Long designationDetailsId, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "getDesignation() :";
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
			Optional<DesignationDetails> designationDetails = designationRepository.findById(designationDetailsId);
			
			if(designationDetails.isPresent()) {
				DesignationDetails existingUserDetails = designationDetails.get();
			    response = new Response("Designation Details", existingUserDetails.getWebDesignationDetails());
			} else {
				response = new Response("Designation not found with the designationDetailsId :"+designationDetailsId, null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while retrieving the designation, "+designationDetailsId;
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	//=========================================================================

	@PutMapping(URLConstants.Designation.DEACTIVATE_DESIGNATION)
	public ResponseEntity<Response> deactivateDesignation(@PathVariable(value = "designationDetailsId") Long designationDetailsId, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "deactivateDesignation() :";
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
			Optional<DesignationDetails> designationDetails = designationRepository.findById(designationDetailsId);
			if(designationDetails.isPresent()) {
				DesignationDetails existingUserDetails = designationDetails.get();
				existingUserDetails.setStatus(Constants.INACTIVE);
				designationRepository.save(existingUserDetails);
			    response = new Response("Deactivate designation successful", null);
			} else {
				response = new Response("Designation not found with the designationDetailsId :"+designationDetailsId, null);
			}
			
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while deactivting the designation "+designationDetailsId;
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	//=========================================================================
	
	@PutMapping(URLConstants.Designation.ACTIVATE_DESIGNATION)
	public ResponseEntity<Response> activateDesignation(@PathVariable(value = "designationDetailsId") Long designationDetailsId, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "activateDesignation() :";
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
			Optional<DesignationDetails> designationDetails = designationRepository.findById(designationDetailsId);
			
			if(designationDetails.isPresent()) {
				DesignationDetails existingUserDetails = designationDetails.get();
				existingUserDetails.setStatus(Constants.ACTIVE);
				designationRepository.save(existingUserDetails);
			    response = new Response("Activate designation successful", null);
			} else {
				response = new Response("Designation not found with the designationDetailsId :"+designationDetailsId, null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while activting the designation "+designationDetailsId;
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	//=========================================================================
	
	@PutMapping(URLConstants.Designation.UPDATE_DESIGNATION)
	public ResponseEntity<Response> updateDesignation(@RequestBody DesignationDetails designationDetails, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "updateDesignation() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		AuthorizationDetails authorizationDetails = null;
		Response response = null;
		Long designationDetailsId = null;
		boolean isDesignationExists = false;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			
			if(!authorizationDetails.isValidAuthCode()) {
				return getInvalidAuthCodeResponseEntity(authCode);
			}
			if(!authorizationDetails.isValidAccess()) {
				return getUnauthorizedAccessResponseEntity();
			}
			String validationResult = ValidationUtil.getInstance().validateUpdateDesignationRequest(designationDetails);
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return getInvalidInputResponseEntity(validationResult);
			}
			designationDetailsId = designationDetails.getDesignationDetailsId();
			OrganizationDetails organizationDetails = getOrganizationDetails(authorizationDetails.getUserDetailsId());
			
			List<DesignationDetails> designations = designationRepository.findByDesignation(designationDetails.getDesignation(), organizationDetails.getOrganizationDetailsId());
			if(designations != null && !designations.isEmpty()) {
				for(DesignationDetails details : designations) {
					if(details.getDesignationDetailsId().compareTo(designationDetailsId) != 0 && details.getDesignation().equalsIgnoreCase(designationDetails.getDesignation())) {
						isDesignationExists = true;
						break;
					}
				}
			} 
			
			if(isDesignationExists) {
				response = new Response(DESIGNATION_ALREADY_EXISTS, null);
			} else {
				Optional<DesignationDetails> designation = designationRepository.findById(designationDetailsId);
				
				if(designation.isPresent()) {
					DesignationDetails existingDesignation = designation.get();
					existingDesignation.setDesignation(designationDetails.getDesignation());
					existingDesignation.setDescription(designationDetails.getDescription());
					DesignationDetails updatedDesignationDetails = designationRepository.save(existingDesignation);
					response = new Response("Update designation successful", updatedDesignationDetails.getWebDesignationDetails());
				} else {
					new Response("Designation not found with the designationDetailsId :"+designationDetailsId, null);
				}
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while updating the Designation, "+designationDetailsId;
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	//=========================================================================
	
}
