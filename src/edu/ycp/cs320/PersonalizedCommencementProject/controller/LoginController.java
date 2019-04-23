package edu.ycp.cs320.PersonalizedCommencementProject.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Graduate;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.User;
import edu.ycp.cs320.PersonalizedCommencementProject.model.LoginModel;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.DatabaseProvider;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.DerbyDatabase;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.IDatabase;

public class LoginController {
	private IDatabase db = null;
	private LoginModel model;

	public LoginController() {
		
		// creates DB instance
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}
	
	public void setModel(LoginModel model) {
		this.model = model;
	}
	
	public LoginModel getModel() {
		return model;
	}
	
	public ArrayList<User> getUserByUsername(String username) {
		
		// get the list of (Author, Book) pairs from DB
		List<User> userList = db.findUserByUsername(username);
		ArrayList<User> users = null;
		
		if (userList.isEmpty()) {
			System.out.println("No users found");
			return null;
		}
		else {
			users = new ArrayList<User>();
			for (User user : userList) {
				users.add(user);
			}			
		}
		
		// return found user
		return users;
	}

	public ArrayList<Graduate> getGraduateByUsername(String username) {
		
		// get the list of (Author, Book) pairs from DB
		List<Graduate> graduateList = db.findGraduateByUsername(username);
		ArrayList<Graduate> graduates = null;
		
		if (graduateList.isEmpty()) {
			System.out.println("No graduates found");
			return null;
		}
		else {
			graduates = new ArrayList<Graduate>();
			for (Graduate graduate : graduateList) {
				graduates.add(graduate);
			}			
		}
		
		// return found graduate
		return graduates;
	}
}
