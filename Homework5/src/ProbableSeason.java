/* Samuel Bartolomew
 * Scott Weiss
 * Homework 5 part 4
 * Write a program that reads a temperature as a whole number from the keyboard and outputs a 
 * “probable” season (winter, spring, summer, or fall) depending on the temperature.
 * If the temperature is greater than or equal to 90, it is probably summer.
 * If the temperature is greater than or equal to 70 and less than 90, it is probably spring.
 * If the temperature is greater than or equal to 50 and less than 70, it is probably fall.
 * If the temperature is less than 50, it is probably winter.
 * If the temperature is greater than 110 or less than −5, then you should output that the 
 * temperature entered is outside the valid range.
 * Submit this as ProbableSeason.java.
 */

import java.util.Scanner;

public class ProbableSeason 
{
	public static void main(String[] args)
	{
		int tempF;
		String seasonGuess = "";
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter the temperature outside: ");
		tempF = scan.nextInt();
		if(tempF >= 90 && tempF < 110)
		{
			seasonGuess = "Summer";
		}
		else if(tempF >= 70)
		{
			seasonGuess = "Spring";
		}
		else if(tempF >= 50)
		{
			seasonGuess = "Fall";
		}
		else if(tempF < 50 && tempF > -5)
		{
			seasonGuess = "Winter";
		}
		
		if(tempF >= 110 || tempF <= -5)
		{
			System.out.println("The tempature you entered is out of the range.");
		}
		else
		{
			System.out.printf("%s is the season I am guessing.%n", seasonGuess);
		}
	}
}
