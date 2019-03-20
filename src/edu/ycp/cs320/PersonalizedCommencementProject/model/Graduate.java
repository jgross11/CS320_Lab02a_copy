package edu.ycp.cs320.PersonalizedCommencementProject.model;

import edu.ycp.cs320.PersonalizedCommencementProject.miscellaneousClasses.ContentComponent;
import edu.ycp.cs320.PersonalizedCommencementProject.miscellaneousClasses.InfoState;

// Graduate model class
public class Graduate extends User{
	private String major, minor, QRCodeString;
	private InfoState pendingInfo, currentInfo;
	private Advisor advisor;
	private boolean status;
	
	public Graduate() {
		
	}
	
	public Graduate(User userModel) {
		super(userModel.getUsername(), userModel.getPassword(), userModel.getType(), userModel.getFirstName(), userModel.getLastName());
	}
	
	public String getMajor() {
		return major;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	
	public String getMinor() {
		return minor;
	}
	
	public void setMinor(String minor) {
		this.minor = minor;
	}
	
	public String getQRCodeString() {
		return QRCodeString;
	}
	
	public void setQRCodeString(String QRCodeString) {
		this.QRCodeString = QRCodeString;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Advisor getAdvisor() {
		return advisor;
	}
	
	public void setAdvisor(Advisor advisor) {
		this.advisor = advisor;
	}
	
	public InfoState getCurrentInfo() {
		return currentInfo;
	}
	
	public void setCurrentInfo(InfoState currentInfo) {
		this.currentInfo = currentInfo;
	}
	
	public InfoState getPendingInfo() {
		return pendingInfo;
	}
	
	public void setPendingInfo(InfoState pendingInfo) {
		this.pendingInfo = pendingInfo;
	}
	
	public void calculateStatus() {
		status = true;
		for(ContentComponent content : currentInfo.getContents()) {
			if(!content.getStatus()) {
				status = false;
				break;
			}
		}
	}
}
