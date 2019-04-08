package edu.ycp.cs320.PersonalizedCommencementProject.controller;

import java.util.Date;

import edu.ycp.cs320.PersonalizedCommencementProject.model.Admin;


// Admin Controller class
/*
 * TODO: Everything
 */
public class AdminController {
	private Admin model;

	public Admin getModel() {
		return model;
	}
	
	public void setModel(Admin model) {
		this.model = model;
	}
	
	public void setEndDate(Date date) {
		model.setEndDate(date);
	}
}
