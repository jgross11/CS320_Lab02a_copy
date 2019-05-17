package edu.ycp.cs320.PersonalizedCommencementProject.databaseModel;

import java.sql.Date;

public class Admin extends User{
private long deadline; 
	
	public Admin() {
		
	}

	public Admin(User user) {
		super(user.getUsername(), user.getPassword(), user.getType(), user.getFirstName(), user.getLastName(), user.getImage());
	}
	
	public void setDate(long date) {
		deadline = date; 
	}

	public void setDate(Date date) {
		deadline = date.getTime();
	}
	public long getDate() {
		return deadline; 
	}
	
	public Date getDateAsDate() {
		return new Date(deadline);
	}
}
