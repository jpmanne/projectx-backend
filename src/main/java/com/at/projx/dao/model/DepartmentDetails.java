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

import com.at.projx.response.model.WebDepartmentDetails;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "department_details")
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = { "status"}, allowGetters = true)
public class DepartmentDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_details_id", length = 20, nullable = false)
	private Long departmentDetailsId;

	@NotBlank
	@Column(name = "department", length = 50, nullable = false)
	private String department;
	
	@Column(name = "description", length = 255, nullable = true)
	private String description;

	@NotBlank
	@Column(name = "status", length = 1, nullable = false)
	private String status;

	@OneToOne
	@JoinColumn(name = "organization_details_id")
	private OrganizationDetails organizationDetails;

	public Long getDepartmentDetailsId() {
		return departmentDetailsId;
	}

	public void setDepartmentDetailsId(Long departmentDetailsId) {
		this.departmentDetailsId = departmentDetailsId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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
	
	public WebDepartmentDetails getWebDepartmentDetails() {
		WebDepartmentDetails details = new WebDepartmentDetails();
		details.setDepartmentDetailsId(departmentDetailsId);
		details.setDepartment(department);
		details.setDescription(description);
		details.setStatus(status);
		return details;
	}
}