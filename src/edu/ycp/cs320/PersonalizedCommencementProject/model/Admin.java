package edu.ycp.cs320.PersonalizedCommencementProject.model;

import java.sql.Date;


// Admin Model class
/*
 * TODO: Everything
 */
public class Admin extends User{

	private Advisor[] Advisors; 
	private int num_Advisors, num_completedAdvisors; 
	private Date Deadline; 

	
	public Admin() {
		
	}
	
	public Admin(User user) {
		super(user.getUsername(), user.getPassword(), user.getType(), user.getFirstName(), user.getLastName());
	}
	

	//
	public void addAdvisor(int index, Advisor advisor) {
		if(index >= 0 && Advisors.length > 0 && index < Advisors.length) {
			Advisors[index] = advisor;
		}
		else {
			if(index < 0) {
				System.err.println("Received negative index argument");
			}
			else if(Advisors.length <= 0) {
				System.err.println("Advisor array length is <= 0");
			}
			else if(index >= Advisors.length){
				System.err.println("Received index argument larger than Advsior array length");
			}
			else {
				System.err.println("Unknown error when adding Advisor to Admin Advisor array");
			}
		}
	}
	
	//
	public void setAdvisors (Advisor[] a) {
	this.Advisors = a; 
	}
	
	//
	public Advisor getCertainAdvisor(int index) {
		return Advisors[index]; 
		
	}
	
	//
	public Advisor[] getAdvisors() {
		return Advisors; 
	}
	
	//
	public void setNumAdvisors(int index) {
		num_Advisors = index; 
	}
	
	//
	public int getNumAdvisors() {
		return num_Advisors; 
	}
	
	//
	public void setNumOfCompletedAdvisors(int index) {
		num_completedAdvisors = index; 
	}
	
	//
	public int getNumOfCompletedAdvisors() {
		
		return num_completedAdvisors; 
	}
	
	//
	@SuppressWarnings("deprecation")
	public void setDate(int year, int month, int day) {
		Deadline = new Date(year ,month, day); 
	}
	
public Date getDate() {
		
		return Deadline; 
	}
}
