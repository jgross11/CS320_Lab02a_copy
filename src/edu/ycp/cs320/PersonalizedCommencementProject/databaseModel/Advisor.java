package edu.ycp.cs320.PersonalizedCommencementProject.databaseModel;

import java.util.ArrayList;

public class Advisor extends User{
	private ArrayList<Graduate> graduates, pendingGraduates, completedGraduates;
	private int numGraduates, numCompletedGraduates;
	private boolean status;
	private String academicInformation;
	
	public Advisor() {
		graduates = new ArrayList<Graduate>();
	}
	
	public Advisor(User user) {
		super(user.getUsername(), user.getPassword(), user.getType(), user.getFirstName(), user.getLastName(), user.getImage());
		graduates = new ArrayList<Graduate>();
	}
	
	public int getNumGraduates() {
		return numGraduates;
	}
	
	public void setNumGraduates(int numGraduates) {
		this.numGraduates = numGraduates;
	}
	
	public int getNumCompletedGraduates() {
		return numCompletedGraduates;
	}
	
	public void setNumCompletedGraduates(int numCompletedGraduates) {
		this.numCompletedGraduates = numCompletedGraduates;
	}
	
	public ArrayList<Graduate> getGraduates() {
		return graduates;
	}
	
	public void setGraduates(ArrayList<Graduate> graduates) {
		this.graduates = graduates;
	}
	
	public void addGraduate(Graduate graduate) {
		graduates.add(graduate);
	}
	
	public void addGraduate(int index, Graduate graduate) {
		graduates.add(index, graduate);
	}
	
	public Graduate getGraduate(int index) {
		return graduates.get(index);
	}
	
	public String getAcademicInformation() {
		return academicInformation;
	}
	
	public void setAcademicInformation(String academicInformation) {
		this.academicInformation = academicInformation;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void calculateStatus() {
		status = false;
		calculateNumCompletedGraduates();
		if(numCompletedGraduates == graduates.size()) {
			status = true;
		}
	}
	// TODO: rewrite this function to work for arraylists
	public void generatePendingAndCompletedGraduateList() {
		/*calculateNumCompletedGraduates();
		completedGraduates = new ArrayList<Graduate>();
		pendingGraduates = new ArrayList<Graduate>();
		int pendingCounter = 0;
		int completedCounter = 0;
		for(Graduate grad : graduates) {
			if(grad.getStatus() == true) {
				completedGraduates[completedCounter] = grad;
				completedCounter++;
			}
			else {
				pendingGraduates[pendingCounter] = grad;
				pendingCounter++;
			}
		}
		*/
		completedGraduates = new ArrayList<Graduate>();
		pendingGraduates = new ArrayList<Graduate>();
		for(Graduate graduate : graduates) {
			if(graduate.getStatus() == true) {
				completedGraduates.add(graduate);
			}
			else {
				pendingGraduates.add(graduate);
			}
		}
	}
	
	public void calculateNumCompletedGraduates() {
		numCompletedGraduates = 0;
		for(Graduate grad : graduates) {
			if(grad.getStatus() == true) {
				numCompletedGraduates++;
			}
		}
	}
	
	public ArrayList<Graduate> getCompletedGraduates() {
		return completedGraduates;
	}
	
	public ArrayList<Graduate> getPendingGraduates() {
		return pendingGraduates;
	}
}
