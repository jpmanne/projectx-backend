/**
* @author  Jaya Prakash Manne
* @version 1.0
* @since   01-Nov-2019 
*/
package com.at.projx.model;

public class OptionDetails {
	private int id;
	private String value;
	public int getId() {
		return id;
	}
	public String getValue() {
		return value;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public OptionDetails(int id, String value) {
		super();
		this.id = id;
		this.value = value;
	}
}
