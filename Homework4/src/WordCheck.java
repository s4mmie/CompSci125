/* Samuel Bartholomew
 * Scott Weiss
 * Homework 4 part 5
 * Write a program that has the user enter a word. 
 * Have the program output YES if the word begins and ends with vowels (AEIOU) 
 * or NO if it does not. Call your class WordCheck.
 */

import java.util.Scanner;

public class WordCheck {
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		System.out.println("This program will check to see if the first and last letter in your word are vowels.");
		System.out.print("Please enter your word: ");
		String word = scan.nextLine();//get word from user
		int wordLength = word.length();//set the word elgnth to an int
		boolean isWord = false;//see if the word is valid of starting and ending with AEIOU
		if(word.indexOf(' ') != -1)//check to see if a space is in the word and exit if so
		{
			System.out.println("The word contains a space and is not a single word.");
			System.exit(1);
		}
		//check uppercase character at first index and see if it is AEIOU
		if(Character.toUpperCase(word.charAt(0)) == 'A' || Character.toUpperCase(word.charAt(0)) == 'E' || Character.toUpperCase(word.charAt(0)) == 'I' || Character.toUpperCase(word.charAt(0)) == 'O' || Character.toUpperCase(word.charAt(0)) == 'U')
		{//check to see uppercase character is at the last index and see if its AEIOU
			if(Character.toUpperCase(word.charAt(wordLength-1)) == 'A' || Character.toUpperCase(word.charAt(wordLength-1)) == 'E' || Character.toUpperCase(word.charAt(wordLength-1)) == 'I' || Character.toUpperCase(word.charAt(wordLength-1)) == 'O' || Character.toUpperCase(word.charAt(wordLength-1)) == 'U')
			{
				System.out.printf("Yes the word '%s' starts and ends with a vowel!", word);
				isWord = true;//the word starts and ends with a vowel
			}
		}
		if(!isWord)//the word doesnt start and end with a vowel
		{
			System.out.printf("No the word '%s' does not start and end with a vowel.", word);
		}
	}
}
