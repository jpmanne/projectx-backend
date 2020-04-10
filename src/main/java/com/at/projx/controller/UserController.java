/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.at.projx.dao.model.RoleDetails;
import com.at.projx.dao.model.UserDetails;
import com.at.projx.exception.ProjectXException;
import com.at.projx.model.AuthorizationDetails;
import com.at.projx.model.Response;
import com.at.projx.request.model.UserDetailsRequest;
import com.at.projx.response.model.WebUserDetails;
import com.at.projx.util.AppUtil;
import com.at.projx.util.UniversalUniqueCodeGenerator;
import com.at.projx.util.ValidationUtil;

@RestController
@RequestMapping(URLConstants.User.API_BASE)
public class UserController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	//=========================================================================

	@GetMapping(URLConstants.User.IS_EMAIL_EXISTS)
	public ResponseEntity<Response> isEmailExists(@RequestParam("email") String email) throws ProjectXException {
		String logTag = "isEmailExists() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		Response response = null;
		List<UserDetails> existingUsers = null;
		
		try {
			existingUsers = userRepository.getUserByEmail(email.toLowerCase());
			
			if(existingUsers != null && !existingUsers.isEmpty()) {
				response = new Response("Email already exists.", null);
			} else {
				response = new Response("Email available.", null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while isEmailExists";
			handleException(LOGGER, logTag, exceptionMessage, e, null); 
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}

	//=========================================================================
	
	@GetMapping(URLConstants.User.GET_ALL_USERS)
	public ResponseEntity<Response> getAllUsers(@RequestParam("authCode") String authCode, @RequestParam("status") String status) throws ProjectXException {
		String logTag = "getAllUsers() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		AuthorizationDetails authorizationDetails = null;
		List<UserDetails> users = null; 
		Response response = null;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			
			if(!authorizationDetails.isValidAuthCode()) {
				return getInvalidAuthCodeResponseEntity(authCode);
			}
			if(!authorizationDetails.isValidAccess()) {
				return getUnauthorizedAccessResponseEntity();
			}
			users = userRepository.getUsers(getOrganizationDetailsId(authorizationDetails.getUserDetailsId()), status, authorizationDetails.getUserDetailsId());
			
			if(users != null && !users.isEmpty()) {
				List<WebUserDetails> webUsers = new ArrayList<WebUserDetails>(users.size());
				for (UserDetails userDetails : users) {
					webUsers.add(userDetails.getWebUser());
				}
				response = new Response("Users", webUsers);
			} else {
				response = new Response("Users not found", null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while retrieving all the users";
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails); 
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}

	//=========================================================================
	
	@PostMapping(URLConstants.User.ADD_USER)
	public ResponseEntity<Response> addUser(@RequestBody UserDetailsRequest requestObj, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "addUser() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		AuthorizationDetails authorizationDetails = null;
		Response response = null;
		UserDetails userDetails = null;
		String email = null;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			
			if(!authorizationDetails.isValidAuthCode()) {
				return getInvalidAuthCodeResponseEntity(authCode);
			}
			
			if(!authorizationDetails.isValidAccess()) {
				return getUnauthorizedAccessResponseEntity();
			}
			String validationResult = ValidationUtil.getInstance().validateAddUserRequest(requestObj);
			
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return getInvalidInputResponseEntity(validationResult);
			}
			email = requestObj.getEmail().trim().toLowerCase();
			List<UserDetails> existingUsers = userRepository.getUserByEmail(email);
			
			if(existingUsers != null && !existingUsers.isEmpty()) {
				return getInvalidInputResponseEntity("Email already exists.");
			} 
			userDetails = new UserDetails();
			userDetails.setEmail(email);
			userDetails.setFirstName(requestObj.getFirstName());
			userDetails.setLastName(requestObj.getLastName());
			userDetails.setPassword(UniversalUniqueCodeGenerator.getInstance().getAutoGeneratedPassword());
			userDetails.setPhoneNumber(requestObj.getPhoneNumber());
			RoleDetails roleDetails = new RoleDetails();
			roleDetails.setRoleId(requestObj.getRoleId());
			userDetails.setRoleDetails(roleDetails);
			userDetails.setStatus(Constants.ACTIVE);
			userDetails.setOrganizationDetails(getOrganizationDetails(authorizationDetails.getUserDetailsId()));
			userDetails.setCreatedAt(new Date());
			UserDetails ud = userRepository.save(userDetails);
			
			if(ud.getUserDetailsId() != null) {
				response = new Response("User added successfully", ud.getWebUser());
			} else {
				response = new Response("User adding failure", null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while adding the user ";
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}
	
	//=========================================================================

	@GetMapping(URLConstants.User.GET_USER)
	public ResponseEntity<Response> getUserById(@PathVariable(value = "userDetailsId") Long userDetailsId, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "getUserById() :";
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
			Optional<UserDetails> userDetails = userRepository.findById(userDetailsId);
			
			if(userDetails.isPresent()) {
			    UserDetails existingUserDetails = userDetails.get();
			    response = new Response("User Details", existingUserDetails.getWebUser());
			} else {
				response = new Response("User not found with the userDetailsId :"+userDetailsId, null);
			}
			
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while retrieving the user, "+userDetailsId;
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}
	
	//=========================================================================

	@PutMapping(URLConstants.User.DEACTIVATE_USER)
	public ResponseEntity<Response> deactivateUser(@PathVariable(value = "userDetailsId") Long userDetailsId, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "deactivateUser() :";
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
			
			Optional<UserDetails> userDetails = userRepository.findById(userDetailsId);
			if(userDetails.isPresent()) {
			    UserDetails existingUserDetails = userDetails.get();
			    existingUserDetails.setStatus(Constants.INACTIVE); 
			    userRepository.save(existingUserDetails);
			    response = new Response("User deactivate successful", null);
			} else {
				response = new Response("User not found with the userDetailsId :"+userDetailsId, null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while deactivating the user "+userDetailsId;
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}
	
	//=========================================================================
	
	@PutMapping(URLConstants.User.ACTIVATE_USER)
	public ResponseEntity<Response> activateUser(@PathVariable(value = "userDetailsId") Long userDetailsId, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "activateUser() :";
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
			
			Optional<UserDetails> userDetails = userRepository.findById(userDetailsId);
			if(userDetails.isPresent()) {
			    UserDetails existingUserDetails = userDetails.get();
			    existingUserDetails.setStatus(Constants.ACTIVE); 
			    userRepository.save(existingUserDetails);
			    response = new Response("User activate successful", null);
			} else {
				response = new Response("User not found with the userDetailsId :"+userDetailsId, null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while activating the user "+userDetailsId;
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}
	
	//=========================================================================
	
	/*@PutMapping(URLConstants.User.UPDATE_USER)
	public ResponseEntity<Response> updateUser(@PathVariable(value = "userDetailsId") Long userDetailsId, @Valid @RequestBody UserDetails userDetails, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "updateUser() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		AuthorizationDetails authorizationDetails = null;
		Response response = null;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			
			if(authorizationDetails.isValidAuthCode()) {
				if(authorizationDetails.isValidAccess()) {
					Optional<UserDetails> user = userRepository.findById(userDetailsId);
					
					if(user != null && user.isPresent()) {
					    UserDetails existingUser = user.get();
					    existingUser.setFirstName(userDetails.getFirstName());
					    existingUser.setLastName(userDetails.getLastName()); 
					    //existingUser.setMiddleName(userDetails.getMiddleName());
					    existingUser.setEmail(userDetails.getEmail());
					    existingUser.setPhoneNumber(userDetails.getPhoneNumber());
					    //existingUser.setAddressLine1(userDetails.getAddressLine1());
					    //existingUser.setAddressLine2(userDetails.getAddressLine2());
					    //existingUser.setCity(userDetails.getCity());
					    //existingUser.setState(userDetails.getState());
					    //existingUser.setCountry(userDetails.getCountry());
						UserDetails updatedUserDetails = userRepository.save(existingUser);
						response = new Response("Update User Successful", updatedUserDetails.getWebUser());
					} else {
						new Response("User not found with the userDetailsId :"+userDetailsId, null);
					}
				} else {
					LOGGER.info(logTag + "Unauthorized Access : "+authCode);
					return new ResponseEntity<Response>(getUnAuthorizedAccessRespose(), HttpStatus.UNAUTHORIZED);
				}
			} else {
				response = getInvalidAuthCodeRespose(authCode);
				LOGGER.info(logTag + "Invalid AuthCode : "+authCode);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while updating the user, "+userDetailsId;
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	//=========================================================================
*/}
