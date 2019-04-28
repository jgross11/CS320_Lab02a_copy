package edu.ycp.cs320.PersonalizedCommencementProject.databaseModel;

public class ContentComponent {
	private boolean status;
	
	// photo / video: path to file as a string
	// text: text (string)
	private String content;
	private String username;
	private String infoStateType;
	private String type;
	
	public ContentComponent() {

	}
	
	public ContentComponent(String content) {
		this.content = content;
		status = false;
	}
	
	public ContentComponent(String path, Boolean status) {
		this.content = path;
		this.status = status;
	}

	public ContentComponent(String path, Boolean status, String type, String username) {
		this.content = path;
		this.status = status;
		this.type = type;
		this.username = username;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getInfoStateType() {
		return infoStateType;
	}

	public void setInfoStateType(String infoStateType) {
		this.infoStateType = infoStateType;
	}
}
