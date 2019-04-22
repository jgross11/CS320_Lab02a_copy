package edu.ycp.cs320.PersonalizedCommencementProject.queries;

import java.util.List;
import java.util.Scanner;

import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.DatabaseProvider;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.IDatabase;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.InitDatabase;

public class AnAdvisorQuery {
	// find advisor by this username
	private final static String userToFind = "agrove9";
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		List<Advisor> advisorList = db.findAdvisorByUsername(userToFind);
		
		// check if anything was returned and output the list
		if (advisorList.isEmpty()) {
			System.out.println("There are no users in the database");
		}
		else {
			for (Advisor advisor : advisorList) {
				System.out.println("name: " + advisor.getName());
				System.out.println("password: " + advisor.getPassword());
				System.out.println("type: " + advisor.getType());
				System.out.println("image: " + advisor.getImage());
				System.out.println("academic information: " + advisor.getAcademicInformation());
				System.out.println("status: " + advisor.getStatus());
			}
		}
	}
}
