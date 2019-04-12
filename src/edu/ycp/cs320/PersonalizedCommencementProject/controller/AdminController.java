package edu.ycp.cs320.PersonalizedCommencementProject.controller;

import edu.ycp.cs320.PersonalizedCommencementProject.model.Admin;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Advisor;

// Admin Controller class

public class AdminController {
	private Admin model; 
	
	public Admin getModel() {
		return model;
	}
	
    public void setModel(Admin model) {
		this.model = model; 
	}
    
    public void setDate(long date) {
    	model.setDate(date);
    }
}
