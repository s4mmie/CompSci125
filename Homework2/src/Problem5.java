//Samuel Bartholomew
//1/31/2024
//Professor: Scott Weiss

/*Write a complete Java program called Problem5.java 
 * that has the user enter five grades. The program 
 * should output the largest of the five grades. 
 * Don't use techniques we haven't discussed in class. 
 * Hint: Use the max function. You'll need to call it four times.
 */

import javax.swing.JOptionPane;

public class Problem5 {
	public static void main(String[] args)
	{
		String input;
		double grade1 = 0;
		double grade2 = 0;
		double grade3 = 0;
		double grade4 = 0;
		double grade5 = 0;
		
		input = JOptionPane.showInputDialog(null, "You will be entering 5 grades \n and see your highest grade \n Please enter your first grade (EXAMPLE 92): ");
		try
		{
			grade1 = Double.parseDouble(input);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"The grade you entered above is not a number\nThe program will now exit.");
			System.exit(1);
		}
		
		input = JOptionPane.showInputDialog(null, "Please Enter the second grade: ");
		try
		{
			grade2 = Double.parseDouble(input);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"The grade you entered above is not a number\nThe program will now exit.");
			System.exit(1);
		}
		
		
		input = JOptionPane.showInputDialog(null, "Please enter the third grade: ");
		try
		{
			grade3 = Double.parseDouble(input);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"The grade you entered above is not a number\nThe program will now exit.");
			System.exit(1);
		}
		
		input = JOptionPane.showInputDialog(null, "Please enter the fourth grade: ");
		try
		{
			grade4 = Double.parseDouble(input);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"The grade you entered above is not a number\nThe program will now exit.");
			System.exit(1);
		}
		
		input = JOptionPane.showInputDialog(null, "Please enter the fifth grade: ");
		try
		{
			grade5 = Double.parseDouble(input);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"The grade you entered above is not a number\nThe program will now exit.");
			System.exit(1);
		}
		
		JOptionPane.showMessageDialog(null,"The highest grade is " + Math.max(grade1, Math.max(grade2, Math.max(grade3, Math.max(grade4, grade5)))));
	
	}
}
