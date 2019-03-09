package edu.ycp.cs320.PersonalizedCommencementProject.controller;

import edu.ycp.cs320.PersonalizedCommencementProject.model.Graduate;

public class GraduateController extends UserController{
	private Graduate model;
	
	public void setModel(Graduate model) {
		this.model = model;
	}
	public Graduate getModel() {
		return this.model;
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
}
