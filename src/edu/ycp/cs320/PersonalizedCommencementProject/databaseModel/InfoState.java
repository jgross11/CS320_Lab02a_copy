package edu.ycp.cs320.PersonalizedCommencementProject.databaseModel;
public class InfoState {
	private String formatType;
	private final int numContents = 7;
	private ContentComponent[] contents;
	
	public InfoState() {
		contents = new ContentComponent[numContents];
	}
	
	public InfoState(InfoState oldState) {
		formatType = oldState.getFormatType();
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
