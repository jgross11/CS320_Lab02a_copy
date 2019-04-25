package edu.ycp.cs320.PersonalizedCommencementProject.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Admin;
import edu.ycp.cs320.PersonalizedCommencementProject.databaseModel.Advisor;
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
	public List<Admin> findAdminByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InfoState> findGraduateInfoStateByGraduateUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	private List<ZUNUSED_Author> authorList;
	private List<ZUNUSED_Book> bookList;
	
	// Fake database constructor - initializes the DB
	// the DB only consists for a List of Authors and a List of Books
	public FakeDatabase() {
		authorList = new ArrayList<ZUNUSED_Author>();
		bookList = new ArrayList<ZUNUSED_Book>();
		
		// Add initial data
		readInitialData();
		
//		System.out.println(authorList.size() + " authors");
//		System.out.println(bookList.size() + " books");
	}

	// loads the initial data retrieved from the CSV files into the DB
	public void readInitialData() {
		try {
			authorList.addAll(InitialData.getAuthors());
			bookList.addAll(InitialData.getBooks());
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't read initial data", e);
		}
	}
	
	// query that retrieves Book and its Author by Title
	@Override
	public List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> findAuthorAndBookByTitle(String title) {
		List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> result = new ArrayList<ZUNUSED_Pair<ZUNUSED_Author,ZUNUSED_Book>>();
		for (ZUNUSED_Book book : bookList) {
//			System.out.println("Book: <" + book.getTitle() + ">" + "  Title: <" + title + ">");
			
			if (book.getTitle().equals(title)) {
				ZUNUSED_Author author = findAuthorByAuthorId(book.getAuthorId());
				result.add(new ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>(author, book));
			}
		}
		return result;
	}
	
	// query that retrieves all Books, for the Author's last name
	@Override
	public List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> findAuthorAndBookByAuthorLastName(String lastName)
	{
		// create list of <Author, Book> for returning result of query
		List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> result = new ArrayList<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>>();
		
		// search through table of Books
		for (ZUNUSED_Book book : bookList) {
			for (ZUNUSED_Author author : authorList) {
				if (book.getAuthorId() == author.getAuthorId()) {
					if (author.getLastname().equals(lastName)) {
						// if this book is by the specified author, add it to the result list
						result.add(new ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>(author, book));						
					}
				}
			}
		}
		return result;
	}

	
	// query that retrieves all Books, with their Authors, from DB
	@Override
	public List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> findAllBooksWithAuthors() {
		List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> result = new ArrayList<ZUNUSED_Pair<ZUNUSED_Author,ZUNUSED_Book>>();
		for (ZUNUSED_Book book : bookList) {
			ZUNUSED_Author author = findAuthorByAuthorId(book.getAuthorId());
			result.add(new ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>(author, book));
		}
		return result;
	}
		

	// query that retrieves all Authors from DB
	@Override
	public List<ZUNUSED_Author> findAllAuthors() {
		List<ZUNUSED_Author> result = new ArrayList<ZUNUSED_Author>();
		for (ZUNUSED_Author author : authorList) {
			result.add(author);
		}
		return result;
	}
	
	
	// query that inserts a new Book, and possibly new Author, into Books and Authors lists
	// insertion requires that we maintain Book and Author id's
	// this can be a real PITA, if we intend to use the IDs to directly access the ArrayLists, since
	// deleting a Book/Author in the list would mean updating the ID's, since other list entries are likely to move to fill the space.
	// or we could mark Book/Author entries as deleted, and leave them open for reuse, but we could not delete an Author
	//    unless they have no Books in the Books table
	@Override
	public Integer insertBookIntoBooksTable(String title, String isbn, int published, String lastName, String firstName)
	{
		int authorId = -1;
		int bookId   = -1;
		
		// search Authors list for the Author, by first and last name, get author_id
		for (ZUNUSED_Author author : authorList) {
			if (author.getLastname().equals(lastName) && author.getFirstname().equals(firstName)) {
				authorId = author.getAuthorId();
			}
		}
		
		// if the Author wasn't found in Authors list, we have to add new Author to Authors list
		if (authorId < 0) {
			// set author_id to size of Authors list + 1 (before adding Author)
			authorId = authorList.size() + 1;
			
			// add new Author to Authors list
			ZUNUSED_Author newAuthor = new ZUNUSED_Author();			
			newAuthor.setAuthorId(authorId);
			newAuthor.setLastname(lastName);
			newAuthor.setFirstname(firstName);
			authorList.add(newAuthor);
			
			System.out.println("New author (ID: " + authorId + ") " + "added to Authors table: <" + lastName + ", " + firstName + ">");
		}

		// set book_id to size of Books list + 1 (before adding Book)
		bookId = bookList.size() + 1;

		// add new Book to Books list
		ZUNUSED_Book newBook = new ZUNUSED_Book();
		newBook.setBookId(bookId);
		newBook.setAuthorId(authorId);
		newBook.setTitle(title);
		newBook.setIsbn(isbn);
		newBook.setPublished(published);
		bookList.add(newBook);
		
		// return new Book Id
		return bookId;
	}
	
	//not implemented in FakeDB
	public List<ZUNUSED_Author> removeBookByTitle(final String title) {
		List<ZUNUSED_Author> authors = new ArrayList<ZUNUSED_Author>();
		
		return authors;
	}
	

	// query that retrieves an Author based on author_id
	private ZUNUSED_Author findAuthorByAuthorId(int authorId) {
		for (ZUNUSED_Author author : authorList) {
			if (author.getAuthorId() == authorId) {
				return author;
			}
		}
		return null;
	}
	*/

	@Override
	public List<Graduate> findAdvisorGraduatesByAdvisorUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
