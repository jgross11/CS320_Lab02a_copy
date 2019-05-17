package edu.ycp.cs320.PersonalizedCommencementProject.persist;

import java.io.IOException;
import java.util.ArrayList;
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
import edu.ycp.cs320.PersonalizedCommencementProject.model.ZUNUSED_Pair;
*/

/*
 * This file is heavily based off of example code provided by <DONALD J. HAKE II>
 * Thanks.
 */

public class FakeDatabase implements IDatabase {

	@Override
	public List<User> findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Graduate> findGraduateByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advisor> findAdvisorByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InfoState> findGraduateInfoStateByGraduateUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Graduate> findAdvisorGraduatesByAdvisorUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContentComponent> findContentComponentsByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertGraduateMediaIntoContentComponentTable(ContentComponent content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> fetchEventDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertDateIntoAdminTable(long date) {
		// TODO Auto-generated method stub
		return null;
	}
}
