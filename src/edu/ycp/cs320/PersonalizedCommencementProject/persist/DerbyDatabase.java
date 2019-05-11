package edu.ycp.cs320.PersonalizedCommencementProject.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

public class DerbyDatabase implements IDatabase {
	private List<User> userList;
	private List<Graduate> graduateList;
	private List<Advisor> advisorList;
	private List<Admin> adminList;
	private List<InfoState> infoStateList;
	private List<ContentComponent> contentComponentList;
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	@Override
	public List<User> findUserByUsername(String username) {
		return executeTransaction(new Transaction<List<User>>() {
			@Override
			public List<User> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select users.*" +
							"  from  users " +
							"  where users.username = ? "
					);
					stmt.setString(1, username);
					
					List<User> result = new ArrayList<User>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						User user = new User();
						loadUser(user, resultSet, 1);
						result.add(user);
					}
					
					// check if the username was found
					if (!found) {
						System.out.println(username + " was not found in the user table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public List<Graduate> findGraduateByUsername(String username) {
		return executeTransaction(new Transaction<List<Graduate>>() {
			@Override
			public List<Graduate> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				System.out.println("Attempting to find graduate by username: " + username);
				try {
					stmt = conn.prepareStatement(
							"select graduates.major, graduates.advisorusername, "
							+ "graduates.status, contentComponents.infostatetype, "
							+ "contentComponents.status, contentComponents.type, "
							+ "contentComponents.content "
							+ "from graduates, contentComponents where "
							+ "graduates.username = contentComponents.username and graduates.username = ?"
					);
					stmt.setString(1, username);
					List<User> gradAsUser = findUserByUsername(username);
					List<Graduate> result = new ArrayList<Graduate>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						Graduate graduate = new Graduate(gradAsUser.get(0));
						loadGraduate(graduate, resultSet, 1);
						result.add(graduate);
					}
					
					// check if the username was found
					if (!found) {
						System.out.println(username + " was not found in the user table");
					}
					System.out.println(username + " was found");
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public List<Advisor> findAdvisorByUsername(String username) {
		return executeTransaction(new Transaction<List<Advisor>>() {
			@Override
			public List<Advisor> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select users.*, advisors.* from users, advisors where advisors.username = ? and users.username = advisors.username"
					);
					stmt.setString(1, username);
					
					List<Advisor> result = new ArrayList<Advisor>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						User user = new User();
						loadUser(user, resultSet, 1);
						Advisor advisor = new Advisor(user);
						loadAdvisor(advisor, resultSet, 7);
						result.add(advisor);
					}
					
					// check if the username was found
					if (!found) {
						System.out.println(username + " was not found in the user table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public List<Admin> findAdminByUsername(String username) {
		return executeTransaction(new Transaction<List<Admin>>() {
			@Override
			public List<Admin> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select users.*, admins.* from users, admins where admins.username = ? and users.username = admins.username"
					);
					stmt.setString(1, username);
					
					List<Admin> result = new ArrayList<Admin>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						User user = new User();
						loadUser(user, resultSet, 1);
						Admin admin = new Admin(user);
						loadAdmin(admin, resultSet, 7);
						result.add(admin);
					}
					
					// check if the username was found
					if (!found) {
						System.out.println(username + " was not found in the user table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public List<ContentComponent> findContentComponentsByUsername(String username) {
		return executeTransaction(new Transaction<List<ContentComponent>>() {
			@Override
			public List<ContentComponent> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select contentComponents.* from contentComponents where contentComponents.username = ?"
					);
					stmt.setString(1, username);
					
					List<ContentComponent> result = new ArrayList<ContentComponent>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						ContentComponent content = new ContentComponent();
						loadContentComponent(content, resultSet, 1);
					}
					
					// check if the contentComponents were found
					if (!found) {
						System.out.println("No contentComponents belonging to " + username + " were found in the contentComponent table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public List<InfoState> findGraduateInfoStateByGraduateUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Graduate> findAdvisorGraduatesByAdvisorUsername(String username) {
		return executeTransaction(new Transaction<List<Graduate>>() {
			@Override
			public List<Graduate> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select graduates.username from graduates where graduates.advisorUsername = ?"
					);
					stmt.setString(1, username);
					
					List<Graduate> result = new ArrayList<Graduate>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					int i = 1;
					String graduateUsername = "";
					while (resultSet.next()) {
						found = true;
						graduateUsername = resultSet.getString(i++);
						result.addAll(findGraduateByUsername(graduateUsername));
						i = 1;
					}
					
					// check if the username was found
					if (!found) {
						System.out.println(username + " was not found in the advisor table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	// wrapper SQL transaction function that calls actual transaction function (which has retries)
		public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
			try {
				return doExecuteTransaction(txn);
			} catch (SQLException e) {
				throw new PersistenceException("Transaction failed", e);
			}
		}
		
		// SQL transaction function which retries the transaction MAX_ATTEMPTS times before failing
		public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
			Connection conn = connect();
			
			try {
				int numAttempts = 0;
				boolean success = false;
				ResultType result = null;
				
				while (!success && numAttempts < MAX_ATTEMPTS) {
					try {
						result = txn.execute(conn);
						conn.commit();
						success = true;
					} catch (SQLException e) {
						if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
							// Deadlock: retry (unless max retry count has been reached)
							numAttempts++;
						} else {
							// Some other kind of SQLException
							throw e;
						}
					}
				}
				
				if (!success) {
					throw new SQLException("Transaction failed (too many retries)");
				}
				
				// Success!
				return result;
			} finally {
				DBUtil.closeQuietly(conn);
			}
		}

		// TODO: Here is where you name and specify the location of your Derby SQL database
		// TODO: Change it here and in SQLDemo.java under CS320_LibraryExample_Lab06->edu.ycp.cs320.sqldemo
		// TODO: DO NOT PUT THE DB IN THE SAME FOLDER AS YOUR PROJECT - that will cause conflicts later w/Git
		private Connection connect() throws SQLException {
			// set conn to database relative to os
			String os = System.getProperty("os.name");
			Connection conn;
			if(os.equals("Linux")) {
				conn = DriverManager.getConnection("jdbc:derby:home/CS320-2019-PersonalizedCommencementProject-DB/pcp.db;create=true");
			}
			else {
				conn = DriverManager.getConnection("jdbc:derby:C:/CS320-2019-PersonalizedCommencementProject-DB/pcp.db;create=true");		
			}
			
			// Set autocommit() to false to allow the execution of
			// multiple queries/statements as part of the same transaction.
			conn.setAutoCommit(false);
			
			return conn;
		}
	
		// retrieves User information from query result set
		private void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
			
			// users.username
			user.setUsername(resultSet.getString(index++));
			
			// users.password
			user.setPassword(resultSet.getString(index++)); 
			
			// users.firstname
			user.setFirstName(resultSet.getString(index++)); 
			
			// users.lastname
			user.setLastName(resultSet.getString(index++)); 
			
			// users.type
			user.setType(resultSet.getString(index++)); 
			
			// users.image
			user.setImage(resultSet.getString(index++)); 
		}
		private void loadInfoState(InfoState info, ResultSet resultSet, int index) throws SQLException {
			
	
		}
		
		// retrieves Graduate information from query result set
		private void loadGraduate(Graduate graduate, ResultSet resultSet, int index) throws SQLException {
			
			// graduates.major
			graduate.setMajor(resultSet.getString(index++));
			
			// graduates.advisorUsername
			graduate.setAdvisor(resultSet.getString(index++));
			
			// graduates.status
			graduate.setStatus(Boolean.valueOf(resultSet.getString(index++)));
			
			InfoState current = new InfoState();
			InfoState pending = new InfoState();
			do {
				System.out.println("index: " + index);
				
				// contentcomponents.infostatetype
				String infoStateType = resultSet.getString(index++);
				
				// contentcomponents.status
				String status = resultSet.getString(index++);
				
				// contentcomponents.type
				String contentType = resultSet.getString(index++);
				
				// contentcomponents.path
				String path = resultSet.getString(index++);
				
				ContentComponent content = new ContentComponent(path, Boolean.valueOf(status), contentType, graduate.getUsername());
				
				int contentIndex = 0;
				
				switch(content.getType()) {
				case "profilePicture":
					contentIndex = InfoState.PROFILE_INDEX;
					break;
				case "extraInformation":
					contentIndex = InfoState.EXTRAINFORMATION_INDEX;
					break;
				case "namePronunciation":
					contentIndex = InfoState.NAMEPRONUNCIATION_INDEX;
					break;
				case "slideshow1":
					contentIndex = InfoState.SLIDESHOW1_INDEX;
					break;
				case "slideshow2":
					contentIndex = InfoState.SLIDESHOW2_INDEX;
					break;
				case "slideshow3":
					contentIndex = InfoState.SLIDESHOW3_INDEX;
					break;
				case "slideshow4":
					contentIndex = InfoState.SLIDESHOW4_INDEX;
					break;
				case "video":
					contentIndex = InfoState.VIDEO_INDEX;
					break;
				}
				System.out.println("contentIndex for type " + contentType + ": " + contentIndex);
				if(infoStateType.equals("pending")) {
					pending.getContents().remove(contentIndex);
					pending.getContents().add(contentIndex, content);
				}
				else {
					current.getContents().remove(contentIndex);
					current.getContents().add(contentIndex, content);
				}
				index -= 4;
			}
			while(resultSet.next());
			for(int i = 0; i < current.getContents().size(); i++) {
				System.out.println("current for index " + i + ": " + current.getContents().get(i).getContent());
				System.out.println("pending for index " + i + ": " + pending.getContents().get(i).getContent());
			}
			graduate.setCurrentInfo(current);
			graduate.setPendingInfo(current);
		}
		
		// retrieves Advisor information from query result set
		private void loadAdvisor(Advisor advisor, ResultSet resultSet, int index) throws SQLException {
					
			// skip advisors.username
			index++;
			
			// advisors.academicInfo
			advisor.setAcademicInformation((resultSet.getString(index++))); 
			
			// advisors.status
			advisor.setStatus(resultSet.getBoolean(index++));
			
			// populate advisor's list of students
		}
		
		// retrieves Admin information from query result set
		private void loadAdmin(Admin admin, ResultSet resultSet, int index) throws SQLException {
					
			// skip admins.username
			index++;
			
			// admins.eventDate
			admin.setDate(Date.valueOf(resultSet.getString(index++))); 
		}
		
		// retrieves ContentComponent information from query result set
		private void loadContentComponent(ContentComponent content, ResultSet resultSet, int index) throws SQLException {
			
			// contentComponent.username
			content.setUsername(resultSet.getString(index++));
			
			// contentComponent.infoStateType
			content.setInfoStateType(resultSet.getString(index++));
			
			// contentComponent.status
			content.setStatus(resultSet.getBoolean(index++));
			
			// contentComponent.type
			content.setType(resultSet.getString(index++));
			
			// contentComponent.content
			content.setContent(resultSet.getString(index++));
			
		}
		

	//  creates the users, graduates, advisors, admins, and infostate tables
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				// user table
				PreparedStatement stmt1 = null;
				
				// graduates table
				PreparedStatement stmt2 = null;
				
				// advisor table
				PreparedStatement stmt3 = null;
				
				// admin table
				PreparedStatement stmt4 = null;
				
				// infostate table
				PreparedStatement stmt5 = null;
				
				// contentComponent table
				PreparedStatement stmt6 = null;
			
				try {
					stmt1 = conn.prepareStatement(
						/*
						 *User Table Schema
						 * UserId(int) | username(varchar) | password(varchar) | firstname(varchar)| lastname(varchar) | accountType(varchar) | userImage(varvchar path)  
						 * 
						 * 
						 */
						"create table users ( "+
						"	userID integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +
						"  username varchar(50), "+
						"  password varchar(50), "+
						"  firstname varchar(25), "+
						"  lastname varchar(25), "+
						"  accountType varchar(8),"+
						"  userImage varchar(100)"+
						")"
					);	
					stmt1.executeUpdate();
					
					System.out.println("Users table created");
					
					stmt2 = conn.prepareStatement(
						"create table graduates("+
						"	userID integer constraint UserId references users, " +
						"major varchar(50), "+ 
						"advisorUsername varchar(50),"+
						"status varchar(10)"+
						")"
					);
					stmt2.executeUpdate();
					
					System.out.println("Graduate table created");					
					
					stmt3 = conn.prepareStatement(
							"create table advisors("+
							"	author_id integer constraint author_id references authors, " +
							"academicInfo varchar(50),"+
							"status varchar(5)"+
							")"
					);
					stmt3.executeUpdate();
					
					System.out.println("Advisor table created");

					stmt4 = conn.prepareStatement(
							"create table admins("+
							"	author_id integer constraint author_id references authors, " +
							"eventDate varchar(50)"+
							")"
					);
					stmt4.executeUpdate();
					
					System.out.println("Admin table created");
					
					stmt5 = conn.prepareStatement(

							"create table infostates("+
							"	author_id integer constraint author_id references authors, " +
							"infoStateType varchar(7), extraInfo varchar(255), "+
							"namePronunciation varchar(100), "+
							"slideshowPhoto1 varchar(100), "+
							"slideshowPhoto2 varchar(100), "+
							"slideshowPhoto3 varchar(100), "+
							"slideshowPhoto4 varchar(100), "+
							"video varchar(100)"+
							 ")"

							"create table infostates("
							+ "username varchar(50), "
							+ "infoStateType varchar(7) "
							+ ")"

					);
					stmt5.executeUpdate();
					
					System.out.println("InfoState table created");
					
					stmt6 = conn.prepareStatement(
							
						"create table contentComponents ( "
						+ "  username varchar(50), "
						+ "  infoStateType varchar(50), "
						+ "  status varchar(5), "
						+ "  type varchar(50), "
						+ "  content varchar(50) "
						+ ")"
					);	
					stmt6.executeUpdate();
					
					System.out.println("ContentComponents table created");
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
				}
			}
		});
	}
	
	// loads data retrieved from CSV files into DB tables in batch mode
	
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				try {
					userList = InitialData.getUsers();
					graduateList = InitialData.getGraduates();
					advisorList = InitialData.getAdvisors();
					adminList = InitialData.getAdmins();
					infoStateList = InitialData.getInfoStates();
					contentComponentList = InitialData.getContentComponents();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertUser = null;
				PreparedStatement insertGraduate = null;
				PreparedStatement insertAdvisor = null;
				PreparedStatement insertAdmin = null;
				PreparedStatement insertInfoState = null;
				PreparedStatement insertContentComponent = null;

				try {
					// insert users into users table
					insertUser = conn.prepareStatement("insert into users (username, password, firstname, lastname, accountType, userImage) values (?, ?, ?, ?, ?, ?)");
					for (User user : userList) {
						insertUser.setString(1, user.getUsername());
						insertUser.setString(2, user.getPassword());
						insertUser.setString(3, user.getFirstName());
						insertUser.setString(4, user.getLastName());
						insertUser.setString(5, user.getType());
						insertUser.setString(6, user.getImage());
						insertUser.addBatch();
					}
					insertUser.executeBatch();
					System.out.println("Users table populated");
					
					// insert graduates into graduates table
					insertGraduate = conn.prepareStatement("insert into graduates (userID, major, advisorUsername, status) values (?, ?, ?, ?)");
					for(Graduate graduate : graduateList) {
						insertGraduate.setInt(1, graduate.getUserId());
						insertGraduate.setString(2, graduate.getMajor());
						insertGraduate.setString(3, graduate.getAdvisor());
						insertGraduate.setString(4, String.valueOf(graduate.getStatus()));
						insertGraduate.addBatch();
					}
					insertGraduate.executeBatch();
					System.out.println("Graduates table populated");
					
					// insert advisors into advisors table
					insertAdvisor = conn.prepareStatement("insert into advisors (userId, academicInfo, status) values (?, ?, ?)");
					for(Advisor advisor : advisorList) {
						insertAdvisor.setInt(1, advisor.getUserId());
						insertAdvisor.setString(2, advisor.getAcademicInformation());
						insertAdvisor.setString(3, String.valueOf(advisor.getStatus()));
						insertAdvisor.addBatch();
					}
					insertAdvisor.executeBatch();
					System.out.println("Advisors table populated");
					
					// insert admins into admins table
					insertAdmin = conn.prepareStatement("insert into admins (username, eventDate) values (?, ?)");
					for(Admin admin : adminList) {
						insertAdmin.setInt(1, admin.getUserId());
						insertAdmin.setString(2, String.valueOf(admin.getDate()));
						insertAdmin.addBatch();
					}
					insertAdmin.executeBatch();
					System.out.println("Admins table populated");
					
					// insert infoState into infoState table
					insertInfoState = conn.prepareStatement("insert into infostates ("

							+ "userId, infoStateType, extraInfo, namePronunciation, slideshowPhoto1, "
							+ "slideshowPhoto2, slideshowPhoto3, slideshowPhoto4, video) values ("
							+ "?, ?, ?, ?, ?, ?, ?, ?, ?)");
=======
							+ "username, infoStateType)"
							+ " values ("
							+ "?, ?)");

					for(InfoState infoState : infoStateList) {
						insertInfoState.setInt(1, infoState.getUserId());
						insertInfoState.setString(2, infoState.getFormatType());
						/*
						for(int i = 0; i < infoState.getNumContents(); i++) {
							insertInfoState.setString(i + 3, infoState.getContentAtIndex(i).getContent());
						}
						*/
						for(ContentComponent component : infoState.getContents()) {
							
						}
						insertInfoState.addBatch();
					}
					insertInfoState.executeBatch();
					System.out.println("InfoState table populated");
					
					insertContentComponent = conn.prepareStatement("insert into contentComponents ("
							+ "username, infoStateType, status, type, content)"
							+ " values (?, ?, ?, ?, ?)"); 
					for(ContentComponent content : contentComponentList) {
						insertContentComponent.setString(1, content.getUsername());
						insertContentComponent.setString(2, content.getInfoStateType());
						insertContentComponent.setString(3, String.valueOf(content.getStatus()));
						insertContentComponent.setString(4, content.getType());
						insertContentComponent.setString(5, content.getContent());
						insertContentComponent.addBatch();
					}
					insertContentComponent.executeBatch();
					System.out.println("ContentComponent table populated");
					return true;
				} finally {
					DBUtil.closeQuietly(insertUser);
					DBUtil.closeQuietly(insertGraduate);
					DBUtil.closeQuietly(insertAdvisor);	
					DBUtil.closeQuietly(insertAdmin);
					DBUtil.closeQuietly(insertInfoState);
				}
			}
		});
	}
	
	
	// 
	public String InsertIntoEventDate(String date) throws SQLException {
		PreparedStatement stmt1 = null; 
		try {
			Connection conn = null;
			stmt1 = conn.prepareStatement(
				"insert into admin(admin.date)"+
				"select ? "+
				"where admin.username = Jgross11 " 
				);
		
			stmt1.setString(1, date);
			stmt1.execute(); 
		}
		finally {
			DBUtil.closeQuietly(stmt1);
		}
		return null;
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("PCP DB successfully initialized!");
	}

	@Override
	public List<Graduate> findGraduateByUserId(int x) {
		return executeTransaction(new Transaction<List<Graduate>>() {
			@Override
			public List<Graduate> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select graduates.* " +
							"from graduates"+
							"where graduates.userid = ?"
					);
					stmt.setInt(1, x);
					
					List<Graduate> result = new ArrayList<Graduate>();
					
					resultSet = stmt.executeQuery();
					
					// for making sure a result was found  
					Boolean found = false;
					while (resultSet.next()) {
						found = true;
						User user = new User();
						loadUser(user, resultSet, 1);
						Graduate graduate = new Graduate(user);
						loadGraduate(graduate, resultSet, 8);
						result.add(graduate);
					}
					
					// this if statements checks if 
					if (!found) {
						System.out.println("graduate was not found in the advisor table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
		
	}

	@Override
	public List<Advisor> findAdvisorByUserId(int x) {
		return executeTransaction(new Transaction<List<Advisor>>() {
			@Override
			public List<Advisor> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
					"select advisors.* "+
					"from advisors"+ 
					"where advisors.userId = ?"
							
					);
					stmt.setInt(1, x);
					
					List<Advisor> result = new ArrayList<Advisor>();
					
					resultSet = stmt.executeQuery();
					
					// for making sure a result was found  
					Boolean found = false;
					while (resultSet.next()) {
						found = true;
						User user = new User(); 
						loadUser(user, resultSet, 1);
						Advisor advisor = new Advisor(); 
						loadAdvisor(advisor, resultSet, 8);
						result.add(advisor);
					}
					
					// this if statements checks if 
					if (!found) {
						System.out.println("Advisor was not found in the advisor table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public List<Admin> findAdminByUserId(int x) {
		return executeTransaction(new Transaction<List<Admin>>() {
			@Override
			public List<Admin> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
					"select admins.* "+
					"from admins"+ 
					"where admins.userId = ?"
							
					);
					stmt.setInt(1, x);
					
					List<Admin> result = new ArrayList<Admin>();
					
					resultSet = stmt.executeQuery();
					
					// for making sure a result was found  
					Boolean found = false;
					while (resultSet.next()) {
						found = true;
						User user = new User(); 
						loadUser(user, resultSet, 1);
						Admin admin = new Admin(); 
						loadAdmin(admin, resultSet, 8);
						result.add(admin);
					}
					
					// this if statements checks if 
					if (!found) {
						System.out.println("Admin was not found in the advisor table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public List<InfoState> findGraduateInfoStateByGraduateUserID(int x) {		
		return executeTransaction(new Transaction<List<InfoState>>() {
			@Override
				public List<InfoState> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
						"select infoStates.* "+
						"from infoStates"+ 
						"where infostates.userId = ?"
						
						);
				stmt.setInt(1, x);
				
				List<InfoState> result = new ArrayList<InfoState>();
				
				resultSet = stmt.executeQuery();
				
				while() 
			
				

				
				return result;
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(stmt);
			}
		}
	});
	}

	@Override
	public String updateGraduateContentToApporve(String oldContentPath, int studentUserID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateGraduateContentToApporve(String oldContentPath, String newContentPath, int studentUserID) {
		// TODO Auto-generated method stub
		return null;
	}


}
