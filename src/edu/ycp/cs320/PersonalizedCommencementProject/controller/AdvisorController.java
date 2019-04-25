package edu.ycp.cs320.PersonalizedCommencementProject.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Admin;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Graduate;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.User;
import edu.ycp.cs320.PersonalizedCommencementProject.model.LoginModel;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.DatabaseProvider;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.DerbyDatabase;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.IDatabase;

public class AdvisorController {
	private IDatabase db = null;
	private Advisor model;

	public AdvisorController() {
		
		// creates DB instance
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}
	
	public void setModel(Advisor model) {
		this.model = model;
	}
	
	public Advisor getModel() {
		return model;
	}
	
	public ArrayList<Graduate> getGraduateByUsername(String username) {
		
		// get the list of users - should only return one
		List<Graduate> graduateList = db.findGraduateByUsername(username);
		ArrayList<Graduate> graduates = null;
		
		if (graduateList.isEmpty()) {
			System.out.println("No graduates found");
			return null;
		}
		else {
			graduates = new ArrayList<Graduate>();
			for (Graduate graduate : graduates) {
				graduates.add(graduate);
			}			
		}
		
		// return found graduate
		return graduates;
	}
}
