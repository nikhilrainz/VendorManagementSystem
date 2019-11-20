package com.ust.model;

public class ContactPerson {
	
	/**** INSTANCE VARIABLE DECLARATION ****/
	private int contactId;
	private String contactName;
	private int vendorId;
	private String department;
	private String email;
	private String phone;
	private String isActiveP;
	
	
	/**** DEFAULT CONTSTRUCTOR ****/
	public ContactPerson()
	{
		
	}

	/**** PARAMETERIZED CONSTRUCTOR ****/
	public ContactPerson(int contactId, String contactName, int vendorId,
			String department, String email, String phone, String isActiveP) {
		super();
		this.contactId = contactId;
		this.contactName = contactName;
		this.vendorId = vendorId;
		this.department = department;
		this.email = email;
		this.phone = phone;
		this.isActiveP = isActiveP;
	}

	/**** SETTERS AND GETTERS ****/
	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIsActiveP() {
		return isActiveP;
	}

	public void setIsActiveP(String isActiveP) {
		this.isActiveP = isActiveP;
	}

	/**** TO STRING USED FOR OVERRIDING FUNCTIONS ****/
	@Override
	public String toString() {
		return "ContactPerson [contactId=" + contactId + ", contactName="
				+ contactName + ", vendorId=" + vendorId + ", department="
				+ department + ", email=" + email + ", phone=" + phone
				+ ", isActiveP=" + isActiveP + "]";
	}
	
	

}
