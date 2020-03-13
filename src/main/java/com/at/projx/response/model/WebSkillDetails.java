/**
* @author  Jaya Prakash Manne
* @version 1.0
* @since   01-Nov-2019 
*/
package com.at.projx.response.model;

public class WebSkillDetails {
	private Long skillDetailsId;
	private String skill;
	private String experience;
	
	public Long getSkillDetailsId() {
		return skillDetailsId;
	}

	public void setSkillDetailsId(Long skillDetailsId) {
		this.skillDetailsId = skillDetailsId;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}
}