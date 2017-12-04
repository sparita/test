package com.epam.dto;

import java.io.Serializable;

/**
 * Class to hold user username and password in plain text.
 * 
 * @author suresh
 *
 */
public class Credentials implements Serializable {
	
	private static final long serialVersionUID = -6888698690615403331L;
	private String username;
	private String password;
	
	public void setUsername(final String username) {
		this.username = username;
	}
	public void setPassword(final String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
}
