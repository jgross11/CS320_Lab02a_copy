package edu.ycp.cs320.PersonalizedCommencementProject.persist;

import java.sql.SQLException;
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


public interface IDatabase {

	public List<User> findUserByUsername(String username);
	public List<Graduate> findGraduateByUsername(String username);
	public List<Advisor> findAdvisorByUsername(String username);
	public List<Admin> findAdminByUsername(String username);
	public List<InfoState> findGraduateInfoStateByGraduateUsername(String username);
	List<Graduate> findAdvisorGraduatesByAdvisorUsername(String username);
	public String InsertIntoEventDate(String date) throws SQLException;
	
	//didn't know if anyone still needed the old queries so left them delete before code report please 
	public List<User> findUserByUsernameAndPassword(String username, String password); 
	public List<Graduate> findGraduateByUserId(int x);
	public List<Advisor> findAdvisorByUserId(int x); 
	public List<Admin> findAdminByUserId(int x);
	public List<InfoState> findGraduateInfoStateByGraduateUserID(int x); 
	public String updateGraduateContentToApporve(String oldContentPath,String newContentPath, int studentUserID); 
	
	
}
