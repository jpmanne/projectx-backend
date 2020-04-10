/**
* @author  Jaya Prakash Manne
*/
package com.at.projx.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "client_details")
@EntityListeners(AuditingEntityListener.class)
public class ClientDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_details_id", length = 20)
	private Long clientDetailsId;

	@Column(name = "client_name", length = 150, nullable = false)
	private String clientName;
	
	@Column(name = "description", length = 255, nullable = true)
	private String description;
	
	@Column(name = "status", length = 1, nullable = false)
	private String status;
	
	@OneToOne
	@JoinColumn(name = "organization_details_id")
	private OrganizationDetails organizationDetails;

	public Long getClientDetailsId() {
		return clientDetailsId;
	}

	public void setClientDetailsId(Long clientDetailsId) {
		this.clientDetailsId = clientDetailsId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
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

	public OrganizationDetails getOrganizationDetails() {
		return organizationDetails;
	}

	public void setOrganizationDetails(OrganizationDetails organizationDetails) {
		this.organizationDetails = organizationDetails;
	}
}