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

public class TitleQuery {
	/*
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		System.out.print("Enter a title: ");
		String title = keyboard.nextLine();
		
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		List<ZUNUSED_Pair<ZUNUSED_Author, ZUNUSED_Book>> authorBookList = db.findAuthorAndBookByTitle(title);
		
		// check if anything was returned and output the list
		if (authorBookList.isEmpty()) {
			System.out.println("No books found with title <" + title + ">");
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
