package edu.ycp.cs320.PersonalizedCommencementProject.databaseModel;

public class ContentComponent {
private boolean status;
	
	// photo / video: path to file as a string
	// text: text (string)
	private String content;
	
	public ContentComponent() {

	}
	
	public ContentComponent(String content) {
		this.content = content;
		status = false;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
