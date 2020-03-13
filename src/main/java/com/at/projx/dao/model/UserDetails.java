/**
* @author  Jaya Prakash Manne
* @version 1.0
* @since   01-Nov-2019 
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

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.at.projx.response.model.WebUserDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user_details")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt"}, allowGetters = true)
public class UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_details_id", length = 20)
	private Long userDetailsId;

	@Column(name = "email", length = 150, nullable = false)
	private String email;

	@Column(name = "password", length = 150, nullable = false)
	private String password;
	
	@Column(name = "first_name", length = 150, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 150, nullable = false)
	private String lastName;

	@Column(name = "phone_number", length = 15, nullable = true)
	private String phoneNumber;
	
	@Column(name = "status", length = 1, nullable = false)
	private String status = "1";

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@OneToOne
	@JoinColumn(name = "role_id")
	private RoleDetails roleDetails;
	
	public Long getUserDetailsId() {
		return userDetailsId;
	}

	public void setUserDetailsId(Long userDetailsId) {
		this.userDetailsId = userDetailsId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public RoleDetails getRoleDetails() {
		return roleDetails;
	}

	public void setRoleDetails(RoleDetails roleDetails) {
		this.roleDetails = roleDetails;
	}

	public WebUserDetails getWebUser() {
		WebUserDetails webUser = new WebUserDetails();
		webUser.setUserDetailsId(userDetailsId);
		webUser.setFirstName(firstName);
		webUser.setLastName(lastName);
		webUser.setPhoneNumber(phoneNumber);
		webUser.setEmail(email);
		webUser.setStatus(status);
		webUser.setRoleId(roleDetails.getRoleId());
		return webUser;
	}
}