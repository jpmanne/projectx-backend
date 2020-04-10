/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.dao.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.at.projx.response.model.WebProfileDetails;

@Entity
@Table(name = "profile_details")
@EntityListeners(AuditingEntityListener.class)
public class ProfileDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profile_details_id", length = 20)
	private Long profileDetailsId;

	//Personal Details
	@Column(name = "gender", length = 1, nullable = false)
	private String gender;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth", length = 10, nullable = false)
	private Date dateOfBirth;
	
	@Column(name = "marital_status", length = 1, nullable = true)
	private String maritalStatus;
	
	@Column(name = "religion", length = 100, nullable = true)
	private String religion;
	
	@Column(name = "nationality", length = 100, nullable = true)
	private String nationality;
	
	//Contact Details
	@Column(name = "address", length = 255, nullable = false)
	private String address;
	
	@Column(name = "city", length = 100, nullable = false)
	private String city;
	
	@Column(name = "state", length = 100, nullable = false)
	private String state;
	
	@Column(name = "country", length = 100, nullable = false)
	private String country;
	
	@Column(name = "pin_code", length = 15, nullable = true)
	private String pinCode;
	
	@Column(name = "primary_contact_name", length = 150, nullable = true)
	private String primaryContactName;
	
	@Column(name = "primary_contact_relationship", length = 50, nullable = true)
	private String primaryContactRelationship;
	
	@Column(name = "primary_contact_phone", length = 15, nullable = true)
	private String primaryContactPhone;
	
	@Column(name = "secondary_contact_name", length = 150, nullable = true)
	private String secondaryContactName;
	
	@Column(name = "secondary_contact_relationship", length = 50, nullable = true)
	private String secondaryContactRelationship;
	
	@Column(name = "secondary_contact_phone", length = 15, nullable = true)
	private String secondaryContactPhone;
	
	//Bank Details
	@Column(name = "bank_account_name", length = 150, nullable = true)
	private String bankAccountName;
	
	@Column(name = "bank_account_number", length = 50, nullable = true)
	private String bankAccountNumber;
	
	@Column(name = "ifsc_code", length = 15, nullable = true)
	private String ifscCode;
	
	//Authorization Details
	@Column(name = "passport_no", length = 15, nullable = true)
	private String passportNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "passport_exp_date", length = 10, nullable = false)
	private Date passportExpDate;
	
	@Column(name = "pan_no", length = 15, nullable = true)
	private String panNo;
	
	@Column(name = "aadhar_no", length = 15, nullable = true)
	private String aadharNo;
	
	@Column(name = "ssn", length = 15, nullable = true)
	private String ssn;
	
	@OneToOne
	@JoinColumn(name = "user_details_id")
	private UserDetails userDetails;

	public Long getProfileDetailsId() {
		return profileDetailsId;
	}

	public void setProfileDetailsId(Long profileDetailsId) {
		this.profileDetailsId = profileDetailsId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getPrimaryContactName() {
		return primaryContactName;
	}

	public void setPrimaryContactName(String primaryContactName) {
		this.primaryContactName = primaryContactName;
	}

	public String getPrimaryContactRelationship() {
		return primaryContactRelationship;
	}

	public void setPrimaryContactRelationship(String primaryContactRelationship) {
		this.primaryContactRelationship = primaryContactRelationship;
	}

	public String getPrimaryContactPhone() {
		return primaryContactPhone;
	}

	public void setPrimaryContactPhone(String primaryContactPhone) {
		this.primaryContactPhone = primaryContactPhone;
	}

	public String getSecondaryContactName() {
		return secondaryContactName;
	}

	public void setSecondaryContactName(String secondaryContactName) {
		this.secondaryContactName = secondaryContactName;
	}

	public String getSecondaryContactRelationship() {
		return secondaryContactRelationship;
	}

	public void setSecondaryContactRelationship(String secondaryContactRelationship) {
		this.secondaryContactRelationship = secondaryContactRelationship;
	}

	public String getSecondaryContactPhone() {
		return secondaryContactPhone;
	}

	public void setSecondaryContactPhone(String secondaryContactPhone) {
		this.secondaryContactPhone = secondaryContactPhone;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public Date getPassportExpDate() {
		return passportExpDate;
	}

	public void setPassportExpDate(Date passportExpDate) {
		this.passportExpDate = passportExpDate;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	
	public WebProfileDetails getWebProfileDetails() {
		WebProfileDetails details = new WebProfileDetails();
		details.setProfileDetailsId(profileDetailsId);
		details.setGender(gender);
		details.setDateOfBirth(dateOfBirth);
		details.setMaritalStatus(maritalStatus);
		details.setReligion(religion);
		details.setNationality(nationality);
		
		details.setAddress(address);
		details.setCity(city);
		details.setState(state);
		details.setCountry(country);
		details.setPinCode(pinCode);
		
		details.setPrimaryContactName(primaryContactName);
		details.setPrimaryContactRelationship(primaryContactRelationship);
		details.setPrimaryContactPhone(primaryContactPhone);
		details.setSecondaryContactName(secondaryContactName);
		details.setSecondaryContactRelationship(secondaryContactRelationship);
		details.setSecondaryContactPhone(secondaryContactPhone);
		
		details.setBankAccountName(bankAccountName);
		details.setBankAccountNumber(bankAccountNumber);
		details.setIfscCode(ifscCode);
		
		details.setPassportNo(passportNo);
		details.setPassportExpDate(passportExpDate);
		details.setPanNo(panNo);
		details.setAadharNo(aadharNo);
		details.setSsn(ssn);
		
		details.setUserDetailsId(userDetails.getUserDetailsId());
		details.setEmail(userDetails.getEmail());
		details.setPhoneNumber(userDetails.getPhoneNumber());
		details.setFullName(userDetails.getFirstName().concat(" ").concat(userDetails.getLastName())); 
		
		return details;
	}
}