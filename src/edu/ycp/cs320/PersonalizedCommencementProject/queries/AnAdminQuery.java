package edu.ycp.cs320.PersonalizedCommencementProject.queries;

import java.util.List;
import java.util.Scanner;

import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Admin;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.DatabaseProvider;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.IDatabase;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.InitDatabase;

public class AnAdminQuery {
	// find admin by this username
	private final static String userToFind = "jgross11";
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		List<Admin> adminList = db.findAdminByUsername(userToFind);
		
		// check if anything was returned and output the list
		if (adminList.isEmpty()) {
			System.out.println("There are no users in the database");
		}
		else {
			for (Admin admin : adminList) {
				System.out.println("name: " + admin.getName());
				System.out.println("password: " + admin.getPassword());
				System.out.println("type: " + admin.getType());
				System.out.println("image: " + admin.getImage());
				System.out.println("event date: " + admin.getDate());
			}
		}
	}
}