/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.at.projx.dao.model.EducationDetails;
import com.at.projx.dao.model.EmploymentDetails;
import com.at.projx.dao.model.ExperienceDetails;
import com.at.projx.dao.model.ProfileDetails;
import com.at.projx.dao.model.UserDetails;
import com.at.projx.exception.ProjectXException;
import com.at.projx.model.AuthorizationDetails;
import com.at.projx.model.Response;
import com.at.projx.repository.EducationRepository;
import com.at.projx.repository.EmploymentRepository;
import com.at.projx.repository.ExperienceRepository;
import com.at.projx.repository.ProfileRepository;
import com.at.projx.request.model.EducationDetailsRequest;
import com.at.projx.request.model.ExperienceDetailsRequest;
import com.at.projx.request.model.ProfileDetailsRequest;
import com.at.projx.response.model.WebEducationDetails;
import com.at.projx.response.model.WebExperienceDetails;
import com.at.projx.response.model.WebProfileDetails;
import com.at.projx.util.AppUtil;
import com.at.projx.util.ValidationUtil;

@RestController
@RequestMapping(URLConstants.Profile.API_BASE)
public class ProfileController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);
	
	@Autowired
	EmploymentRepository employmentRepository;

	@Autowired
	EducationRepository educationRepository;
	
	@Autowired
	ExperienceRepository experienceRepository;
	
	@Autowired
	ProfileRepository profileRepository;
	
	//=========================================================================

	@GetMapping(URLConstants.Profile.GET_PROFILE)
	public ResponseEntity<Response> getProfile(@PathVariable(value = "userDetailsId") Long userDetailsId, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "getProfile() :";
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
			
			List<ProfileDetails> profiles = profileRepository.getProfile(userDetailsId);
			
			if(profiles != null && !profiles.isEmpty()) {
				if(profiles.size() > 1) {
					//Need to throw an exception as the multiple profile details 
					response = new Response("Multiple Profile Details saved for the userDetailsId :"+userDetailsId, null);
				} else {
					ProfileDetails profileDetails = profiles.get(0);
					WebProfileDetails webProfileDetails = profileDetails.getWebProfileDetails();
					
					if(webProfileDetails != null) {
						List<EmploymentDetails> employments = employmentRepository.findByUserDetailsId(userDetailsId);
						
						if(employments != null && !employments.isEmpty()) {
							if(employments.size() > 1) {
								//Need to throw an error, employee is having multiple employments details
							} else {
								EmploymentDetails employmentDetails = employments.get(0);
								webProfileDetails.setEmployeeId(employmentDetails.getEmployeeId());
								webProfileDetails.setDesignation(employmentDetails.getDesignationDetails().getDesignation());
								webProfileDetails.setDepartment(employmentDetails.getDepartmentDetails().getDepartment());
								webProfileDetails.setReportingToUserName(employmentDetails.getReportingToUserDetails().getFirstName().concat(" ").concat(employmentDetails.getReportingToUserDetails().getLastName()));
								webProfileDetails.setDateOfJoining(employmentDetails.getDateOfJoining());
							}
						} 
						
						List<EducationDetails>  educations = educationRepository.getEducations(Constants.ACTIVE, userDetailsId); //TODO: Need to make it order by
						
						if(educations != null && !educations.isEmpty()) {
							List<WebEducationDetails> webEducations = new ArrayList<>();
							for(EducationDetails details : educations) {
								webEducations.add(details.getWebEducationDetails());
							}
							webProfileDetails.setEducations(webEducations);
						}
						
						List<ExperienceDetails>  experiences = experienceRepository.getExperiences(Constants.ACTIVE, userDetailsId);
						
						if(experiences != null && !experiences.isEmpty()) {
							List<WebExperienceDetails> webExperiences = new ArrayList<>();
							for(ExperienceDetails details : experiences) {
								webExperiences.add(details.getWebExperienceDetails());
							}
							webProfileDetails.setExperiences(webExperiences);
						}
						
						response = new Response("Profile Details", webProfileDetails);
					} else {
						response = new Response("Profile Details not found for the userDetailsId :"+userDetailsId, null);
					}
				} 
			} else {
				response = new Response("Profile Details not found for the userDetailsId :"+userDetailsId, null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while retrieving the Educations, "+userDetailsId;
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}
	
	//=========================================================================
	
	@PostMapping(URLConstants.Profile.SAVE_PROFILE)
	public ResponseEntity<Response> saveProfile(@RequestBody ProfileDetailsRequest profileDetailsRequest, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "saveProfile() :";
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
			String validationResult = ValidationUtil.getInstance().validateProfileDetailsRequest(profileDetailsRequest); 
			
			if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
				return getInvalidInputResponseEntity(validationResult);
			} 
			ProfileDetails profileDetails = populateProfileDetails(profileDetailsRequest); 
			
			if(profileDetailsRequest.getProfileDetailsId() != null && profileDetailsRequest.getProfileDetailsId().longValue() > 0) {
				profileDetails.setProfileDetailsId(profileDetailsRequest.getProfileDetailsId());
			}
			profileRepository.save(profileDetails);
			response = new Response("Profile saved successfully", null);
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while saving the Profile ";
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}
	
	//=========================================================================
	
	private ProfileDetails populateProfileDetails(ProfileDetailsRequest req) {
		ProfileDetails details = new ProfileDetails();
		//Personal Details
		details.setProfileDetailsId(req.getProfileDetailsId());
		details.setGender(req.getGender());
		details.setDateOfBirth(req.getDateOfBirth());
		details.setMaritalStatus(req.getMaritalStatus());
		details.setReligion(req.getReligion());
		details.setNationality(req.getNationality());
		
		//Contact Details
		details.setAddress(req.getAddress());
		details.setCity(req.getCity());
		details.setState(req.getState());
		details.setCountry(req.getCountry());
		details.setPinCode(req.getPinCode());
		details.setPrimaryContactName(req.getPrimaryContactName());
		details.setPrimaryContactRelationship(req.getPrimaryContactRelationship());
		details.setPrimaryContactPhone(req.getPrimaryContactPhone());
		details.setSecondaryContactName(req.getSecondaryContactName());
		details.setSecondaryContactRelationship(req.getSecondaryContactRelationship());
		details.setSecondaryContactPhone(req.getSecondaryContactPhone());
		
		//Bank Details
		details.setBankAccountName(req.getBankAccountName());
		details.setBankAccountNumber(req.getBankAccountNumber());
		details.setIfscCode(req.getIfscCode());
		
		//Authorization Details
		details.setPassportNo(req.getPassportNo());
		details.setPassportExpDate(req.getPassportExpDate());
		details.setPanNo(req.getPanNo());
		details.setAadharNo(req.getAadharNo());
		details.setSsn(req.getSsn());
		UserDetails userDetails = new UserDetails();
		userDetails.setUserDetailsId(req.getUserDetailsId());
		details.setUserDetails(userDetails);
		return details;
	}
	//=========================================================================
	
	@GetMapping(URLConstants.Profile.GET_EDUCATIONS)
	public ResponseEntity<Response> getEducations(@PathVariable(value = "userDetailsId") Long userDetailsId, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "getEducations() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		AuthorizationDetails authorizationDetails = null;
		Response response = null;
		List<WebEducationDetails> webEducations = null;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			if(!authorizationDetails.isValidAuthCode()) {
				return getInvalidAuthCodeResponseEntity(authCode);
			}
			if(!authorizationDetails.isValidAccess()) {
				return getUnauthorizedAccessResponseEntity();
			}
			List<EducationDetails>  educations = educationRepository.getEducations(Constants.ACTIVE, userDetailsId);
			
			if(educations != null && !educations.isEmpty()) {
				webEducations = new ArrayList<>();
				for(EducationDetails details : educations) {
					webEducations.add(details.getWebEducationDetails());
				}
				response = new Response("Educations", webEducations);
			} else {
				response = new Response("Educations not found for the userDetailsId :"+userDetailsId, null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while retrieving the Educations, "+userDetailsId;
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}
	
	//=========================================================================
	
	@PostMapping(URLConstants.Profile.SAVE_EDUCATIONS)
	public ResponseEntity<Response> saveEducations(@RequestBody List<EducationDetailsRequest> educationsReq, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "saveEducations() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		AuthorizationDetails authorizationDetails = null;
		Response response = null;
		List<EducationDetails> educations = null;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			
			if(!authorizationDetails.isValidAuthCode()) {
				return getInvalidAuthCodeResponseEntity(authCode);
			}
			if(!authorizationDetails.isValidAccess()) {
				return getUnauthorizedAccessResponseEntity();
			}
			String validationResult = null;
			if(educationsReq != null && !educationsReq.isEmpty()) {
				educations = new ArrayList<>();
				for(EducationDetailsRequest eduDetailsReq : educationsReq) {
					validationResult = ValidationUtil.getInstance().validateEducationDetails(eduDetailsReq);
					
					if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
						return getInvalidInputResponseEntity(validationResult);
					} 
					if(Constants.INACTIVE.equalsIgnoreCase(eduDetailsReq.getStatus()) && eduDetailsReq.getEducationDetailsId() == null) {
						// No need to save this entry
					} else {
						educations.add(populateEducationDetails(eduDetailsReq));
					}
				}
			} else {
				return getInvalidInputResponseEntity("Educations cannot be null or empty");
			}
			educationRepository.saveAll(educations);
			response = new Response("Educations saved successfully", null);
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while saving the educations ";
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}
	
	//=========================================================================
	
	@GetMapping(URLConstants.Profile.GET_EXPERIENCES)
	public ResponseEntity<Response> getExperiences(@PathVariable(value = "userDetailsId") Long userDetailsId, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "getExperiences() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		AuthorizationDetails authorizationDetails = null;
		Response response = null;
		List<WebExperienceDetails> webExperiences = null;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			if(!authorizationDetails.isValidAuthCode()) {
				return getInvalidAuthCodeResponseEntity(authCode);
			}
			if(!authorizationDetails.isValidAccess()) {
				return getUnauthorizedAccessResponseEntity();
			}
			List<ExperienceDetails>  experiences = experienceRepository.getExperiences(Constants.ACTIVE, userDetailsId);
			
			if(experiences != null && !experiences.isEmpty()) {
				webExperiences = new ArrayList<>();
				for(ExperienceDetails details : experiences) {
					webExperiences.add(details.getWebExperienceDetails());
				}
				response = new Response("Experiences", webExperiences);
			} else {
				response = new Response("Experiences not found for the userDetailsId :"+userDetailsId, null);
			}
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while retrieving the Experiences, "+userDetailsId;
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}
	
	//=========================================================================
	
	@PostMapping(URLConstants.Profile.SAVE_EXPERIENCES)
	public ResponseEntity<Response> saveExperiences(@RequestBody List<ExperienceDetailsRequest> experiencesReq, @RequestParam("authCode") String authCode) throws ProjectXException {
		String logTag = "saveExperiences() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		AuthorizationDetails authorizationDetails = null;
		Response response = null;
		List<ExperienceDetails> experiences = null;
		
		try {
			authorizationDetails = validateAuthorization(authCode);
			
			if(!authorizationDetails.isValidAuthCode()) {
				return getInvalidAuthCodeResponseEntity(authCode);
			}
			if(!authorizationDetails.isValidAccess()) {
				return getUnauthorizedAccessResponseEntity();
			}
			String validationResult = null;
			if(experiencesReq != null && !experiencesReq.isEmpty()) {
				experiences = new ArrayList<>();
				for(ExperienceDetailsRequest expDetailsReq : experiencesReq) {
					validationResult = ValidationUtil.getInstance().validateExperienceDetails(expDetailsReq);
					
					if(!Constants.SUCCESS.equalsIgnoreCase(validationResult)) {
						return getInvalidInputResponseEntity(validationResult);
					} 
					if(Constants.INACTIVE.equalsIgnoreCase(expDetailsReq.getStatus()) && expDetailsReq.getExperienceDetailsId() == null) {
						//No need to save this entry
					} else {
						experiences.add(populateExperienceDetails(expDetailsReq));
					}
				}
			} else {
				return getInvalidInputResponseEntity("Experiences cannot be null or empty");
			}
			experienceRepository.saveAll(experiences);
			response = new Response("Experiences saved successfully", null);
		} catch (Exception e) {
			String exceptionMessage = logTag + "Exception while saving the experiences ";
			handleException(LOGGER, logTag, exceptionMessage, e, authorizationDetails);
		}
		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return getOKResponseEntity(response);
	}
	
	//=========================================================================
	
	private EducationDetails populateEducationDetails(EducationDetailsRequest eduDetailsReq) {
		EducationDetails educationDetails = new EducationDetails();
		educationDetails.setEducationDetailsId(eduDetailsReq.getEducationDetailsId());
		educationDetails.setSubject(eduDetailsReq.getSubject());
		educationDetails.setDegree(eduDetailsReq.getDegree());
		educationDetails.setInstitution(eduDetailsReq.getInstitution());
		educationDetails.setStartingDate(eduDetailsReq.getStartingDate());
		educationDetails.setCompletionDate(eduDetailsReq.getCompletionDate());
		UserDetails userDetails = new UserDetails();
		userDetails.setUserDetailsId(eduDetailsReq.getUserDetailsId()); 
		educationDetails.setUserDetails(userDetails);
		educationDetails.setStatus(eduDetailsReq.getStatus());
		return educationDetails;
	}
	
	//=========================================================================
	
	private ExperienceDetails populateExperienceDetails(ExperienceDetailsRequest expDetailsReq) {
		ExperienceDetails experienceDetails = new ExperienceDetails();
		experienceDetails.setExperienceDetailsId(expDetailsReq.getExperienceDetailsId());
		experienceDetails.setCompanyName(expDetailsReq.getCompanyName());
		experienceDetails.setJobPosition(expDetailsReq.getJobPosition());
		experienceDetails.setLocation(expDetailsReq.getLocation());
		experienceDetails.setPeriodFrom(expDetailsReq.getPeriodFrom());
		experienceDetails.setPeriodTo(expDetailsReq.getPeriodTo());
		UserDetails userDetails = new UserDetails();
		userDetails.setUserDetailsId(expDetailsReq.getUserDetailsId()); 
		experienceDetails.setUserDetails(userDetails);
		experienceDetails.setStatus(expDetailsReq.getStatus());
		return experienceDetails;
	}
	
	//=========================================================================
	
}