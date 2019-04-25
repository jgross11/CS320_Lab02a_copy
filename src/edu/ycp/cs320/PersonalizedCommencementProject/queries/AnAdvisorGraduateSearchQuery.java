package edu.ycp.cs320.PersonalizedCommencementProject.queries;

import java.util.List;
import java.util.Scanner;

import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Admin;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Graduate;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.DatabaseProvider;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.IDatabase;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.InitDatabase;

public class AnAdvisorGraduateSearchQuery {
	// find advisor's graduates by advisor's username
	private final static String userToFind = "agrove9";
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		List<Graduate> graduateList = db.findAdvisorGraduatesByAdvisorUsername(userToFind);
		
		// check if anything was returned and output the list
		if (graduateList.isEmpty()) {
			System.out.println("There are no users in the database");
		}
		else {
			for (Graduate graduate : graduateList) {
				System.out.println("name: " + graduate.getName());
				System.out.println("password: " + graduate.getPassword());
				System.out.println("type: " + graduate.getType());
				System.out.println("image: " + graduate.getImage());
			}
		}
	}
}