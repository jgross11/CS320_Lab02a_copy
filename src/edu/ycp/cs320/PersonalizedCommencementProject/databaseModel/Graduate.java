package edu.ycp.cs320.PersonalizedCommencementProject.databaseModel;

public class Graduate extends User{
	private String major, minor, QRCodeString;
	private InfoState pendingInfo, currentInfo;
	private String advisor;
	private boolean status;
	
	public Graduate() {
		
	}
	
	public Graduate(User userModel) {
		super(userModel.getUsername(), userModel.getPassword(), userModel.getType(), userModel.getFirstName(), userModel.getLastName(), userModel.getImage());
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
	
	public String getAdvisor() {
		return advisor;
	}
	
	public void setAdvisor(String advisor) {
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
		for(ContentComponent content : pendingInfo.getContents()) {
			if(!content.getStatus()) {
				status = false;
				break;
			}
		}
	}
	public int getProfileIndex() {
		return InfoState.PROFILE_INDEX;
	}
	public int getExtraInformationIndex() {
		return InfoState.EXTRAINFORMATION_INDEX;
	}
	public int getNamePronunciationIndex() {
		return InfoState.NAMEPRONUNCIATION_INDEX;
	}
	public int getSlideshow1Index() {
		return InfoState.SLIDESHOW1_INDEX;
	}
	public int getSlideshow2Index() {
		return InfoState.SLIDESHOW2_INDEX;
	}
	public int getSlideshow3Index() {
		return InfoState.SLIDESHOW3_INDEX;
	}
	public int getSlideshow4Index() {
		return InfoState.SLIDESHOW4_INDEX;
	}
	public int getVideoIndex() {
		return InfoState.VIDEO_INDEX;
	}
}
