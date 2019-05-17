package edu.ycp.cs320.PersonalizedCommencementProject.persist;

import java.util.List;

import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Admin;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.ContentComponent;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Graduate;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.InfoState;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.User;



/*
 * This file is heavily based off of example code provided by <DONALD J. HAKE II>
 * Thanks.
 */

/*
import edu.ycp.cs320.PersonalizedCommencementProject.model.ZUNUSED_Author;
import edu.ycp.cs320.PersonalizedCommencementProject.model.ZUNUSED_Book;
import edu.ycp.cs320.PersonalizedCommencementProject.model.ZUNUSED_Pair;
*/

public interface IDatabase {
	public List<User> findUserByUsername(String username);
	public List<Graduate> findGraduateByUsername(String username);
	public List<Advisor> findAdvisorByUsername(String username);
	public List<Long> fetchEventDate();
	public List<InfoState> findGraduateInfoStateByGraduateUsername(String username);
	public List<Graduate> findAdvisorGraduatesByAdvisorUsername(String username);
	public List<ContentComponent> findContentComponentsByUsername(String username);
	public Integer insertGraduateMediaIntoContentComponentTable(ContentComponent content);
	public Integer insertDateIntoAdminTable(long date);
}
