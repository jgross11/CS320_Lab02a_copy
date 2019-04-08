package edu.ycp.cs320.PersonalizedCommencementProject.model;

import java.util.Date;

// Admin Model class
/*
 * TODO: Everything
 */
public class Admin extends User{
	private Date date;
	
	public Admin() {
		
	}
	
	public Admin(User user) {
		super(user.getUsername(), user.getPassword(), user.getType(), user.getFirstName(), user.getLastName());
	}
	
	public Date getEndDate() {
		return date;
	}
	
	public void setEndDate(Date date) {
		this.date = date;
	}
	
}
