package bic20803project;

import java.util.Scanner;

// use of Inheritance 
public class librarian extends user {
	private int code = 1234; // private variable is used to to design a class that is completely enclosed
	
	Scanner input = new Scanner(System.in);

// constructor
public librarian(){
	
	// Print statement
	System.out.println("Enter Librarian Name: ");

	// this keywords refers to current instance
	this.studentName = input.nextLine();
		
	System.out.println("Enter Matric Number: ");
	this.matricID = input.nextLine();
	
	int c; // set a constraint for code by using looping
	do {
		System.out.println("Enter Code: ");
		c = input.nextInt();
		if(c != code) {
			System.out.println("NOT VALID CODE!!! Please try again");
		}
	}while(c != code);
 }

// Mutators (setters) - A method in a class 
// to assign value of private variables
public void setCode(int code) {
	this.code = code;
	}

// Accessors (getters) - A method in a class 
// to display returning value of variables
public int getCode() {
	return code;
	}
}