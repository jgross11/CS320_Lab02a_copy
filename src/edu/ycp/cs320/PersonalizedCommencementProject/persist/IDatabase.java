package edu.ycp.cs320.PersonalizedCommencementProject.persist;

import java.util.List;

import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Admin;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Advisor;
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
	/*
	public List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> findAuthorAndBookByTitle(String title);
	public List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> findAuthorAndBookByAuthorLastName(String lastName);
	public Integer insertBookIntoBooksTable(String title, String isbn, int published, String lastName, String firstName);
	public List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> findAllBooksWithAuthors();
	public List<ZUNUSED_Author> findAllAuthors();
	public List<ZUNUSED_Author> removeBookByTitle(String title);		
	*/
	public List<User> findUserByUsername(String username);
	public List<Graduate> findGraduateByUsername(String username);
	public List<Advisor> findAdvisorByUsername(String username);
	public List<Admin> findAdminByUsername(String username);
	public List<InfoState> findGraduateInfoStateByGraduateUsername(String username);
}
