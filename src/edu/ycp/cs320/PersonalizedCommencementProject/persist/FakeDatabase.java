package edu.ycp.cs320.PersonalizedCommencementProject.persist;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Admin;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Graduate;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.InfoState;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.User;




/*
 * This file is heavily based off of Skeleton example code provided by <DONALD J. HAKE II> 
 * Thanks.
 */

public class FakeDatabase implements IDatabase {
	private List<User> UserList; 
	private List<Graduate> GraduateList; 
	private List<Admin> AdminList; 
	private List<Advisor> AdvisorList; 
	private List<InfoState> InfoStateList; 
	public FakeDatabase() {
		
		UserList = new ArrayList<User>(); 
		GraduateList = new ArrayList<Graduate>(); 
		AdminList = new ArrayList<Admin>(); 
		AdvisorList = new ArrayList<Advisor>();
		InfoStateList = new ArrayList<InfoState>(); 

		readInitialData(); 
		System.out.println(UserList.size()+"Users"); 
		System.out.println(GraduateList.size()+"Graduates");
		System.out.println(AdminList.size()+"Admins"); 
	
	}
	private void readInitialData() {
		try {
			
			UserList.addAll(InitialData.getUsers()); 
			GraduateList.addAll(InitialData.getGraduates()); 
			AdminList.addAll(InitialData.getAdmins()); 
			AdvisorList.addAll(InitialData.getAdvisors()); 
			InfoStateList.addAll(InitialData.getInfoStates());
			
		}
		catch(IOException e) {
			throw new IllegalStateException("Couldn't read initial data", e);
		}
		
	}
	@Override
	public List<User> findUserByUsername(String username) {
		List<User> Result = new ArrayList<User>();
		
		for(User user : UserList) {
			if(user.getUsername() == username) {
				Result.add(user); 
			} 
		}
		return Result; 
	}

	@Override
	public List<Graduate> findGraduateByUsername(String username) {
		List<Graduate> Result = new ArrayList<Graduate>(); 
		for(Graduate graduate : GraduateList) {
			if(graduate.getUsername() == username) {
				Result.add(graduate); 
			}
		} 	
		return Result; 
	}

	@Override
	public List<Advisor> findAdvisorByUsername(String username) {
		List<Advisor> Result = new ArrayList<Advisor>();
		
		for(Advisor advisor : AdvisorList) {
			if(advisor.getUsername() == username) {
				Result.add(advisor); 
			}
		}
		return Result; 
	}

	@Override
	public List<Admin> findAdminByUsername(String username) {
		List<Admin> Result = new ArrayList<Admin>(); 
		
		for(Admin admin : AdminList) {
			if(admin.getUsername() == username) {
				Result.add(admin); 
			}
		}
		
		return Result; 
	}

	@Override
	public List<InfoState> findGraduateInfoStateByGraduateUsername(String username) {
		List<InfoState> Result = new ArrayList<InfoState>(); 
		
		for(InfoState infostate: InfoStateList) {
			if(infostate.getUsername() == username) {
				Result.add(infostate); 
			}
		}
	
		return Result; 
	}
	

	@Override
	public List<Graduate> findAdvisorGraduatesByAdvisorUsername(String username) {
		List<Graduate> Result = new ArrayList<Graduate>(); 
		
		for(Graduate graduate : GraduateList) {
			if(graduate.getAdvisor() == username) {
				Result.add(graduate);
			}
		}
		
		return Result; 
	}
	@Override
	public String InsertIntoEventDate(String date) throws SQLException {
		return null; 
	}
	@Override
	public List<Graduate> findGraduateByUserId(int x) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Advisor> findAdvisorByUserId(int x) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Admin> findAdminByUserId(int x) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<InfoState> findGraduateInfoStateByGraduateUserID(int x) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String updateGraduateContentToApporve(String oldContentPath, int studentUserID) {
		// TODO Auto-generated method stub
		return null;
	}
}
