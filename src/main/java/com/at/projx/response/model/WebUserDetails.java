package com.at.projx.response.model;

import org.springframework.util.StringUtils;

public class WebUserDetails {
	private Long userDetailsId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String status;
	private Long roleId;
	private Long organizationDetailsId;
	
	public String getFullName() {
		StringBuilder fullNameBuilder = new StringBuilder();
		if (!StringUtils.isEmpty(firstName)) {
			fullNameBuilder.append(firstName);
		}
		if (!StringUtils.isEmpty(lastName)) {
			if (fullNameBuilder.length() > 0) {
				fullNameBuilder.append(" ");
			}
			fullNameBuilder.append(lastName);
		}
		return fullNameBuilder.toString();
	}

	public Long getUserDetailsId() {
		return userDetailsId;
	}

	public void setUserDetailsId(Long userDetailsId) {
		this.userDetailsId = userDetailsId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getOrganizationDetailsId() {
		return organizationDetailsId;
	}

	public void setOrganizationDetailsId(Long organizationDetailsId) {
		this.organizationDetailsId = organizationDetailsId;
	}
}
