package edu.ycp.cs320.PersonalizedCommencementProject.queries;

import java.util.List;
import java.util.Scanner;

import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Graduate;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.InfoState;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.DatabaseProvider;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.IDatabase;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.InitDatabase;

public class AGraduateQuery {
	// find graduate by this username
	private final static String userToFind = "dchism";
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		List<Graduate> graduateList = db.findGraduateByUsername(userToFind);
		
		// check if anything was returned and output the list
		if (graduateList.isEmpty()) {
			System.out.println("There are no users in the database");
		}
		else {
			for (Graduate grad : graduateList) {
				System.out.println("name: " + grad.getName());
				System.out.println("password: " + grad.getPassword());
				System.out.println("type: " + grad.getType());
				System.out.println("image: " + grad.getImage());
				System.out.println("major: " + grad.getMajor());
				System.out.println("advisor username: " + grad.getAdvisor());
				System.out.println("status: " + grad.getStatus());
				InfoState gradCurrentInfo = grad.getCurrentInfo();
				InfoState gradPendingInfo = grad.getPendingInfo();
				System.out.println("current extra info: " + gradCurrentInfo.getContentAtIndex(0).getContent());
				System.out.println("current name pronunciation: " + gradCurrentInfo.getContentAtIndex(1).getContent());
				System.out.println("current image1: " + gradCurrentInfo.getContentAtIndex(2).getContent());
				System.out.println("current image2: " + gradCurrentInfo.getContentAtIndex(3).getContent());
				System.out.println("current image3: " + gradCurrentInfo.getContentAtIndex(4).getContent());
				System.out.println("current image4: " + gradCurrentInfo.getContentAtIndex(5).getContent());
				System.out.println("current vid1: " + gradCurrentInfo.getContentAtIndex(6).getContent());
				System.out.println("pending extra info: " + gradPendingInfo.getContentAtIndex(0).getContent());
				System.out.println("pending name pronunciation: " + gradPendingInfo.getContentAtIndex(1).getContent());
				System.out.println("pending image1: " + gradPendingInfo.getContentAtIndex(2).getContent());
				System.out.println("pending image2: " + gradPendingInfo.getContentAtIndex(3).getContent());
				System.out.println("pending image3: " + gradPendingInfo.getContentAtIndex(4).getContent());
				System.out.println("pending image4: " + gradPendingInfo.getContentAtIndex(5).getContent());
				System.out.println("pending vid1: " + gradPendingInfo.getContentAtIndex(6).getContent());
			}
		}
	}
}
