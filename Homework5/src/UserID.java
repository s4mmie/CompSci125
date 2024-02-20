/* Samuel Bartholomew
 * Scott Weiss
 * Homework5 part 3
 * Write a program that takes a word as input from the keyboard, representing a user ID. 
 * (Often, websites place constraints on user IDs.) Your program should do the following:
 * if the user ID contains between 6 and 10 characters inclusive, 
 * then output “Welcome, ___” (where ___ is replaced with whatever ID the user enters); 
 * otherwise, output “Sorry, user ID invalid”. This should be submitted as UserID.java.
 */

import java.util.Scanner;

public class UserID 
{
	public static void main(String[] args)
	{
		String user;
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter your user name: ");
		user = scan.nextLine();
		if(user.length() >=6 && user.length()<=10)
		{
			System.out.printf("Welcome, %s%n", user);
		}
		else
		{
			System.out.println("Sorry, user ID is invalid.");
		}
	}
}
