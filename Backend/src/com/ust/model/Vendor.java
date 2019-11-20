package com.ust.model;

public class Vendor {
	
	/**** INSTANCE VARIABLE DECLARATION ****/
	private int vendorId;
	private String vendorName;
	private String address;
	private String location;
	private String service;
	private int pincode;
	private String isActiveV;
	
	/**** DEFAULT CONTSTRUCTOR ****/
	public Vendor()
	{
		
	}

	/**** PARAMETERIZED CONSTRUCTOR ****/
	public Vendor(int vendorId, String vendorName, String address,
			String location, String service, int pincode, String isActiveV) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.address = address;
		this.location = location;
		this.service = service;
		this.pincode = pincode;
		this.isActiveV = isActiveV;
	}

	/**** GETTERS AND SETTERS ****/
	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getIsActiveV() {
		return isActiveV;
	}

	public void setIsActiveV(String isActiveV) {
		this.isActiveV = isActiveV;
	}

	/**** TO STRING FOR OVERRIDING FUNCTION ****/
	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", vendorName=" + vendorName
				+ ", address=" + address + ", location=" + location
				+ ", service=" + service + ", pincode=" + pincode
				+ ", isActiveV=" + isActiveV + "]";
	}
	
}
