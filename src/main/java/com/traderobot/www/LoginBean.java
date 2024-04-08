package com.traderobot.www;


import jakarta.enterprise.context.SessionScoped;

import jakarta.inject.Named;

@Named
public class LoginBean {

	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String validateUserLogin() {
		String navResult = "";
		System.out.println("Entered Username is= " + userName + ", password is= " + password);
		if (userName.equalsIgnoreCase("javacodegeeks") && password.equals("access123")) {
			navResult = "success";
		} else {
			navResult = "failure";
		}
		return navResult;
	}
}