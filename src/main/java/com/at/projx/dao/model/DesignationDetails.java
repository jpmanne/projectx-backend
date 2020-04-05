/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.at.projx.response.model.WebDesignationDetails;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "designation_details")
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = { "status"}, allowGetters = true)
public class DesignationDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "designation_details_id", length = 20, nullable = false)
	private Long designationDetailsId;

	@NotBlank
	@Column(name = "designation", length = 50, nullable = false)
	private String designation;
	
	@Column(name = "description", length = 255, nullable = true)
	private String description;

	@NotBlank
	@Column(name = "status", length = 1, nullable = false)
	private String status;

	@OneToOne
	@JoinColumn(name = "organization_details_id")
	private OrganizationDetails organizationDetails;

	public Long getDesignationDetailsId() {
		return designationDetailsId;
	}

	public void setDesignationDetailsId(Long designationDetailsId) {
		this.designationDetailsId = designationDetailsId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OrganizationDetails getOrganizationDetails() {
		return organizationDetails;
	}

	public void setOrganizationDetails(OrganizationDetails organizationDetails) {
		this.organizationDetails = organizationDetails;
	}
	
	public WebDesignationDetails getWebDesignationDetails() {
		WebDesignationDetails details = new WebDesignationDetails();
		details.setDesignationDetailsId(designationDetailsId);
		details.setDesignation(designation);
		details.setDescription(description);
		details.setStatus(status);
		return details;
	}
}