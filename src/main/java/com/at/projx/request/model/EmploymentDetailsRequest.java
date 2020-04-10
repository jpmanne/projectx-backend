/**
* @author  Jaya Prakash Manne
* @version 1.0
* @since   01-Nov-2019 
*/
package com.at.projx.request.model;

import java.util.Date;

public class EmploymentDetailsRequest {
	private Long employmentDetailsId;
	private String employeeId;
	private Date dateOfJoining;
	private Long departmentDetailsId;
	private Long designationDetailsId;
	private Long userDetailsId;
	private Long reportingToUserDetailsId;

	public Long getEmploymentDetailsId() {
		return employmentDetailsId;
	}
	public void setEmploymentDetailsId(Long employmentDetailsId) {
		this.employmentDetailsId = employmentDetailsId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public Long getDepartmentDetailsId() {
		return departmentDetailsId;
	}
	public void setDepartmentDetailsId(Long departmentDetailsId) {
		this.departmentDetailsId = departmentDetailsId;
	}
	public Long getDesignationDetailsId() {
		return designationDetailsId;
	}
	public void setDesignationDetailsId(Long designationDetailsId) {
		this.designationDetailsId = designationDetailsId;
	}
	public Long getUserDetailsId() {
		return userDetailsId;
	}
	public void setUserDetailsId(Long userDetailsId) {
		this.userDetailsId = userDetailsId;
	}
	public Long getReportingToUserDetailsId() {
		return reportingToUserDetailsId;
	}
	public void setReportingToUserDetailsId(Long reportingToUserDetailsId) {
		this.reportingToUserDetailsId = reportingToUserDetailsId;
	}
}