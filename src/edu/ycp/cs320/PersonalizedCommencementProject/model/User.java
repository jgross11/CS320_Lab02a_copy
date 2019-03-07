package edu.ycp.cs320.PersonalizedCommencementProject.model;

public class User {
	private String username, password, firstName, lastName, type;
	public User() {

	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public User(String username, String password, String type) {
		this.username = username;
		this.password = password;
		this.type = type;
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
