package bic20803project;

//Java Program to Illustrate books class
//To Do all the Operations related to Books such as
//add, check-in, check-out,Valid books,Update books

//Importing required classes
import java.util.Scanner;

//CLass
public class books {

	// Class data members
	book theBooks[] = new book[50];
	public static int count = 0;
	public static long serialNo;

	Scanner input = new Scanner(System.in);

	// Method 1
	// To compare books
	// This method to avoid adding to the same book
	public int compareBookObjects(book b1, book b2)
	{
		// If book name matches
		if (b1.bookName.equalsIgnoreCase(b2.bookName)) 
		{
			// Printing book exists
			System.out.println("Book of this Name Already Exists.");
			return 0;
		}

		// if book serial matches
		if (b1.serialNo == b2.serialNo)
		{
			// Print book exists
			System.out.println("Book of this Serial No Already Exists.");
			return 0;
		}
		return 1;
	}

	// Method 2
	// To add book
	// set limit the maximum number of additions to 50 books
	public void addBook(book b)
	{

		for (int i = 0; i < count; i++) {

			if (this.compareBookObjects(b, this.theBooks[i])== 0)
				return;
		}

		if (count < 50)
		{
			theBooks[count] = b;
			count++;
		}
		else 
		{
			System.out.println("No Space to Add More Books.");
		}
	}

	// Method 3
	// To search book by serial number
	public void searchBySno()
	{
		// Display message
		System.out.println("\t\t\t----------SEARCH BY SERIAL NUMBER----------\n");
		
		// Class data members
		System.out.println("Enter Serial No of Book:");
		serialNo = input.nextLong();

		int flag = 0;  // flag variable is used to let the program know that a certain condition has met, in between 0(FALSE) and 1(TRUE)
		System.out.println("Serial.No\t\tName\t\tAuthor\t\tAvailable Qty\t\tTotal Qty");

		for (int i = 0; i < count; i++) 
		{
			if (serialNo == theBooks[i].serialNo) 
			{
				System.out.println(theBooks[i].serialNo + "\t\t" + theBooks[i].bookName + "\t\t" + theBooks[i].authorName + "\t\t" + theBooks[i].bookQtyCopy + "\t\t\t" + theBooks[i].bookQty);
				flag++;
			}
		}
		if (flag != 0) {
			System.out.println("\nNo.Book for Serial.No " + serialNo + " was found.");
		}else {
			System.out.println("NOT Data was found...");
		}
	}

	// Method 4
	// To search author by name
	public void searchByAuthorName()
	{

		// Display message
		System.out.println("\t\t\t----------SEARCH BY AUTHOR'S NAME----------");
		Scanner in = new Scanner(System.in); // due to the full input data, so can create another scanner to store input data

		System.out.println("Enter Author Name:");
		String authorName = in.nextLine();
		
		int flag = 0;

		System.out.println("Serial.No\t\tName\t\tAuthor\t\tAvailable Qty\t\tTotal Qty");

		for (int i = 0; i < count; i++) {

			// if author matches any of its book
			if (authorName.equalsIgnoreCase(theBooks[i].authorName)) 
			{
				// Print below corresponding credentials
				System.out.println(theBooks[i].serialNo + "\t\t" + theBooks[i].bookName + "\t\t" + theBooks[i].authorName + "\t\t" + theBooks[i].bookQtyCopy + "\t\t\t" + theBooks[i].bookQty);
				flag++;
			}
		}

		if (flag != 0) {
			System.out.println("\nThe Book's Author Name " + authorName + " was found.");
		}else {
			System.out.println("NOT Data was found...");
		}
	}

	// Method 5
	// To display all books
	public void showAllBooks()
	{
		System.out.println("\t\t\t----------SHOWING ALL BOOKS----------\n");
		System.out.println("Serial.No\t\tName\t\tAuthor\t\tAvailable Qty\t\tTotal Qty");

		for (int i = 0; i < count; i++)
		{
			System.out.println(theBooks[i].serialNo + "\t\t" + theBooks[i].bookName + "\t\t" + theBooks[i].authorName + "\t\t" + theBooks[i].bookQtyCopy + "\t\t\t" + theBooks[i].bookQty);
		}
	}

	// Method 6
	// To edit the book
	public void upgradeBookQty()
	{
		System.out.println("\t\t\t----------UPGRADE QUANTITY OF A BOOK----------\n");
		System.out.println("Enter Serial No of Book");

		serialNo = input.nextLong();

		for (int i = 0; i < count; i++) {

			if (serialNo == theBooks[i].serialNo)
			{
				// Display message
				System.out.println("Enter No of Books to be Added:");

				int addingQty = input.nextInt();
				theBooks[i].bookQty += addingQty;
				theBooks[i].bookQtyCopy += addingQty;

				return;
			}
		}
	}

	// Method 7
	// To create menu
	public void dispMenu()
	{
		// Displaying menu
		System.out.println("----------------------------------------------------------------------------------------------------------");
		System.out.println("Press 1 to Add new Book.");
		System.out.println("Press 2 to Upgrade Quantity of a Book.");
		System.out.println("Press 3 to Search a Book.");
		System.out.println("Press 4 to Show All Books.");
		System.out.println("Press 5 to Register Student.");
		System.out.println("Press 6 to Show All Registered Students.");
		System.out.println("Press 7 to Book Borrowing Record.");
		System.out.println("Press 8 to Exit Application.");
		System.out.println("-------------------------------------------------------------------------------------------------------");
	}

	// Method 8
	// To search the library
	public int isAvailable(long serialNo)
	{
		for (int i = 0; i < count; i++) 
		{
			if (serialNo == theBooks[i].serialNo) 
			{
				if (theBooks[i].bookQtyCopy > 0) 
				{
					System.out.println("Book is Available.");
					return i;
				}
				System.out.println("Book is Unavailable");
				return -1;
			}	
		}
		System.out.println("No Book of Serial Number " + serialNo + " NOT Available in Library.");
		return -1;
	}

	// Method 9
	// To remove the book from the library
	public book checkOutBook()
	{
		System.out.println("\nEnter the Serial No of the Book to be Borrowed.");
		serialNo = input.nextLong();

		int bookIndex = isAvailable(serialNo);

		if (bookIndex != -1) 
		{
			theBooks[bookIndex].bookQtyCopy--;
			return theBooks[bookIndex];
		}
		return null;
	}

	// Method 10
	// To add the Book to the Library
	public void checkInBook(book b)
	{
		for (int i = 0; i < count; i++) 
		{
			if (b.equals(theBooks[i])) 
			{
				theBooks[i].bookQtyCopy++;
				return;
			}
		}
	}
	
	// Method 11
	// To search the history Book Record
	
	public book searchBook() {
		System.out.println("\t\t----------ENTER BY SERIAL NUMBER----------\n");
		
		// Class data members
		System.out.println("Enter Serial No of Book:");
		serialNo = input.nextLong();

		int flag = 0;

		for (int i = 0; i < count; i++) 
		{
			if (serialNo == theBooks[i].serialNo) 
			{
				return theBooks[i];
			}
		}
		return null;
	}
	
	public void borrowRecord(book book) {
		if(book != null) {
			System.out.println("\t\t----------Borrow Record----------\n");
			for (int i = 0; i < book.history; i++) 
			{
				System.out.printf("%d) Name: ",i+1);
				System.out.println(book.historyStudent[i].studentName);
			}
		}
		else 
		{
			System.out.println("There is no this book in library");
		}
	}
}