package edu.ycp.cs320.PersonalizedCommencementProject.queries;

import java.util.Scanner;

import edu.ycp.cs320.PersonalizedCommencementProject.persist.DatabaseProvider;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.IDatabase;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.InitDatabase;

public class AdminInsertIntoEventDate {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);
		String Date = new String(); 
		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		System.out.println("EnterDate");
		Date = keyboard.next();
		
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		db.InsertIntoEventDate(Date); 
	}
}