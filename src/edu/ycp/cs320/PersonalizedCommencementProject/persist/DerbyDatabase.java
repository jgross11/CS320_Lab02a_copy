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
							"select users.*, graduates.*, infostates.* "
							+ "from users, graduates, infostates where "
							+ "users.username = graduates.username and "
							+ "graduates.username = infostates.username and graduates.username = ?"
					);
					stmt.setString(1, username);
					
					List<Graduate> result = new ArrayList<Graduate>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						User user = new User();
						loadUser(user, resultSet, 1);
						Graduate graduate = new Graduate(user);
						loadGraduate(graduate, resultSet, 8);
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
						System.out.println(graduateUsername);
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
		
		// retrieves Graduate information from query result set
		private void loadGraduate(Graduate graduate, ResultSet resultSet, int index) throws SQLException {
			
			// graduates.major
			graduate.setMajor(resultSet.getString(index++));
			
			// graduates.advisorUsername
			graduate.setAdvisor(resultSet.getString(index++));
			
			// graduates.status
			graduate.setStatus(Boolean.valueOf(resultSet.getString(index++)));
			
			// skips infostates.username
			index++;
			
			// TODO: this could be improved to call a function to iterate instead of 
			// TODO: copying / pasting the same four loops 
			
			// 1st infostate creation
			
			// infostates.type
			String infostateType = resultSet.getString(index++);
			if(infostateType.equals("current")) {
				InfoState currentIS = new InfoState();
				
				// iterates through results adding, in order: extra info->name pronunciation->slideshowphoto1..4->video
				for(int i = 0; i < currentIS.getNumContents(); i++) {
					currentIS.getContents().add(i, new ContentComponent(resultSet.getString(index++)));
				}
				
				// set created infostate to graduate's current
				graduate.setCurrentInfo(currentIS);
			}
			else {
				InfoState pendingIS = new InfoState();
				// iterates through results adding, in order: extra info->name pronunciation->slideshowphoto1..4->video
				
				for(int i = 0; i < pendingIS.getNumContents(); i++) {
					pendingIS.getContents().add(i, new ContentComponent(resultSet.getString(index++)));
				}
				
				// set created infostate to graduate's pending
				graduate.setPendingInfo(pendingIS);
			}
			
			// move to next query
			resultSet.next();
			
			// skips information already set
			index = 12;
			
			// 2nd infostate creation
			infostateType = resultSet.getString(index++);
			if(infostateType.equals("current")) {
				InfoState currentIS = new InfoState();
				
				// iterates through results adding, in order: extra info->name pronunciation->slideshowphoto1..4->video
				for(int i = 0; i < currentIS.getNumContents(); i++) {
					currentIS.getContents().add(i, new ContentComponent(resultSet.getString(index++)));
				}
				
				// set created infostate to graduate's current
				graduate.setCurrentInfo(currentIS);
			}
			else {
				InfoState pendingIS = new InfoState();
				
				// iterates through results adding, in order: extra info->name pronunciation->slideshowphoto1..4->video
				for(int i = 0; i < pendingIS.getNumContents(); i++) {
					pendingIS.getContents().add(i, new ContentComponent(resultSet.getString(index++)));
				}
				
				// set created infostate to graduate's pending
				graduate.setPendingInfo(pendingIS);
			}
			
		}
		
		// retrieves Advisor information from query result set
		private void loadAdvisor(Advisor advisor, ResultSet resultSet, int index) throws SQLException {
					
			// skip advisors.username
			index++;
			
			// advisors.academicInfo
			advisor.setAcademicInformation((resultSet.getString(index++))); 
			
			// advisors.status
			advisor.setStatus(Boolean.parseBoolean(resultSet.getString(index++))); 
			
			// populate advisor's list of students
		}
		
		// retrieves Admin information from query result set
		private void loadAdmin(Admin admin, ResultSet resultSet, int index) throws SQLException {
					
			// skip admins.username
			index++;
			
			// admins.eventDate
			admin.setDate(Date.valueOf(resultSet.getString(index++))); 
		}		
		
	/*
	// transaction that retrieves a Book, and its Author by Title
	@Override
	public List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> findAuthorAndBookByTitle(final String title) {
		return executeTransaction(new Transaction<List<ZUNUSED_Pair<ZUNUSED_Author,ZUNUSED_Book>>>() {
			@Override
			public List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select authors.*, books.* " +
							"  from  authors, books, bookAuthors " +
							"  where books.title = ? " +
							"    and authors.author_id = bookAuthors.author_id " +
							"    and books.book_id     = bookAuthors.book_id"
					);
					stmt.setString(1, title);
					
					List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> result = new ArrayList<ZUNUSED_Pair<ZUNUSED_Author,ZUNUSED_Book>>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						ZUNUSED_Author author = new ZUNUSED_Author();
						loadAuthor(author, resultSet, 1);
						ZUNUSED_Book book = new ZUNUSED_Book();
						loadBook(book, resultSet, 4);
						
						result.add(new ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>(author, book));
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("<" + title + "> was not found in the books table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	// transaction that retrieves a list of Books with their Authors, given Author's last name
	@Override
	public List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> findAuthorAndBookByAuthorLastName(final String lastName) {
		return executeTransaction(new Transaction<List<ZUNUSED_Pair<ZUNUSED_Author,ZUNUSED_Book>>>() {
			@Override
			public List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				// try to retrieve Authors and Books based on Author's last name, passed into query
				try {
					stmt = conn.prepareStatement(
							"select authors.*, books.* " +
							"  from  authors, books, bookAuthors " +
							"  where authors.lastname = ? " +
							"    and authors.author_id = bookAuthors.author_id " +
							"    and books.book_id     = bookAuthors.book_id "   +
							"  order by books.title asc, books.published asc"
					);
					stmt.setString(1, lastName);
					
					// establish the list of (Author, Book) Pairs to receive the result
					List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> result = new ArrayList<ZUNUSED_Pair<ZUNUSED_Author,ZUNUSED_Book>>();
					
					// execute the query, get the results, and assemble them in an ArrayLsit
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						ZUNUSED_Author author = new ZUNUSED_Author();
						loadAuthor(author, resultSet, 1);
						ZUNUSED_Book book = new ZUNUSED_Book();
						loadBook(book, resultSet, 4);
						
						result.add(new ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>(author, book));
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	// transaction that retrieves all Books in Library, with their respective Authors
	@Override
	public List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> findAllBooksWithAuthors() {
		return executeTransaction(new Transaction<List<ZUNUSED_Pair<ZUNUSED_Author,ZUNUSED_Book>>>() {
			@Override
			public List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select authors.*, books.* " +
							"  from authors, books, bookAuthors " +
							"  where authors.author_id = bookAuthors.author_id " +
							"    and books.book_id     = bookAuthors.book_id "   +
							"  order by books.title asc"
					);
					
					List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> result = new ArrayList<ZUNUSED_Pair<ZUNUSED_Author,ZUNUSED_Book>>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						ZUNUSED_Author author = new ZUNUSED_Author();
						loadAuthor(author, resultSet, 1);
						ZUNUSED_Book book = new ZUNUSED_Book();
						loadBook(book, resultSet, 4);
						
						result.add(new ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>(author, book));
					}
					
					// check if any books were found
					if (!found) {
						System.out.println("No books were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}	
	
	
	// transaction that retrieves all Authors in Library
	@Override
	public List<ZUNUSED_Author> findAllAuthors() {
		return executeTransaction(new Transaction<List<ZUNUSED_Author>>() {
			@Override
			public List<ZUNUSED_Author> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from authors " +
							" order by lastname asc, firstname asc"
					);
					
					List<ZUNUSED_Author> result = new ArrayList<ZUNUSED_Author>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						ZUNUSED_Author author = new ZUNUSED_Author();
						loadAuthor(author, resultSet, 1);
						
						result.add(author);
					}
					
					// check if any authors were found
					if (!found) {
						System.out.println("No authors were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	// transaction that inserts new Book into the Books table
	// also first inserts new Author into Authors table, if necessary
	// and then inserts entry into BookAuthors junction table
	@Override
	public Integer insertBookIntoBooksTable(final String title, final String isbn, final int published, final String lastName, final String firstName) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;				
				
				ResultSet resultSet1 = null;
				ResultSet resultSet3 = null;
				ResultSet resultSet5 = null;				
				
				// for saving author ID and book ID
				Integer author_id = -1;
				Integer book_id   = -1;

				// try to retrieve author_id (if it exists) from DB, for Author's full name, passed into query
				try {
					stmt1 = conn.prepareStatement(
							"select author_id from authors " +
							"  where lastname = ? and firstname = ? "
					);
					stmt1.setString(1, lastName);
					stmt1.setString(2, firstName);
					
					// execute the query, get the result
					resultSet1 = stmt1.executeQuery();

					
					// if Author was found then save author_id					
					if (resultSet1.next())
					{
						author_id = resultSet1.getInt(1);
						System.out.println("Author <" + lastName + ", " + firstName + "> found with ID: " + author_id);						
					}
					else
					{
						System.out.println("Author <" + lastName + ", " + firstName + "> not found");
				
						// if the Author is new, insert new Author into Authors table
						if (author_id <= 0) {
							// prepare SQL insert statement to add Author to Authors table
							stmt2 = conn.prepareStatement(
									"insert into authors (lastname, firstname) " +
									"  values(?, ?) "
							);
							stmt2.setString(1, lastName);
							stmt2.setString(2, firstName);
							
							// execute the update
							stmt2.executeUpdate();
							
							System.out.println("New author <" + lastName + ", " + firstName + "> inserted in Authors table");						
						
							// try to retrieve author_id for new Author - DB auto-generates author_id
							stmt3 = conn.prepareStatement(
									"select author_id from authors " +
									"  where lastname = ? and firstname = ? "
							);
							stmt3.setString(1, lastName);
							stmt3.setString(2, firstName);
							
							// execute the query							
							resultSet3 = stmt3.executeQuery();
							
							// get the result - there had better be one							
							if (resultSet3.next())
							{
								author_id = resultSet3.getInt(1);
								System.out.println("New author <" + lastName + ", " + firstName + "> ID: " + author_id);						
							}
							else	// really should throw an exception here - the new author should have been inserted, but we didn't find them
							{
								System.out.println("New author <" + lastName + ", " + firstName + "> not found in Authors table (ID: " + author_id);
							}
						}
					}
					
					// now insert new Book into Books table
					// prepare SQL insert statement to add new Book to Books table
					stmt4 = conn.prepareStatement(
							"insert into books (title, isbn, published) " +
							"  values(?, ?, ?) "
					);
					stmt4.setString(1, title);
					stmt4.setString(2, isbn);
					stmt4.setInt(3, published);
					
					// execute the update
					stmt4.executeUpdate();
					
					System.out.println("New book <" + title + "> inserted into Books table");					

					// now retrieve book_id for new Book, so that we can set up BookAuthor entry
					// and return the book_id, which the DB auto-generates
					// prepare SQL statement to retrieve book_id for new Book
					stmt5 = conn.prepareStatement(
							"select book_id from books " +
							"  where title = ? and isbn = ? and published = ? "
									
					);
					stmt5.setString(1, title);
					stmt5.setString(2, isbn);
					stmt5.setInt(3, published);

					// execute the query
					resultSet5 = stmt5.executeQuery();
					
					// get the result - there had better be one
					if (resultSet5.next())
					{
						book_id = resultSet5.getInt(1);
						System.out.println("New book <" + title + "> ID: " + book_id);						
					}
					else	// really should throw an exception here - the new book should have been inserted, but we didn't find it
					{
						System.out.println("New book <" + title + "> not found in Books table (ID: " + book_id);
					}
					
					// now that we have all the information, insert entry into BookAuthors table
					// which is the junction table for Books and Authors
					// prepare SQL insert statement to add new Book to Books table
					stmt6 = conn.prepareStatement(
							"insert into bookAuthors (book_id, author_id) " +
							"  values(?, ?) "
					);
					stmt6.setInt(1, book_id);
					stmt6.setInt(2, author_id);
					
					// execute the update
					stmt6.executeUpdate();
					
					System.out.println("New entry for book ID <" + book_id + "> and author ID <" + author_id + "> inserted into BookAuthors junction table");						
					
					System.out.println("New book <" + title + "> inserted into Books table");					
					
					return book_id;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);					
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt3);					
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(resultSet5);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
				}
			}
		});
	}
	
	
	// transaction that deletes Book (and possibly its Author) from Library
	@Override
	public List<ZUNUSED_Author> removeBookByTitle(final String title) {
		return executeTransaction(new Transaction<List<ZUNUSED_Author>>() {
			@Override
			public List<ZUNUSED_Author> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;							
				
				ResultSet resultSet1    = null;			
				ResultSet resultSet2    = null;
				ResultSet resultSet5    = null;
				
				try {
					// first get the Author(s) of the Book to be deleted
					// just in case it's the only Book by this Author
					// in which case, we should also remove the Author(s)
					stmt1 = conn.prepareStatement(
							"select authors.* " +
							"  from  authors, books, bookAuthors " +
							"  where books.title = ? " +
							"    and authors.author_id = bookAuthors.author_id " +
							"    and books.book_id     = bookAuthors.book_id"
					);
					
					// get the Book's Author(s)
					stmt1.setString(1, title);
					resultSet1 = stmt1.executeQuery();
					
					// assemble list of Authors from query
					List<ZUNUSED_Author> authors = new ArrayList<ZUNUSED_Author>();					
				
					while (resultSet1.next()) {
						ZUNUSED_Author author = new ZUNUSED_Author();
						loadAuthor(author, resultSet1, 1);
						authors.add(author);
					}
					
					// check if any Authors were found
					// this shouldn't be necessary, there should not be a Book in the DB without an Author
					if (authors.size() == 0) {
						System.out.println("No author was found for title <" + title + "> in the database");
					}
										
					// now get the Book(s) to be deleted
					// we will need the book_id to remove associated entries in BookAuthors (junction table)
				
					stmt2 = conn.prepareStatement(
							"select books.* " +
							"  from  books " +
							"  where books.title = ? "
					);
					
					// get the Book(s)
					stmt2.setString(1, title);
					resultSet2 = stmt2.executeQuery();
					
					// assemble list of Books from query
					List<ZUNUSED_Book> books = new ArrayList<ZUNUSED_Book>();					
				
					while (resultSet2.next()) {
						ZUNUSED_Book book = new ZUNUSED_Book();
						loadBook(book, resultSet2, 1);
						books.add(book);
					}
					
					// first delete entries in BookAuthors junction table
					// can't delete entries in Books or Authors tables while they have foreign keys in junction table
					// prepare to delete the junction table entries (bookAuthors)
					stmt3 = conn.prepareStatement(
							"delete from bookAuthors " +
							"  where book_id = ? "
					);
					
					// delete the junction table entries from the DB for this title
					// this works if there are not multiple books with the same name
					stmt3.setInt(1, books.get(0).getBookId());
					stmt3.executeUpdate();
					
					System.out.println("Deleted junction table entries for book(s) <" + title + "> from DB");									
					
					// now delete entries in Books table for this title
					stmt4 = conn.prepareStatement(
							"delete from books " +
							"  where title = ? "
					);
					
					// delete the Book entries from the DB for this title
					stmt4.setString(1, title);
					stmt4.executeUpdate();
					
					System.out.println("Deleted book(s) with title <" + title + "> from DB");									
					
					// now check if the Author(s) have any Books remaining in the DB
					// only need to check if there are any entries in junction table that have this author ID
					for (int i = 0; i < authors.size(); i++) {
						// prepare to find Books for this Author
						stmt5 = conn.prepareStatement(
								"select books.book_id from books, bookAuthors " +
								"  where bookAuthors.author_id = ? "
						);
						
						// retrieve any remaining books for this Author
						stmt5.setInt(1, books.get(i).getAuthorId());
						resultSet5 = stmt5.executeQuery();						

						// if nothing returned, then delete Author
						if (!resultSet5.next()) {
							stmt6 = conn.prepareStatement(
								"delete from authors " +
								"  where author_id = ?"
							);
							
							// delete the Author from DB
							stmt6.setInt(1, authors.get(i).getAuthorId());
							stmt6.executeUpdate();
							
							System.out.println("Deleted author <" + authors.get(i).getLastname() + ", " + authors.get(i).getFirstname() + "> from DB");
							
							// we're done with this, so close it, since we might have more to do
							DBUtil.closeQuietly(stmt6);
						}
						
						// we're done with this, so close it since we might have more to do
						DBUtil.closeQuietly(resultSet5);
						DBUtil.closeQuietly(stmt5);
					}
					return authors;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(resultSet2);
					
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);					
					DBUtil.closeQuietly(stmt4);					
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
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/CS320-2019-LibraryExample-DB/library.db;create=true");		
		
		// Set autocommit() to false to allow the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	// retrieves Author information from query result set
	private void loadAuthor(ZUNUSED_Author author, ResultSet resultSet, int index) throws SQLException {
		author.setAuthorId(resultSet.getInt(index++));
		author.setLastname(resultSet.getString(index++));
		author.setFirstname(resultSet.getString(index++));
	}
	
	// retrieves Book information from query result set
	private void loadBook(ZUNUSED_Book book, ResultSet resultSet, int index) throws SQLException {
		book.setBookId(resultSet.getInt(index++));
//		book.setAuthorId(resultSet.getInt(index++));  // no longer used
		book.setTitle(resultSet.getString(index++));
		book.setIsbn(resultSet.getString(index++));
		book.setPublished(resultSet.getInt(index++));
	}
	
	// retrieves WrittenBy information from query result set
	private void loadBookAuthors(ZUNUSED_BookAuthor bookAuthor, ResultSet resultSet, int index) throws SQLException {
		bookAuthor.setBookId(resultSet.getInt(index++));
		bookAuthor.setAuthorId(resultSet.getInt(index++));
	}
	*/
	//  creates the users, graduates, advisors, admins, and infostate tables
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
			
				try {
					stmt1 = conn.prepareStatement(
						
						"create table users ( "
						+ "  username varchar(50), "
						+ "  password varchar(50), "
						+ "  firstname varchar(25), "
						+ "  lastname varchar(25), "
						+ "  accountType varchar(8), "
						+ "  userImage varchar(100)"
						+ ")"
					);	
					stmt1.executeUpdate();
					
					System.out.println("Users table created");
					
					stmt2 = conn.prepareStatement(
						"create table graduates("
						+ "username varchar(50) primary key,"
						+ "major varchar(50), "
						+ "advisorUsername varchar(50),"
						+ "status varchar(10)"
						+ ")"
					);
					stmt2.executeUpdate();
					
					System.out.println("Graduate table created");					
					
					stmt3 = conn.prepareStatement(
							"create table advisors("
							+ "username varchar(50), "
							+ "academicInfo varchar(50),"
							+ "status varchar(5)"
							+ ")"
					);
					stmt3.executeUpdate();
					
					System.out.println("Advisor table created");

					stmt4 = conn.prepareStatement(
							"create table admins("
							+ "username varchar(50), "
							+ "eventDate varchar(50)"
							+ ")"
					);
					stmt4.executeUpdate();
					
					System.out.println("Admin table created");
					
					stmt5 = conn.prepareStatement(
							"create table infostates("
							+ "username varchar(50), "
							+ "infoStateType varchar(7), extraInfo varchar(255), "
							+ "namePronunciation varchar(100), "
							+ "slideshowPhoto1 varchar(100), "
							+ "slideshowPhoto2 varchar(100), "
							+ "slideshowPhoto3 varchar(100), "
							+ "slideshowPhoto4 varchar(100), "
							+ "video varchar(100)"
							+ ")"
					);
					stmt5.executeUpdate();
					
					System.out.println("InfoState table created");
										
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
				}
			}
		});
	}
	
	// loads data retrieved from CSV files into DB tables in batch mode
	
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<User> userList;
				List<Graduate> graduateList;
				List<Advisor> advisorList;
				List<Admin> adminList;
				List<InfoState> infoStateList;
				
				try {
					userList = InitialData.getUsers();
					graduateList = InitialData.getGraduates();
					advisorList = InitialData.getAdvisors();
					adminList = InitialData.getAdmins();
					infoStateList = InitialData.getInfoStates();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertUser = null;
				PreparedStatement insertGraduate = null;
				PreparedStatement insertAdvisor = null;
				PreparedStatement insertAdmin = null;
				PreparedStatement insertInfoState = null;

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
					insertGraduate = conn.prepareStatement("insert into graduates (username, major, advisorUsername, status) values (?, ?, ?, ?)");
					for(Graduate graduate : graduateList) {
						insertGraduate.setString(1, graduate.getUsername());
						insertGraduate.setString(2, graduate.getMajor());
						insertGraduate.setString(3, graduate.getAdvisor());
						insertGraduate.setString(4, String.valueOf(graduate.getStatus()));
						insertGraduate.addBatch();
					}
					insertGraduate.executeBatch();
					System.out.println("Graduates table populated");
					
					// insert advisors into advisors table
					insertAdvisor = conn.prepareStatement("insert into advisors (username, academicInfo, status) values (?, ?, ?)");
					for(Advisor advisor : advisorList) {
						insertAdvisor.setString(1, advisor.getUsername());
						insertAdvisor.setString(2, advisor.getAcademicInformation());
						insertAdvisor.setString(3, String.valueOf(advisor.getStatus()));
						insertAdvisor.addBatch();
					}
					insertAdvisor.executeBatch();
					System.out.println("Advisors table populated");
					
					// insert admins into admins table
					insertAdmin = conn.prepareStatement("insert into admins (username, eventDate) values (?, ?)");
					for(Admin admin : adminList) {
						insertAdmin.setString(1, admin.getUsername());
						insertAdmin.setString(2, String.valueOf(admin.getDate()));
						insertAdmin.addBatch();
					}
					insertAdmin.executeBatch();
					System.out.println("Admins table populated");
					
					// insert infoState into infoState table
					insertInfoState = conn.prepareStatement("insert into infostates ("
							+ "username, infoStateType, extraInfo, namePronunciation, slideshowPhoto1, "
							+ "slideshowPhoto2, slideshowPhoto3, slideshowPhoto4, video) values ("
							+ "?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for(InfoState infoState : infoStateList) {
						insertInfoState.setString(1, infoState.getUsername());
						insertInfoState.setString(2, infoState.getFormatType());
						for(int i = 0; i < infoState.getNumContents(); i++) {
							insertInfoState.setString(i + 3, infoState.getContentAtIndex(i).getContent());
						}
						insertInfoState.addBatch();
					}
					insertInfoState.executeBatch();
					System.out.println("InfoState table populated");
					
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
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("PCP DB successfully initialized!");
	}
}
