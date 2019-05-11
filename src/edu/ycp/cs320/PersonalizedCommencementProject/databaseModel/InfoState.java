package edu.ycp.cs320.PersonalizedCommencementProject.databaseModel;

import java.util.ArrayList;

public class InfoState {
	private String formatType;
	private final int numContents = 7;
	private int UserID; 
	private ArrayList<ContentComponent> contents;
	
	public InfoState() {
		contents = new ArrayList<ContentComponent>(numContents);
	}
	
	public InfoState(InfoState oldState) {
		formatType = oldState.getFormatType();
		contents = oldState.getContents();
	}
	
	public int getUserId() {
		return this.UserID;
	}
	
	public void setUserID(int x) {
		this.UserID = x; 
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
}
