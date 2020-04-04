/**
* @author  Jaya Prakash Manne
* @version 1.0
*/
package com.at.projx.request.model;

import java.io.Serializable;

public class BasicSetupRequest implements Serializable {
	private static final long serialVersionUID = -9017991349826302604L;
	private String noOfEmployees;
	private String workEmail;
	private String firstName;
	private String lastName;
	private String organizationName;
	private String workPhone;
	
	public String getNoOfEmployees() {
		return noOfEmployees;
	}
	public void setNoOfEmployees(String noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}
	public String getWorkEmail() {
		return workEmail;
	}
	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
}
