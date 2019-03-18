package edu.ycp.cs320.PersonalizedCommencementProject.controller;

import edu.ycp.cs320.PersonalizedCommencementProject.model.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Graduate;


// Advisor controller class
public class AdvisorController extends UserController{
	private Advisor model;
	
	public Advisor getModel() {
		return model;
	}
	
	public void setModel(Advisor model) {
		this.model = model;
	}
	
	public void setNumGraduates(int numGraduates) {
		model.setNumGraduates(numGraduates);
	}
	
	public void setNumCompletedGraduates(int numCompletedGraduates) {
		model.setNumCompletedGraduates(numCompletedGraduates);
	}
	
	public void setGraduates(Graduate[] graduates) {
		model.setGraduates(graduates);
	}
	
	public void setAcademicInformation(String academicInformation) {
		model.setAcademicInformation(academicInformation);
	}
	
	public void setStatus(boolean status) {
		model.setStatus(status);
	}
	
	public void calculateStatus() {
		model.calculateStatus();
	}
	
	public void calculateNumCompletedGraduates() {
		model.calculateNumCompletedGraduates();
	}
}
