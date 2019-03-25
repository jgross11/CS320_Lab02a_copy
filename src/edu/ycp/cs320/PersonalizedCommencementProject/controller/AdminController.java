package edu.ycp.cs320.PersonalizedCommencementProject.controller;

import edu.ycp.cs320.PersonalizedCommencementProject.model.Admin;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Advisor;

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
	
    public void addAdvisor(int index, Advisor a) {
    	model.addAdvisor(index, a);
    }
    public void setAdvisors(Advisor[] a) {
    	model.setAdvisors(a);
    }
    
    void setNumAdvisors(int number) {
    	model.setNumAdvisors(number);
    }
    
    public void setDate(int year, int month, int day) {
    	model.setDate(year, month, day);
    }
    
    public void setNumOfCompleteAdvisors(int index) {
    	model.setNumOfCompletedAdvisors(index);
    }
}
