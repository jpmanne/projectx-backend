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

import com.at.projx.response.model.WebExperienceDetails;

@Entity
@Table(name = "experience_details")
@EntityListeners(AuditingEntityListener.class)
public class ExperienceDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "experience_details_id", length = 20)
	private Long experienceDetailsId;

	@Column(name = "company_name", length = 150, nullable = false)
	private String companyName;
	
	@Column(name = "job_position", length = 150, nullable = false)
	private String jobPosition;
	
	@Column(name = "location", length = 150, nullable = false)
	private String location;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "period_from", length = 10, nullable = false)
	private Date periodFrom;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "period_to", length = 10, nullable = false)
	private Date periodTo;
	
	@Column(name = "status", length = 1, nullable = false)
	private String status;
	
	@OneToOne
	@JoinColumn(name = "user_details_id")
	private UserDetails userDetails;

	public Long getExperienceDetailsId() {
		return experienceDetailsId;
	}

	public void setExperienceDetailsId(Long experienceDetailsId) {
		this.experienceDetailsId = experienceDetailsId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getPeriodFrom() {
		return periodFrom;
	}

	public void setPeriodFrom(Date periodFrom) {
		this.periodFrom = periodFrom;
	}

	public Date getPeriodTo() {
		return periodTo;
	}

	public void setPeriodTo(Date periodTo) {
		this.periodTo = periodTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	
	public WebExperienceDetails getWebExperienceDetails() {
		WebExperienceDetails details = new WebExperienceDetails();
		details.setExperienceDetailsId(experienceDetailsId);
		details.setCompanyName(companyName);
		details.setJobPosition(jobPosition);
		details.setLocation(location);
		details.setPeriodFrom(periodFrom);
		details.setPeriodTo(periodTo);
		details.setStatus(status);
		details.setUserDetailsId(userDetails.getUserDetailsId()); 
		return details;
	}
}