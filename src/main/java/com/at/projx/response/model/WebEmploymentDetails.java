/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.response.model;

import java.util.Date;

public class WebEmploymentDetails {
	private Long employmentDetailsId;
	private String employeeId;
	private Date dateOfJoining;
	private Long departmentDetailsId;
	private String department;
	private Long designationDetailsId;
	private String designation;
	private Long reportingToUserDetailsId;
	private String reportingToUser;
	private Long userDetailsId;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
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
	public Long getReportingToUserDetailsId() {
		return reportingToUserDetailsId;
	}
	public void setReportingToUserDetailsId(Long reportingToUserDetailsId) {
		this.reportingToUserDetailsId = reportingToUserDetailsId;
	}
	public String getReportingToUser() {
		return reportingToUser;
	}
	public void setReportingToUser(String reportingToUser) {
		this.reportingToUser = reportingToUser;
	}
	public Long getUserDetailsId() {
		return userDetailsId;
	}
	public void setUserDetailsId(Long userDetailsId) {
		this.userDetailsId = userDetailsId;
	}
}