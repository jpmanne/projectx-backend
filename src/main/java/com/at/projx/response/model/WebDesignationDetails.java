/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.response.model;

public class WebDesignationDetails {
	private Long designationDetailsId;
	private String designation;
	private String description;
	private String status;
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
}