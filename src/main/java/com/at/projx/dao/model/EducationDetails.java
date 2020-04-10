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

import com.at.projx.response.model.WebEducationDetails;

@Entity
@Table(name = "education_details")
@EntityListeners(AuditingEntityListener.class)
public class EducationDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "education_details_id", length = 20)
	private Long educationDetailsId;

	@Column(name = "degree", length = 100, nullable = false)
	private String degree;
	
	@Column(name = "subject", length = 100, nullable = false)
	private String subject;
	
	@Column(name = "institution", length = 150, nullable = false)
	private String institution;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "starting_date", length = 10, nullable = false)
	private Date startingDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "completion_date", length = 10, nullable = false)
	private Date completionDate;
	
	@Column(name = "status", length = 1, nullable = false)
	private String status;
	
	@OneToOne
	@JoinColumn(name = "user_details_id")
	private UserDetails userDetails;

	public Long getEducationDetailsId() {
		return educationDetailsId;
	}

	public void setEducationDetailsId(Long educationDetailsId) {
		this.educationDetailsId = educationDetailsId;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
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
	
	public WebEducationDetails getWebEducationDetails() {
		WebEducationDetails details = new WebEducationDetails();
		details.setEducationDetailsId(educationDetailsId);
		details.setSubject(subject);
		details.setDegree(degree);
		details.setInstitution(institution);
		details.setCompletionDate(completionDate);
		details.setStartingDate(startingDate);
		details.setStatus(status);
		details.setUserDetailsId(userDetails.getUserDetailsId());
 		return details;
	}
}