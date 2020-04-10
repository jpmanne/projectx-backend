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

@Entity
@Table(name = "project_assignment_details")
@EntityListeners(AuditingEntityListener.class)
public class ProjectAssignmentsDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_assignment_details_id", length = 20)
	private Long projectAssignmentDetailsId;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", length = 10, nullable = false)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", length = 10, nullable = true)
	private Date endDate;
	
	@OneToOne
	@JoinColumn(name = "user_details_id")
	private UserDetails userDetails;
	
	@OneToOne
	@JoinColumn(name = "project_details_id")
	private ProjectDetails projectDetails;
	
	@Column(name = "status", length = 1, nullable = false)
	private String status;

	public Long getProjectAssignmentDetailsId() {
		return projectAssignmentDetailsId;
	}

	public void setProjectAssignmentDetailsId(Long projectAssignmentDetailsId) {
		this.projectAssignmentDetailsId = projectAssignmentDetailsId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public ProjectDetails getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}