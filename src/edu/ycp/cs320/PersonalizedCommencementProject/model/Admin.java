package edu.ycp.cs320.PersonalizedCommencementProject.model;

// Admin Model class
/*
 * TODO: Everything
 */
public class Admin extends User{
	private String date;
	
	public Admin() {
		
	}
	
	public Admin(User user) {
		super(user.getUsername(), user.getPassword(), user.getType(), user.getFirstName(), user.getLastName());
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
}
