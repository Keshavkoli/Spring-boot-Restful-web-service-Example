package com.javatpoint.model;

public class FamilyMembers {

	public String fatherName;
	public String motherName;
	public String brotherName;
	public String sisterName;
	public String grandmotherName;

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getBrotherName() {
		return brotherName;
	}

	public void setBrotherName(String brotherName) {
		this.brotherName = brotherName;
	}

	public String getSisterName() {
		return sisterName;
	}

	public void setSisterName(String sisterName) {
		this.sisterName = sisterName;
	}

	public String getGrandmotherName() {
		return grandmotherName;
	}

	public void setGrandmotherName(String grandmotherName) {
		this.grandmotherName = grandmotherName;
	}

	public FamilyMembers(String fatherName, String motherName, String brotherName, String sisterName,
			String grandmotherName) {
		super();
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.brotherName = brotherName;
		this.sisterName = sisterName;
		this.grandmotherName = grandmotherName;
	}

	public FamilyMembers() {
		super();
	}

	@Override
	public String toString() {
		return "FamilyMembers [fatherName=" + fatherName + ", motherName=" + motherName + ", brotherName=" + brotherName
				+ ", sisterName=" + sisterName + ", grandmotherName=" + grandmotherName + "]";
	}

}
