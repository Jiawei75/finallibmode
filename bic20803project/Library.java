package bic20803project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;

//Java Program to Illustrate Application CLass
//To Create The Menu For the Program

//Importing required classes
import java.util.Scanner;

//Class
public class Library {

	// Main driver method
	public static void main(String[] args) throws IOException
	{
		// Creating object of Scanner class
		// to take input from user
		Scanner input = new Scanner(System.in);
		
		librarian lb = new librarian();
		System.out.println(lb.getCode() + " ---> LOGIN SUCCESSFULLY");
		
		// Displaying menu
		System.out.println("\n**********************Welcome to the UTHM PTTA Library!**********************");
		System.out.println("                     Select From The Following Options:			 ");
		System.out.println("*****************************************************************************");
		
		// Creating object of book class
		books ob = new books();
		
		// Creating object of students class
		students obStudent = new students();
				
		// Read data from student.txt
		Scanner readStudentFile = new Scanner(new File("student.txt"));
		int fileloop = 0;
		while (readStudentFile.hasNextLine()) {
			String a = readStudentFile.nextLine();
			String[] b = a.split("/",0);
			
			Student x = new Student("");
			
			// can direct acess data through Student 
			x.regNum = (b[0]);
			x.studentName = b[1]; 
			x.matricID = (b[2]);
			
			obStudent.addStudent(x);
			
			fileloop++;
		}	

		// Read data from booksList.txt
		Scanner readBooksFile = new Scanner(new File("booksList.txt"));
		fileloop = 0;
		while (readBooksFile.hasNextLine()) {
			String a = readBooksFile.nextLine();
			String[] b = a.split("/",0);
			
			book z = new book("");
			
			z.setSerialNo(Long.valueOf(b[0]));
			z.setBookName(b[1]);
			z.setAuthorName(b[2]);
			z.setBookQty(Integer.valueOf(b[3]));
			z.setBookQtyCopy(Integer.valueOf(b[4]));
			
			for(int j = 5; j < 7 ; j++) {  // because place of history of array is start from no.5 in file.txt
				if(b.length-1 >= j) {
					
					// nested for-loop is to used to find data student
					for(int i = 0; i < obStudent.theStudents.length; i++) {
						if(obStudent.theStudents[i] != null) {
							if(b[j].equalsIgnoreCase(obStudent.theStudents[i].regNum)) {
								z.historyStudent[z.history] = obStudent.theStudents[i];
									z.history++;
							}
						}
					}
				}
			}
			ob.addBook(z);
			fileloop++;
		}
		
		// Read the history record of borrowing by student
		fileloop = 0;
		Scanner readStudentFile1 = new Scanner(new File("student.txt"));
		while (readStudentFile1.hasNextLine()) {
			String a = readStudentFile1.nextLine();
			String[] b = a.split("/",0);
			
			for(int j = 3; j < 5 ; j++) {
				if(b.length-1 >= j) {
					for(int i =0; i<obStudent.theStudents[fileloop].borrowedBooks.length ; i++) {
						if(ob.theBooks[i] != null) {
							if(b[j].equalsIgnoreCase(String.valueOf(ob.theBooks[i].serialNo))) {
								obStudent.theStudents[fileloop].borrowedBooks[obStudent.theStudents[fileloop].booksCount] = ob.theBooks[i] ;
									obStudent.theStudents[fileloop].booksCount++;
							}
						}
					}
				}
			}
			fileloop++;
		}
		
		int choice;
		int searchChoice;

		// Creating menu
		// using do-while loop
		do {
			ob.dispMenu();
			choice = input.nextInt();

			// Switch case
			switch (choice) {

			// Case 
			case 1:
				book b = new book();
				ob.addBook(b);
				break;

			// Case 
			case 2:
				ob.upgradeBookQty();
				break;

			// Case 
			case 3:
				do {
				System.out.println(" Press 1 to Search with Book Serial No. ");
				System.out.println(" Press 2 to Search with Book's Author Name. ");
				
				// constraint of searching book
				searchChoice = input.nextInt();
				if(!(searchChoice == 1 || searchChoice == 2)) {
					System.out.println("Error Input !!!");
					input.nextLine();
					}
				}while(!(searchChoice == 1 || searchChoice == 2));

				// Nested switch
				switch (searchChoice) {
				
				// Case 
				case 1:
					ob.searchBySno();
					break;
				
				// Case 
				case 2:
					ob.searchByAuthorName();
				}
				break;

			// Case
			case 4:
				ob.showAllBooks();
				break;

			// Case
			case 5:
				Student s = new Student();
				obStudent.addStudent(s);
				break;

			// Case
			case 6:
				obStudent.showAllStudents();
				break;
				
			// Case
			case 7:
				ob.borrowRecord(ob.searchBook());
				break;
				
			case 8:
				// Print statement
				System.out.println("\t\t----------THANK YOU AND WELCOME AGAIN TO UTHM PTTA LIBRARY----------");
				break;
				
			// Default case that will execute for sure
			// if above cases does not match
			default:
				System.out.println("ERROR INPUT !!! PLEASE Re-enter again...\n");
			}
			
			// Write the books data into files 
			FileWriter BooksFile = new FileWriter("booksList.txt");
			for(int i = 0 ; i < ob.theBooks.length ; i++) {
				if(ob.theBooks[i] != null) {
					String history = "";
					for(int j =0; j<ob.theBooks[i].historyStudent.length; j++) {
						if(ob.theBooks[i].historyStudent[j] != null) {
							history += "/" + ob.theBooks[i].historyStudent[j].regNum ;
						}
					}
					BooksFile.write(ob.theBooks[i].serialNo + "/" + ob.theBooks[i].bookName + "/" + ob.theBooks[i].authorName+ "/"  + ob.theBooks[i].bookQty + "/" + ob.theBooks[i].bookQtyCopy + history + "\n");
				}else {
					break;
				}
			}	
			BooksFile.close();
			
			// Write the Student data into files 
			FileWriter StudentFile = new FileWriter("student.txt");
			for(int i = 0 ; i < obStudent.theStudents.length ; i++) {
				if(obStudent.theStudents[i] != null) {
					String history = "";
					for(int j =0; j<obStudent.theStudents[i].borrowedBooks.length ;j++) {
						if(obStudent.theStudents[i].borrowedBooks[j]!= null) {
							history += "/" + obStudent.theStudents[i].borrowedBooks[j].serialNo;
						}
					}
					StudentFile.write(obStudent.theStudents[i].regNum + "/" + obStudent.theStudents[i].studentName + "/" + obStudent.theStudents[i].matricID + history + "\n");
				}else {
				break;
				}
			}
			StudentFile.close();
			
		// Checking condition at last where we are
		// checking case entered value is not zero
		}while (choice != 8);
	}
}
