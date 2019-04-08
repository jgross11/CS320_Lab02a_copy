package edu.ycp.cs320.PersonalizedCommencementProject.model;

public class User {
	private String username, password, firstName, lastName, type;
	public User() {

	}
	public User(String username, String password, String type, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.type = type;
		this.firstName = firstName;
		this.lastName = lastName;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getName() {
		return firstName + " " + lastName;
	}
}
