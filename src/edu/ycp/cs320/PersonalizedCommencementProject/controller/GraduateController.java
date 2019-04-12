package edu.ycp.cs320.PersonalizedCommencementProject.controller;

import edu.ycp.cs320.PersonalizedCommencementProject.miscellaneousClasses.InfoState;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Graduate;

// Graduate controller class
public class GraduateController extends UserController{
	private Graduate model;
	
	public Graduate getModel() {
		return model;
	}
	
	public void setModel(Graduate model) {
		this.model = model;
	}
	
	public void setMajor(String major) {
		model.setMajor(major);
	}
	
	public void setMinor(String minor) {
		model.setMinor(minor);
	}
	
	public void setQRCodeString(String QRCodeString) {
		model.setQRCodeString(QRCodeString);
	}
	
	public void setStatus(boolean status) {
		model.setStatus(status);
	}
	
	public void setAdvisor(Advisor advisor) {
		model.setAdvisor(advisor);
	}
	
	public void setCurrentInfo(InfoState currentInfo) {
		model.setCurrentInfo(currentInfo);
	}
	
	public void setPendingInfo(InfoState pendingInfo) {
		model.setPendingInfo(pendingInfo);
	}
	
	public void calculateStatus() {
		model.calculateStatus();
	}
}
