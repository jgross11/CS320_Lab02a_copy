package edu.ycp.cs320.PersonalizedCommencementProject.model;

// Admin Model class
/*
 * TODO: Everything
 */
public class Admin extends User{
	
	public Admin() {
		
	}
	
	public Admin(User user) {
		super(user.getUsername(), user.getPassword(), user.getType(), user.getFirstName(), user.getLastName());
	}
}
