/*
 * Samuel Bartholomew
 * Professor: Scott Weiss
 * Homework3 Part 2
 *  Write a Java program that prompts the user for a domain name. 
 *  Your program should then concatenate that name with www. and .com 
 *  in order to form an Internet domain name and output the result. 
 *  For instance, if the name entered by the user is yahoo, then the output will be www.yahoo.com 
 */

import java.util.Scanner;

public class FullDomain {
	public static void main(String[] args)
	{
		//init vars & construct scanner
		String domain = "";
		Scanner scan = new Scanner(System.in);
		//get the users next line
		System.out.print("Please enter a domain name: ");
		//add www. and .com
		domain = scan.nextLine();
		domain = "www."+domain+".com";
		//print to user
		System.out.println("The full domain you entered is : "+ domain);
		
		
		
	}
}
