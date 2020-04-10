/**
* @author  Jaya Prakash Manne
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

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.at.projx.response.model.WebEmploymentDetails;

@Entity
@Table(name = "employment_details")
@EntityListeners(AuditingEntityListener.class)
public class EmploymentDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employment_details_id", length = 20)
	private Long employmentDetailsId;

	@Column(name = "employee_id", length = 15, nullable = false)
	private String employeeId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_joining", length = 10, nullable = false)
	private Date dateOfJoining;
	
	@OneToOne
	@JoinColumn(name = "department_details_id")
	private DepartmentDetails departmentDetails;
	
	@OneToOne
	@JoinColumn(name = "designation_details_id")
	private DesignationDetails designationDetails;
	
	@OneToOne
	@JoinColumn(name = "user_details_id")
	private UserDetails userDetails;
	
	@OneToOne
	@JoinColumn(name = "reprting_to_user_details_id")
	private UserDetails reportingToUserDetails;

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

	public DepartmentDetails getDepartmentDetails() {
		return departmentDetails;
	}

	public void setDepartmentDetails(DepartmentDetails departmentDetails) {
		this.departmentDetails = departmentDetails;
	}

	public DesignationDetails getDesignationDetails() {
		return designationDetails;
	}

	public void setDesignationDetails(DesignationDetails designationDetails) {
		this.designationDetails = designationDetails;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public UserDetails getReportingToUserDetails() {
		return reportingToUserDetails;
	}

	public void setReportingToUserDetails(UserDetails reportingToUserDetails) {
		this.reportingToUserDetails = reportingToUserDetails;
	}
	
	public WebEmploymentDetails getWebEmploymentDetails() {
		WebEmploymentDetails details = new WebEmploymentDetails();
		details.setEmploymentDetailsId(employmentDetailsId);
		details.setEmployeeId(employeeId);
		details.setDateOfJoining(dateOfJoining);
		details.setUserDetailsId(userDetails.getUserDetailsId());
		details.setDepartmentDetailsId(departmentDetails.getDepartmentDetailsId());
		details.setDepartment(departmentDetails.getDepartment());
		details.setDesignationDetailsId(designationDetails.getDesignationDetailsId());
		details.setDesignation(designationDetails.getDesignation());
		details.setReportingToUserDetailsId(reportingToUserDetails.getUserDetailsId());
		details.setReportingToUser(reportingToUserDetails.getFirstName().concat(" ").concat(reportingToUserDetails.getLastName()));
		return details;
	}
}