package edu.ycp.cs320.PersonalizedCommencementProject.databaseModel;

import java.sql.Date;

public class Admin extends User{
private Date deadline; 
	
	public Admin() {
		
	}
	
	public Admin(User user) {
		super(user.getUsername(), user.getPassword(), user.getType(), user.getFirstName(), user.getLastName());
	}
	
	public void setDate(long date) {
		deadline = new Date(date); 
	}

	public Date getDate() {
		return deadline; 
	}
}
