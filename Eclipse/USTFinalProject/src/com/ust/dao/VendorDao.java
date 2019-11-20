package com.ust.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ust.model.Login;
import com.ust.model.VendorContact;

public class VendorDao implements IVendorDao {

	/**** JDBC TEMPLATE DECLARATION ****/
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	/**************************** Login ****************************/
	/* (non-Javadoc)
	 * @see com.ust.dao.IVendorDao#verifyUserLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public Login verifyUserLogin(String username, String password) {

		String sql = "select userId from loginTable where username= ? and password = ?";
		return template.queryForObject(sql, new Object[] {username,password},
				new BeanPropertyRowMapper<Login>(Login.class));
	}

	/**************************** View Vendor Details ****************************/
	/* (non-Javadoc)
	 * @see com.ust.dao.IVendorDao#vendorList()
	 */
	@Override
	public List<VendorContact> vendorList() 
	{
		return template.query("select vendorId,vendorName,address,location,service,pincode,contactId,"
				+ "contactName,department,email,phone,isActiveV,isActiveP from vendorTable join personTable using(vendorId)"
				+ "where isActiveV = 'Yes' order by vendorId desc",
						new RowMapper<VendorContact>() 
						{
							public VendorContact mapRow(ResultSet rs,
									int row) throws SQLException {
								
								VendorContact vendor = new VendorContact();
								vendor.setVendorId(rs.getInt(1));
								vendor.setVendorName(rs.getString(2));
								vendor.setAddress(rs.getString(3));
								vendor.setLocation(rs.getString(4));
								vendor.setService(rs.getString(5));
								vendor.setPincode(rs.getInt(6));
								vendor.setContactId(rs.getInt(7));
								vendor.setContactName(rs.getString(8));
								vendor.setDepartment(rs.getString(9));
								vendor.setEmail(rs.getString(10));
								vendor.setPhone(rs.getString(11));
								vendor.setIsActiveV(rs.getString(12));
								vendor.setIsActiveP(rs.getString(13));
								return vendor;
						}
					});
	}
	
	/**************************** Insert Vendor Details ****************************/
	/* (non-Javadoc)
	 * @see com.ust.dao.IVendorDao#insertVendor(com.ust.model.VendorContact)
	 */
	@Override
	public int insertVendor(VendorContact vendorBean)
	{
		String statusV = "Yes";
		String sql = "insert into vendorTable(vendorName,address,location,service,pincode,isActiveV)"
				+ "values(?,?,?,?,?,?)";
		template.update(sql,new Object[] {vendorBean.getVendorName(),vendorBean.getAddress(),
				vendorBean.getLocation(),vendorBean.getService(),vendorBean.getPincode(),statusV});
		
		String sql2 = "select max(vendorId) from vendorTable";
		int vendorId = template.queryForObject(sql2,Integer.class);
		
		String statusP = "Yes";
		String sql3 = "insert into personTable(contactName,vendorId,department,email,phone,isActiveP)"
				+ "values(?,?,?,?,?,?)";
		return template.update(sql3,new Object[] {vendorBean.getContactName(),vendorId,
				vendorBean.getDepartment(),vendorBean.getEmail(),vendorBean.getPhone(),statusP});
	}
	
	/**************************** Update Vendor Details ****************************/
	/* (non-Javadoc)
	 * @see com.ust.dao.IVendorDao#updateVendor(com.ust.model.VendorContact)
	 */
	@Override
	public int updateVendor(VendorContact vendorBean) 
	{
		String sql = "update vendorTable set vendorName = '"+vendorBean.getVendorName()+
										"',address = '"+vendorBean.getAddress()+
										"',location = '"+vendorBean.getLocation()+
										"',service = '"+vendorBean.getService()+
										"',pincode = "+vendorBean.getPincode()+""
										+ "where vendorId = "+vendorBean.getVendorId()+"";
		template.update(sql, new Object[] {});
		
		String sql1 = "update personTable set contactName = '"+vendorBean.getContactName()+
										"',department = '"+vendorBean.getDepartment()+
										"',email = '"+vendorBean.getEmail()+
										"',phone = '"+vendorBean.getPhone()+
										"' where vendorId = "+vendorBean.getVendorId()+"";
		return template.update(sql1, new Object[] {});
	}
	
	/**************************** Get Vendor Details By VendorId ****************************/
	/* (non-Javadoc)
	 * @see com.ust.dao.IVendorDao#getVendorDetailsById(int)
	 */
	@Override
	public List<VendorContact> getVendorDetailsById(int vendorId) 
	{
		return template.query("select vendorId,vendorName,address,location,service,pincode,contactId,contactName,department,"
				+ "email,phone,isActiveV,isActiveP from vendorTable join personTable using(vendorId) where vendorId = "
				+vendorId+""
				,new RowMapper<VendorContact>(){
				public VendorContact mapRow(ResultSet rs,int row) throws SQLException
				{
					VendorContact objVendor=new VendorContact();
					objVendor.setVendorId(rs.getInt(1));
					objVendor.setVendorName(rs.getString(2));
					objVendor.setAddress(rs.getString(3));
					objVendor.setLocation(rs.getString(4));
					objVendor.setService(rs.getString(5));
					objVendor.setPincode(rs.getInt(6));
					objVendor.setContactId(rs.getInt(7));
					objVendor.setContactName(rs.getString(8));
					objVendor.setDepartment(rs.getString(9));
					objVendor.setEmail(rs.getString(10));
					objVendor.setPhone(rs.getString(11));
					objVendor.setIsActiveV(rs.getString(12));
					objVendor.setIsActiveP(rs.getString(13));
					return objVendor;
				}
			});
	}
	
	/**************************** Search For a Vendor using location, name and service ****************************/
	/* (non-Javadoc)
	 * @see com.ust.dao.IVendorDao#getVendor(java.lang.String)
	 */
	@Override
	public List<VendorContact> getVendor(String searchString) 
	{
		return template.query("select vendorId,vendorName,address,location,service,pincode,contactId,contactName,department"
				+ ",email,phone from vendorTable join personTable using(vendorId)"
				+ "where location Like '"+searchString+"%' or vendorName Like '"+searchString+"%'"
				+"or contactName Like '"+searchString+"%'or service Like '"+searchString+"%'", 
				new RowMapper<VendorContact>() 
				{
					public VendorContact mapRow(ResultSet rs, int row)throws SQLException 
					{
						VendorContact objVendorContact = new VendorContact();
						objVendorContact.setVendorId(rs.getInt(1));
						objVendorContact.setVendorName(rs.getString(2));;
						objVendorContact.setAddress(rs.getString(3));
						objVendorContact.setLocation(rs.getString(4));
						objVendorContact.setService(rs.getString(5));
						objVendorContact.setPincode(rs.getInt(6));
						objVendorContact.setContactId(rs.getInt(7));
						objVendorContact.setContactName(rs.getString(8));
						objVendorContact.setDepartment(rs.getString(9));
						objVendorContact.setEmail(rs.getString(10));
						objVendorContact.setPhone(rs.getString(11));
						return objVendorContact;
					}
				});
	}
	
	/**************************** Disable a vendor using vendorId ****************************/
	/* (non-Javadoc)
	 * @see com.ust.dao.IVendorDao#disableVendor(int)
	 */
	@Override
	public int disableVendor(int vendorId) 
	{
		String sql = "update vendorTable set isActiveV='No' where vendorId = ?";
		return template.update(sql, new Object[] { vendorId });
	}
}
