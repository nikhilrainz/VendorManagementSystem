package com.ust.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ust.dao.IVendorDao;
import com.ust.model.Login;
import com.ust.model.VendorContact;

@RestController
public class VendorController {

	@Autowired
	IVendorDao vendorDaoImpl;

	/**************************** Login ****************************/
	@RequestMapping(value = "/api/login/{username}/{password}", method = RequestMethod.GET)
	public Login selectRoleName(@PathVariable("username") String username,
								@PathVariable("password") String password) 
	{
			return vendorDaoImpl.verifyUserLogin(username, password);
	}
	
	/**************************** Display all Vendors ****************************/
	@RequestMapping(value = "/api/vendor", method = RequestMethod.GET)
	public List<VendorContact> getAllVendors() 
	{
		List list = vendorDaoImpl.vendorList();
		return list;
	}
	
	/**************************** Insert Vendor Details ****************************/
	@RequestMapping(value = "/api/saveVendor", method = { RequestMethod.POST,RequestMethod.PUT })
	public void vendorInsert(@RequestBody VendorContact vendBean)
	{
		System.out.println(vendBean.getVendorId());
			if(vendBean.getVendorId() !=0)
			{
				vendorDaoImpl.updateVendor(vendBean);
			}
			else
			{
				vendorDaoImpl.insertVendor(vendBean);
			}
	}
	
	/**************************** View Vendor Details by vendor Id ****************************/
	@RequestMapping(value = "/api/viewVendor/{vendorId}", 
				method = RequestMethod.GET,
				headers = "Accept=application/json")
	public VendorContact getVendorDetailsById(@ModelAttribute("objVendor") VendorContact objVendor,@PathVariable("vendorId") int vendorId)
	{
		List list = vendorDaoImpl.getVendorDetailsById(vendorId);
		objVendor=(VendorContact) list.get(0);
		return objVendor;
	}
	
	/**************************** Search for a vendor using search string ****************************/
	@RequestMapping(value = "/api/searchVendor/{searchString}", method = RequestMethod.GET)
	public List<VendorContact> searchVendorById(@PathVariable("searchString") String searchString)
	{
		List list = vendorDaoImpl.getVendor(searchString);
		return list;
	}
	
	/**************************** Disable a Vendor using vendor Id ****************************/
	@RequestMapping(value = "/api/disableVendor/{vendorId}", method = RequestMethod.PUT)
	void vendorDisable(@PathVariable("vendorId") int vendorId) {
		vendorDaoImpl.disableVendor(vendorId);
	}
}
