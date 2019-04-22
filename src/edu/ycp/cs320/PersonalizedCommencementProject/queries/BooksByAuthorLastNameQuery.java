package edu.ycp.cs320.PersonalizedCommencementProject.queries;

import java.util.List;
import java.util.Scanner;
/*
import edu.ycp.cs320.PersonalizedCommencementProject.model.ZUNUSED_Author;
import edu.ycp.cs320.PersonalizedCommencementProject.model.ZUNUSED_Book;
import edu.ycp.cs320.PersonalizedCommencementProject.model.ZUNUSED_Pair;
*/
import edu.ycp.cs320.PersonalizedCommencementProject.persist.DatabaseProvider;
import edu.ycp.cs320.PersonalizedCommencementProject.persist.IDatabase;

public class BooksByAuthorLastNameQuery {
	/*
	 	public static void main(String[] args) throws Exception {
	 
		
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		System.out.print("Enter an author's last name: ");
		String lastName = keyboard.nextLine();
		
		IDatabase db = DatabaseProvider.getInstance();
		List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> authorBookList = db.findAuthorAndBookByAuthorLastName(lastName);
		if (authorBookList.isEmpty()) {
			System.out.println("No books found for author <" + lastName + ">");
		}
		else {
			for (ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book> authorBook : authorBookList) {
				ZUNUSED_Author author = authorBook.getLeft();
				ZUNUSED_Book book = authorBook.getRight();
				System.out.println(author.getLastname() + "," + author.getFirstname() + "," + book.getTitle() + "," + book.getIsbn());
			}			
		}

	}
	*/
}