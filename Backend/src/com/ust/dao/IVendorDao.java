package com.ust.dao;

import java.util.List;

import com.ust.model.Login;
import com.ust.model.VendorContact;

public interface IVendorDao {

	/**************************** Login ****************************/
	public abstract Login verifyUserLogin(String username, String password);

	/**************************** View Vendor Details ****************************/
	public abstract List<VendorContact> vendorList();

	/**************************** Insert Vendor Details ****************************/
	public abstract int insertVendor(VendorContact vendorBean);

	/**************************** Update Vendor Details ****************************/
	public abstract int updateVendor(VendorContact vendorBean);

	/**************************** Get Vendor Details By VendorId ****************************/
	public abstract List<VendorContact> getVendorDetailsById(int vendorId);

	/**************************** Search For a Vendor using location, name and service ****************************/
	public abstract List<VendorContact> getVendor(String searchString);

	/**************************** Disable a vendor using vendorId ****************************/
	public abstract int disableVendor(int vendorId);

}