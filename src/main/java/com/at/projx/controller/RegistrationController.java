/**
* @author  Jaya Prakash Manne
* @version 1.0
* @since   23-Jan-2020 
*/
package com.at.projx.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.at.projx.common.Constants;
import com.at.projx.common.URLConstants;
import com.at.projx.dao.model.OrganizationDetails;
import com.at.projx.dao.model.RoleDetails;
import com.at.projx.dao.model.UserDetails;
import com.at.projx.email.EmailDetails;
import com.at.projx.email.EmailUtil;
import com.at.projx.model.Response;
import com.at.projx.repository.OrganizationRepository;
import com.at.projx.repository.UserRepository;
import com.at.projx.request.model.BasicSetupRequest;
import com.at.projx.util.AuthCodeGenerator;
import com.at.projx.util.PasswordEncy;
import com.at.projx.util.ValidationUtil;

@RestController
@RequestMapping(URLConstants.Registration.API_BASE)
public class RegistrationController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	//=========================================================================
	
	@PostMapping(URLConstants.Registration.BASIC_SETUP)
	public ResponseEntity<Response> basicSetup(@Valid @RequestBody BasicSetupRequest basicSetupRequest) {
		String logTag = "basicSetup() ";
		LOGGER.info(logTag + "START of the method");
		List<OrganizationDetails> organizations = null;
		String message = null;
		String password = null;
		boolean isCredentialsMailSent = false;
		
		try {
			String validationResult = ValidationUtil.getInstance().validateBasicStepDetails(basicSetupRequest);
			
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return new ResponseEntity<Response>(new Response(validationResult, null), HttpStatus.OK);
			} else {
				organizations = organizationRepository.getOrganizationDetailsByOrganizationName(basicSetupRequest.getOrganizationName());
				
				if(organizations != null && !organizations.isEmpty()) {
					message = "Organization ["+ basicSetupRequest.getOrganizationName() +"] already registered with us. Please try with another Organization Name";
					//Need to throw an exception that the organization is already exists
					return new ResponseEntity<Response>(new Response(message, null), HttpStatus.OK);
				} else {
					//Step:1 Save the organization
					OrganizationDetails organizationDetails = new OrganizationDetails();
					organizationDetails.setOrganizationCode(AuthCodeGenerator.getInstance().getGeneratedAuthCode());
					organizationDetails.setOrganizationName(basicSetupRequest.getOrganizationName());
					organizationDetails.setNoOfEmployees(basicSetupRequest.getNoOfEmployees());
					organizationDetails.setCreatedDate(new Date());
					organizationDetails.setStatus(Constants.ACTIVE);
					organizationDetails = organizationRepository.save(organizationDetails);
					
					//Step:2 Save the user
					if(organizationDetails.getOrganizationDetailsId() != null) {
						RoleDetails roleDetails = new RoleDetails();
						roleDetails.setRoleId(Constants.ADMIN_ROLE);
						password = AuthCodeGenerator.getInstance().getGeneratedAuthCode();
						UserDetails userDetails = new UserDetails();
						userDetails.setFirstName(basicSetupRequest.getFirstName());
						userDetails.setLastName(basicSetupRequest.getLastName());
						userDetails.setEmail(basicSetupRequest.getWorkEmail());
						userDetails.setPhoneNumber(basicSetupRequest.getWorkPhone());
						userDetails.setOrganizationDetails(organizationDetails);
						userDetails.setPassword(PasswordEncy.getInstance().encrypt(password));
						userDetails.setRoleDetails(roleDetails);
						userDetails.setStatus(Constants.ACTIVE);
						userDetails = userRepository.save(userDetails);
						
						if(userDetails.getUserDetailsId() != null) {
							//Step:3 Need to send the registration mail to the user
							//TODO: Need to enable this after mail address configuration
							//EmailDetails emailDetails = EmailUtil.getInstance().populateCredenitalsMail(userDetails.getFirstName(), userDetails.getLastName(), userDetails.getEmail(), password);
							//isCredentialsMailSent = EmailUtil.getInstance().send(emailDetails);
						} else {
							//TODO: Need to throw an exception that unable to setup and remove the organization details
						}
					} else {
						//TODO: Need to throw an exception that unable to setup
						
					}
					//Step:4 Need to save the organization and registered user mapping table
					
					if(isCredentialsMailSent) {
						LOGGER.info(logTag + "Basic Setup completed successfully");
						message = "Basic Setup completed successfully";
					} else {
						LOGGER.info(logTag + "Unable to complete the Basic Setup");
						message = "Unable to complete the Basic Setup";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info(logTag + "END of the method");
		return new ResponseEntity<Response>(new Response(message, null), HttpStatus.OK);
	}
}
