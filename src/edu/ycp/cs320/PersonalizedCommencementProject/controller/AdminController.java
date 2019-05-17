package edu.ycp.cs320.PersonalizedCommencementProject.controller;

import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Admin;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.DatabaseProvider;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.DerbyDatabase;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.IDatabase;

// Admin Controller class

public class AdminController {
	private Admin model; 
	private IDatabase db = null;
	
	public AdminController() {
		// creates DB instance
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();	
	}
	
	public Admin getModel() {
		return model;
	}
	
    public void setModel(Admin model) {
		this.model = model; 
	}
    
    public void setDate(long date) {
    	model.setDate(date);
    	db.insertDateIntoAdminTable(date);
    }
}
