//Samuel Bartholomew
//1/31/2024
//Professor: Scott Weiss

/*Write a complete Java program called Program4.java
 * that reads two words representing passwords from the 
 * ­keyboard and outputs the number of characters in the 
 * smaller of the two. For example, if the two words are open 
 * and sesame, then the output should be 4, the length of the 
 * shorter word, open. Hint: use the min function. Do not use 
 * techniques we haven't discussed in class.
 */

import javax.swing.JOptionPane;

public class Problem4 {
	
	public static void main(String[] args)
	{
		String input = JOptionPane.showInputDialog(null, "Please provide your first word:");
		String input2 = JOptionPane.showInputDialog(null, "Please provide your second word:");
		System.out.println("The length of the smaller word is: " + Math.min(input.length(),input2.length()));
	}
}
