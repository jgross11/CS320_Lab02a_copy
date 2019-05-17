package edu.ycp.cs320.PersonalizedCommencementProject.databaseModel;

import java.util.ArrayList;

public class InfoState {
	public static final int PROFILE_INDEX = 0;
	public static final String PROFILE = "profilePicture";
	public static final int EXTRAINFORMATION_INDEX = 1;
	public static final String EXTRAINFORMATION = "extraInformation";
	public static final int NAMEPRONUNCIATION_INDEX = 2;
	public static final String NAMEPRONUNCIATION = "namePronunciation";
	public static final int SLIDESHOW1_INDEX = 3;
	public static final String SLIDESHOW1 = "slideshow1";
	public static final int SLIDESHOW2_INDEX = 4;
	public static final String SLIDESHOW2 = "slideshow2";
	public static final int SLIDESHOW3_INDEX = 5;
	public static final String SLIDESHOW3 = "slideshow3";
	public static final int SLIDESHOW4_INDEX = 6;
	public static final String SLIDESHOW4 = "slideshow4";
	public static final int VIDEO_INDEX = 7;
	public static final String VIDEO = "video";
	private final int numContents = 8;
	private String formatType;
	private String username;
	private String layout;
	private ArrayList<ContentComponent> contents;
	
	public InfoState() {
		contents = new ArrayList<ContentComponent>();
		for(int i = 0; i < numContents; i++) {
			contents.add(new ContentComponent());
		}
	}
	
	public InfoState(InfoState oldState) {
		formatType = oldState.getFormatType();
		contents = oldState.getContents();
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFormatType() {
		return formatType;
	}
	
	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}
	
	public int getNumContents() {
		return numContents;
	}
	
	public ArrayList<ContentComponent> getContents() {
		return contents;
	}
	
	public void setContents(ArrayList<ContentComponent> contents) {
		this.contents = contents;
	}
	
	public ContentComponent getContentAtIndex(int index) {
		return contents.get(index);
	}
	
	public void setContentAtIndex(int index, ContentComponent content){
		contents.set(index, content);
	}
	
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
}
