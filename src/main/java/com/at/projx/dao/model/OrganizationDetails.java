/**
* @author  Jaya Prakash Manne
* @version 1.0
*/
package com.at.projx.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "organization_details")
@EntityListeners(AuditingEntityListener.class)
public class OrganizationDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "organization_details_id", length = 20, nullable = false)
	private Long organizationDetailsId;

	@Column(name = "organization_code", length = 32, nullable = false)
	private String organizationCode;
	
	@Column(name = "organization_name", length = 150, nullable = false)
	private String organizationName;

	@Column(name = "website", length = 150, nullable = true)
	private String website;

	@Column(name = "phone_number", length = 15, nullable = true)
	private String phoneNumber;
	
	@Column(name = "no_of_employees", length = 10, nullable = false)
	private String noOfEmployees;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date", length = 10, nullable = false)
	private Date createdDate;
	
	@Column(name = "status", length = 1, nullable = false)
	private String status;

	public Long getOrganizationDetailsId() {
		return organizationDetailsId;
	}

	public void setOrganizationDetailsId(Long organizationDetailsId) {
		this.organizationDetailsId = organizationDetailsId;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNoOfEmployees() {
		return noOfEmployees;
	}

	public void setNoOfEmployees(String noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}