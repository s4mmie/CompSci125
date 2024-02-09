/*
 * Samuel Bartholomew
 * Professor: Scott Weiss
 * Homework 3 part 4
 * Write a Java program that reads a sentence from the keyboard. 
 * The sentence has been encrypted so that the message consists of only the 
 * first five characters with even-numbered indexes. All other characters 
 * should be discarded. Decrypt the sentence and output the result. 
 * For example, if the user inputs “Hiejlzl3ow”, your output should be Hello. 
 * Call your file Decrypt.java. 
 */

import java.util.Scanner;

public class Decrypt {
	public static void main(String[] main)
	{
		//init vars
		String sentence = ""; 
		String decrypt = "";
		Scanner scan = new Scanner(System.in); //construct
		//Take user sentence
		System.out.print("Please provide your encrypted message: ");
		sentence = scan.nextLine();
		
		for(int i = 0; i < sentence.length(); i++)
		{
			if(i % 2 == 0)
			{
				decrypt += sentence.charAt(i);
			}
		}
		System.out.println("Your decrypted message is: " + decrypt);
	}
}
