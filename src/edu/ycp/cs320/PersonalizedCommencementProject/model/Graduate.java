package edu.ycp.cs320.PersonalizedCommencementProject.model;

public class Graduate extends User{
	private String major, minor, QRCodeString;
	//private InfoState pendInfo, currInfo;
	//private Advisor advisor;
	private boolean status;
	
	public Graduate() {
		
	}
	
	public Graduate(User userModel) {
		super(userModel.getUsername(), userModel.getPassword(), userModel.getType(), userModel.getFirstName(), userModel.getLastName());
	}
	
	public String getMajor() {
		return this.major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getMinor() {
		return this.minor;
	}
	public void setMinor(String minor) {
		this.minor = minor;
	}
	public String getQRCodeString() {
		return this.QRCodeString;
	}
	public void setQRCodeString(String QRCodeString) {
		this.QRCodeString = QRCodeString;
	}
	public boolean getStatus() {
		return this.status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
