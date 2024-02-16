/*
 * Samuel Bartholomew
 * Professor: Scott Weiss
 * Homework 3 Part 3
 * 
 * Write a program that reads a file name from the keyboard. 
 * You should expect that the file name has one . 
 * (dot) character in it, separating the file name from the file extension. 
 * Retrieve the file extension and output it. For instance, if the user inputs index.html, 
 * you should output html; if the user inputs MyClass.java, you should output java. 
 * (Hint: Look at the list of String methods.) Your code should be in a file called 
 * GetExtension.java.
 */

import java.util.Scanner;

public class GetExtension {
	public static void main(String[] args)
	{
		//init variables
		int dotAt = 0;
		boolean printed = false;
		String file = "";
		Scanner scan = new Scanner(System.in); // construct scanner
		//Get the users input
		System.out.print("Please enter your file's name with the extension: ");
		file = scan.nextLine();
		dotAt = file.indexOf('.');
		String extension = file.substring(dotAt+1);
		if(dotAt != -1) 
		{
			printed = true;
			System.out.println("Your file type is a 'dot' " + extension);
		}	
		
		//if nothing was printed say the file name was wrong
		if(!printed)
		{
			System.out.println("Your file did not include a '.' so I could not get the file type.");
		}
	}
}
