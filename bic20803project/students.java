package bic20803project;

//Java Program to Illustrate students Class
//To Do all the Operations related to students:
//add Students,check-in books,check out books,ValidStudent

//Importing required classes
import java.util.Scanner;

//Class
public class students {

	// Creating objects of Scanner and students class
	Scanner input = new Scanner(System.in);
	Student theStudents[] = new Student[50];

	public static int count = 0;

	// Method 1
	// To add books
	public void addStudent(Student serial)
	{
		for (int i = 0; i < count; i++) {

			if (serial.regNum.equalsIgnoreCase(theStudents[i].regNum)) {

				// Print statement
				System.out.println("\nStudent of Registration Number " + serial.regNum + " is Already Registered.");

				return;
			}
		}

		if (count <= 50) 
		{
			theStudents[count] = serial;
			count++;
		}
	}

	// Method 2
	// Displaying all students
	public void showAllStudents()
	{
		// Printing student name and
		// corresponding registered number
		System.out.println("Student Name\t\tMatric Number\t\tRegistration Number");
		for (int i = 0; i < count; i++) 
		{
			System.out.println(theStudents[i].studentName + "\t\t\t" + theStudents[i].matricID + "\t\t\t" + theStudents[i].regNum);
		}
	}

	// Method 3
	// To check the Student
	public int isStudent()
	{
		Scanner input2 = new Scanner(System.in);
		System.out.println("\nTo proceed with this process, please ENTER your student's Registration Number below\n");
		// Display message only
		System.out.println("Enter Registration Number:");

		String regNum = input2.nextLine();

		for (int i = 0; i < count; i++)
		{
			if (theStudents[i].regNum.equalsIgnoreCase(regNum)) 
			{
				return i;
			}
		}

		// Print statements
		System.out.println("\nStudent is not Registered.");
		System.out.println("\nGet Registered First.");

		return -1;
	}

	// Method 4
	// Borrow the book
	public void checkOutBook(books book)
	{
		int studentIndex = this.isStudent();

		if (studentIndex != -1)
		{

			book.showAllBooks();
			book b = book.checkOutBook();
			
			System.out.println("\t\t\t--------Checking Out--------");
			
			if (b != null)
			{
				b.borrowStudent(theStudents[studentIndex]);
				if (theStudents[studentIndex].booksCount <= 3)
				{
					//System.out.println("adding book");
					for(int i = 0; i < 3; i++) {
						if(theStudents[studentIndex].borrowedBooks[i] == null){
							theStudents[studentIndex].borrowedBooks[i] = b;
							 theStudents[studentIndex].booksCount++;
							break;
						}
					}
					return;
				}
				else
				{
					System.out.println("Student CANNOT Borrow more than 3 Books.");
					return;
				}
			}
			System.out.println("\nThe Book is OUT OF QUANTITY.");
		}
	}

	// Method 5
	// Return the book
	public void checkInBook(books book)
	{
		int studentIndex = this.isStudent();
		if (studentIndex != -1) {
			if(theStudents[studentIndex].borrowedBooks[0] != null) {
				// Printing credentials corresponding to student
				System.out.println("Serial.No\t\t\tBook Name\t\t\tAuthor Name");
	
				Student serial = theStudents[studentIndex];
	
				for (int i = 0; i < serial.booksCount; i++)
				{
					if(serial.borrowedBooks[i] != null) {
						System.out.println(serial.borrowedBooks[i].serialNo + "\t\t\t" + serial.borrowedBooks[i].bookName + "\t\t\t\t" + serial.borrowedBooks[i].authorName);
					}
				}
	
				// Display message only
				System.out.println("\nEnter Serial Number of Book to be Returned: ");
	
				long serialNo = input.nextLong();
	
				for (int i = 0; i < serial.booksCount; i++)
				{
					if (serialNo == serial.borrowedBooks[i].serialNo)
					{
						book.checkInBook(serial.borrowedBooks[i]);
						serial.borrowedBooks[i] = null;
						if(serial.borrowedBooks[1] != null) {
							serial.borrowedBooks[0] = serial.borrowedBooks[1];
							serial.borrowedBooks[1] = null;
						}
						if(serial.borrowedBooks[2] != null) {
							serial.borrowedBooks[1] = serial.borrowedBooks[2];
							serial.borrowedBooks[2] = null;
						}
						System.out.println("\nReturn Successfully, Thank you. ");
						
						return;
					}
				}
	
				System.out.println("\nBook of Serial.No " + serialNo + " was not found");
			}else {
				System.out.println("\nNO books was borrowed...");
			}
		}
	}
}
