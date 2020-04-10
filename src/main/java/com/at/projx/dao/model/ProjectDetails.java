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
@Table(name = "project_details")
@EntityListeners(AuditingEntityListener.class)
public class ProjectDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_details_id", length = 20)
	private Long projectDetailsId;

	@Column(name = "project_name", length = 150, nullable = false)
	private String projectName;
	
	@Column(name = "description", length = 255, nullable = true)
	private String description;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", length = 10, nullable = false)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", length = 10, nullable = false)
	private Date endDate;
	
	@Column(name = "status", length = 1, nullable = false)
	private String status;
	
	@OneToOne
	@JoinColumn(name = "client_details_id")
	private ClientDetails clientDetails;

	public Long getProjectDetailsId() {
		return projectDetailsId;
	}

	public void setProjectDetailsId(Long projectDetailsId) {
		this.projectDetailsId = projectDetailsId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ClientDetails getClientDetails() {
		return clientDetails;
	}

	public void setClientDetails(ClientDetails clientDetails) {
		this.clientDetails = clientDetails;
	}
}