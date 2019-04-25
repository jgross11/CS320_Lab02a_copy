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
		
		// get the list of users - should only return one
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
		
		// get the list of graduates - should only return one
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
	
	public ArrayList<Advisor> getAdvisorByUsername(String username) {
		
		// get the list of advisors - should only return one
		List<Advisor> advisorList = db.findAdvisorByUsername(username);
		ArrayList<Advisor> advisors = null;
		
		if (advisorList.isEmpty()) {
			System.out.println("No advisors found");
			return null;
		}
		else {
			advisors = new ArrayList<Advisor>();
			for (Advisor advisor : advisorList) {
				advisors.add(advisor);
			}			
		}
		
		// return found advisor
		return advisors;
	}
	
	public ArrayList<Admin> getAdminByUsername(String username) {
		
		// get the list of admins - should only return one
		List<Admin> adminList = db.findAdminByUsername(username);
		ArrayList<Admin> admins = null;
		
		if (adminList.isEmpty()) {
			System.out.println("No admins found");
			return null;
		}
		else {
			admins = new ArrayList<Admin>();
			for (Admin admin : adminList) {
				admins.add(admin);
			}			
		}
		
		// return found admin
		return admins;
	}
	
	public ArrayList<Graduate> getAdvisorGraduatesByAdvisorUsername(String username) {
		
		// get the list of an advisor's graduates
		List<Graduate> graduateList = db.findAdvisorGraduatesByAdvisorUsername(username);
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
		
		// return found graduates
		return graduates;
	}
}
