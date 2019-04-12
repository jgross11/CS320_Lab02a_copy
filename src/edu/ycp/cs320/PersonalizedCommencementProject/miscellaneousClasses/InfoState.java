package edu.ycp.cs320.PersonalizedCommencementProject.miscellaneousClasses;

public class InfoState {
	private String formatType;
	private int numContents;
	private ContentComponent[] contents;
	
	public InfoState() {
		
	}
	
	public InfoState(InfoState oldState) {
		formatType = oldState.getFormatType();
		numContents = oldState.getNumContents();
		contents = oldState.getContents();
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
	
	public void setNumContents(int numContents) {
		this.numContents = numContents;
		contents = new ContentComponent[numContents];
	}
	
	public ContentComponent[] getContents() {
		return contents;
	}
	
	public void setContents(ContentComponent[] contents) {
		this.contents = contents;
	}
	
	public ContentComponent getContentAtIndex(int index) {
		return contents[index];
	}
	
	public void setContentAtIndex(int index, ContentComponent content){
		contents[index] = content;
	}
}
