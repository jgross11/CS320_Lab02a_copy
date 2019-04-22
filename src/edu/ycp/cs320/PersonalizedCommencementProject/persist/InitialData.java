package edu.ycp.cs320.PersonalizedCommencementProject.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Admin;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.ContentComponent;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Graduate;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.InfoState;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.User;


/*
import edu.ycp.cs320.PersonalizedCommencementProject.model.ZUNUSED_Author;
import edu.ycp.cs320.PersonalizedCommencementProject.model.ZUNUSED_Book;
import edu.ycp.cs320.PersonalizedCommencementProject.model.ZUNUSED_BookAuthor;
*/

/*
 * This file is heavily based off of example code provided by <DONALD J. HAKE II>
 * Thanks.
 */

public class InitialData {
	
	private static List<User> globalUsersList;
	private static List<Graduate> globalGraduatesList;
	// reads initial User data from CSV file and returns a List of Users
	public static List<User> getUsers() throws IOException {
		List<User> userList = new ArrayList<User>();
		ReadCSV readUsers = new ReadCSV("users.csv");
		try {
			while (true) {
				List<String> tuple = readUsers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				User user = new User();
				user.setUsername(i.next());
				user.setPassword(i.next());
				user.setFirstName(i.next());
				user.setLastName(i.next());
				user.setType(i.next());
				user.setImage(i.next());
				userList.add(user);
			}
			System.out.println("userList loaded from CSV file");
			globalUsersList = userList;
			return userList;
		} finally {
			readUsers.close();
		}
	}
	
	// reads initial Graduate data from CSV file and returns a List of Graduates
	public static List<Graduate> getGraduates() throws IOException {
		List<Graduate> graduateList = new ArrayList<Graduate>();
		ReadCSV readGraduates = new ReadCSV("graduates.csv");
		try {

			while (true) {
				List<String> tuple = readGraduates.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Graduate graduate = null; 
				
				// obtain graduate username
				String username = i.next();
				
				// search user list for Users with the same username
				for(User user : globalUsersList) {
					
					// found graduate's user information, transfer to graduate class 
					if(user.getUsername().equals(username)) {
						graduate = new Graduate(user);
					}
				}
				// check if graduate's info was found
				if(graduate == null) {
					System.err.println(". . . . GRADUATE INFO NOT FOUND. . . .");
				}
				graduate.setMajor(i.next());
				graduate.setAdvisor(i.next());
				graduate.setStatus(i.next().equals("ready"));
				graduateList.add(graduate);
			}
			System.out.println("GraduateList loaded from CSV file");			
			globalGraduatesList = graduateList;
			return graduateList;
		} finally {
			readGraduates.close();
		}
	}
	
	// reads initial Advisor data from CSV file and returns a List of Advisors
	public static List<Advisor> getAdvisors() throws IOException {
		List<Advisor> advisorList = new ArrayList<Advisor>();
		ReadCSV readAdvisors = new ReadCSV("advisors.csv");
		try {
			
			while (true) {
				List<String> tuple = readAdvisors.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Advisor advisor = null; 
				
				// obtain advisor username
				String username = i.next();
				
				// search user list for Users with the same username
				for(User user : globalUsersList) {
					
					// found advisor's user information, transfer to advisor class 
					if(user.getUsername().equals(username)) {
						advisor = new Advisor(user);
					}
				}
				// check if advisor's info was found
				if(advisor == null) {
					System.err.println(". . . . ADVISOR INFO NOT FOUND. . . .");
				}
				advisor.setAcademicInformation(i.next());
				advisor.setStatus(Boolean.parseBoolean(i.next()));
				
				// iterate through graduate list and add those with this advisor
				for(Graduate graduate : globalGraduatesList) {
					if(graduate.getAdvisor().equals(username)) {
						advisor.addGraduate(graduate);
					}
				}
				advisorList.add(advisor);
			}
			System.out.println("AdvisorList loaded from CSV file");			
			return advisorList;
		} finally {
			readAdvisors.close();
		}
	}
	
	// reads initial Advisor data from CSV file and returns a List of Advisors
	public static List<Admin> getAdmins() throws IOException {
		List<Admin> adminList = new ArrayList<Admin>();
		ReadCSV readAdmins = new ReadCSV("admins.csv");
		try {
			
			while (true) {
				List<String> tuple = readAdmins.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Admin admin = null; 
				
				// obtain admin username
				String username = i.next();
				
				// search user list for Users with the same username
				for(User user : globalUsersList) {
					
					// found admin's user information, transfer to admin class 
					if(user.getUsername().equals(username)) {
						admin = new Admin(user);
					}
				}
				// check if admin's info was found
				if(admin == null) {
					System.err.println(". . . . ADMIN INFO NOT FOUND. . . .");
				}
				admin.setDate(Long.parseLong(i.next()));
				adminList.add(admin);
			}
			System.out.println("AdminList loaded from CSV file");		
			return adminList;
		} finally {
			readAdmins.close();
		}
	}
	
	// reads initial InfoState data from CSV file and returns a List of InfoStates
	public static List<InfoState> getInfoStates() throws IOException {
		List<InfoState> infoStateList = new ArrayList<InfoState>();
		ReadCSV readInfoStates = new ReadCSV("infoStates.csv");
		try {
			
			while (true) {
				List<String> tuple = readInfoStates.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				InfoState infoState = null; 
				
				// obtain infoState graduates' username
				String username = i.next();
				
				// search graduate list for Graduate with the same username
				for(Graduate grad : globalGraduatesList) {
					
					// found graduate, set infostate
					if(grad.getUsername().equals(username)) {
						infoState = new InfoState();
						infoState.setUsername(username);
						// obtain and check infostate's type
						String type = i.next();
						System.out.println(type);
						if(type.equals("current")) {
							grad.setCurrentInfo(infoState);
							infoState.setFormatType("current");
						}
						else {
							grad.setPendingInfo(infoState);
							infoState.setFormatType("pending");
						}
						for(int w = 0; w < infoState.getNumContents(); w++) {
							infoState.getContents().add(new ContentComponent(i.next()));
						}
					}
				}
				// check if infoState's info was found
				if(infoState == null) {
					System.err.println(". . . . INFOSTATE NOT FOUND. . . .");
				}
				infoStateList.add(infoState);
			}
			System.out.println("InfoStateList loaded from CSV file");		
			return infoStateList;
		} finally {
			readInfoStates.close();
		}
	}
}