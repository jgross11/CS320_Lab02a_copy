package edu.ycp.cs320.PersonalizedCommencementProject.model;


// Advisor model class
public class Advisor extends User{
	private Graduate[] graduates;
	private int numGraduates, numCompletedGraduates;
	private boolean status;
	private String academicInformation;
	
	public Advisor() {
		
	}
	
	public Advisor(User user) {
		super(user.getUsername(), user.getPassword(), user.getType(), user.getFirstName(), user.getLastName());
	}
	
	public int getNumGraduates() {
		return numGraduates;
	}
	
	public void setNumGraduates(int numGraduates) {
		this.numGraduates = numGraduates;
	}
	
	public int getNumCompletedGraduates() {
		return numCompletedGraduates;
	}
	
	public void setNumCompletedGraduates(int numCompletedGraduates) {
		this.numCompletedGraduates = numCompletedGraduates;
	}
	
	public Graduate[] getGraduates() {
		return graduates;
	}
	
	public void setGraduates(Graduate[] graduates) {
		this.graduates = graduates;
	}
	
	public void addGraduate(int index, Graduate graduate) {
		if(index >= 0 && graduates.length > 0 && index < graduates.length) {
			graduates[index] = graduate;
		}
		else {
			if(index < 0) {
				System.err.println("Received negative index argument");
			}
			else if(graduates.length <= 0) {
				System.err.println("Graduate array length is <= 0");
			}
			else if(index >= graduates.length){
				System.err.println("Received index argument larger than graduate array length");
			}
			else {
				System.err.println("Unknown error when adding graduate to advisor graduate array");
			}
		}
	}
	
	public Graduate getGraduate(int index) {
		if(index >= 0 && graduates.length > 0 && index < graduates.length) {
			return graduates[index];
		}
		else {
			if(index < 0) {
				System.err.println("Received negative index argument");
				throw new IllegalArgumentException();
			}
			else if(graduates.length <= 0) {
				System.err.println("Graduate array length is <= 0");
				throw new IllegalArgumentException();
			}
			else if(index >= graduates.length){
				System.err.println("Received index argument larger than graduate array length");
				throw new IllegalArgumentException();
			}
			else {
				System.err.println("Unknown error when adding graduate to advisor graduate array");
				throw new IllegalArgumentException();
			}
		}
	}
	
	public String getAcademicInformation() {
		return academicInformation;
	}
	
	public void setAcademicInformation(String academicInformation) {
		this.academicInformation = academicInformation;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void calculateStatus() {
		status = false;
		calculateNumCompletedGraduates();
		if(numCompletedGraduates == this.graduates.length) {
			status = true;
		}
	}
	
	public void calculateNumCompletedGraduates() {
		numCompletedGraduates = 0;
		for(Graduate grad : graduates) {
			if(grad.getStatus() == true) {
				numCompletedGraduates++;
			}
		}
	}
	
}
