package com.ust.model;

public class Login {
	
	/**** INSTANCE VARIABLE DECLERATION ****/
	private int userId;
	private String username;
	private String password;
	
	/**** DEFAULT CONSTRUCTOR ****/
	public Login()
	{
		
	}

	/**** PARAMETERIZED CONSTRUCTOR ****/
	public Login(int userId, String username, String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
	}

	/**** GETTERS AND SETTERS ****/
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**** TO STRING FOR OVERRIDING FUNCTION ****/
	@Override
	public String toString() {
		return "Login [userId=" + userId + ", username=" + username
				+ ", password=" + password + "]";
	}

}
