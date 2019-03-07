package edu.ycp.cs320.PersonalizedCommencementProject.controller;

import edu.ycp.cs320.PersonalizedCommencementProject.model.User;

public class UserController {
	private User model;
	public void setModel(User model) {
		this.model = model;
	}
	public User getModel() {
		return this.model;
	}
	
	public void setUsername(String username) {
		model.setUsername(username);
	}
	public void setPassword(String password) {
		model.setPassword(password);
	}
	public void setType(String type) {
		model.setType(type);
	}
	public void setFirstName(String firstName) {
		model.setFirstName(firstName);
	}
	public void setLastName(String lastName) {
		model.setLastName(lastName);
	}
}
