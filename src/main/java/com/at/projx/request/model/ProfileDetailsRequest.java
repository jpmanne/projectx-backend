/**
* @author  Jaya Prakash Manne
* @version 1.0
* @since   01-Nov-2019 
*/
package com.at.projx.request.model;

import java.util.Date;

public class ProfileDetailsRequest {
	private Long profileDetailsId;
	//Personal Details
	private String gender;
	private Date dateOfBirth;
	private String maritalStatus;
	private String religion;
	private String nationality;
	
	//Contact Details
	private String address;
	private String city;
	private String state;
	private String country;
	private String pinCode;
	private String primaryContactName;
	private String primaryContactRelationship;
	private String primaryContactPhone;
	private String secondaryContactName;
	private String secondaryContactRelationship;
	private String secondaryContactPhone;
	
	//Bank Details
	private String bankAccountName;
	private String bankAccountNumber;
	private String ifscCode;
	
	//Authorization Details
	private String passportNo;
	private Date passportExpDate;
	private String panNo;
	private String aadharNo;
	private String ssn;
	private Long userDetailsId;
	
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
	public Long getUserDetailsId() {
		return userDetailsId;
	}
	public void setUserDetailsId(Long userDetailsId) {
		this.userDetailsId = userDetailsId;
	}
}