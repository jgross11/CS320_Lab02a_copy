package edu.ycp.cs320.PersonalizedCommencementProject.persist;

/*
 * This file is heavily based off of example code provided by <DONALD J. HAKE II>
 * Thanks.
 */

public class DatabaseProvider {
	private static IDatabase theInstance;
	
	public static void setInstance(IDatabase db) {
		theInstance = db;
	}
	
	public static IDatabase getInstance() {
		if (theInstance == null) {
			throw new IllegalStateException("IDatabase instance has not been set!");
		}
		return theInstance;
	}
}
