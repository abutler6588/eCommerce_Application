package com.example.demo.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Rubric - implement a password based authentication scheme
 * You should also add some requirements and validation, as well as a confirm
 * field in the request, to make sure they didn't make a typo.
 * Create password for the user
 * */


public class CreateUserRequest {

	@JsonProperty
	private String username;

	@JsonProperty
	private String password;

	@JsonProperty
	private String confirmPassword;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
