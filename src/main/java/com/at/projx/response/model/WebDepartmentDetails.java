/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.response.model;

public class WebDepartmentDetails {
	private Long departmentDetailsId;
	private String department;
	private String description;
	private String status;

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
}