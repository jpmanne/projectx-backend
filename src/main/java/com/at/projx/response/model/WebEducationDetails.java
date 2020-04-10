/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.response.model;

import java.util.Date;

public class WebEducationDetails {
	private Long educationDetailsId;
	private String degree;
	private String subject;
	private String institution;
	private Date startingDate;
	private Date completionDate;
	private String status;
	private Long userDetailsId;
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
	public Long getUserDetailsId() {
		return userDetailsId;
	}
	public void setUserDetailsId(Long userDetailsId) {
		this.userDetailsId = userDetailsId;
	}
}