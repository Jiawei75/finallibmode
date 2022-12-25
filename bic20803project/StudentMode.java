package bic20803project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StudentMode {
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		
		// Displaying menu
		System.out.println("\n**********************Welcome to the UTHM PTTA Library!**********************");
		System.out.println("                          You are now in STUDENT MODE:		               	 ");
		System.out.println("******************************************************************************");

		books ob = new books();
		students obStudent = new students();

		int choice;
		int searchChoice;
		
		try {
			File file1 = new File("student.txt");
			file1.createNewFile();
		}catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		
		//Read student from the file
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
		
		//Read booksList from the file
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
					for(int i =0; i<obStudent.theStudents.length; i++) {
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
					for(int i = 0; i < obStudent.theStudents[fileloop].borrowedBooks.length ; i++) {
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
			
		do {
			System.out.println("-------------------------------------------------------------------------------------------------------");
			System.out.println("Press 1 to Borrow Book. ");
			System.out.println("Press 2 to Return Book.");
			System.out.println("Press 3 to Exit Application.");
			System.out.println("-------------------------------------------------------------------------------------------------------");

			choice = input.nextInt();
			
			switch (choice) {
				// Case
				case 1:
					obStudent.checkOutBook(ob);
					break;

				// Case
				case 2:
					obStudent.checkInBook(ob);
					break;
					
				// Case
				case 3: 
					break;
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
			
		}while (choice != 3);
		
		System.out.println("\t\t\t----------THANK YOU AND WELCOME AGAIN TO UTHM PTTA LIBRARY----------");
	}
}


