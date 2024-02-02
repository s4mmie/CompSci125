//Samuel Bartholomew
//1/31/24
//Professor: Scott Weiss
/*Write a complete Java program called Problem3.java that converts 3, 6.5, 
 * and 20 inches to millimeters. Note that one inch equals 25.4 millimeters. 
 * The program should do the calculations. It should output three statements of the form 
 * "___ inches equals ___ millimeters" where the appropriate numbers fill the blanks.
 */

import java.text.DecimalFormat;

public class Problem3 {
	public static void main(String[] args)
	{
		double inch1 = 3;
		double inch2 = 6.5;
		double inch3 = 20;
		
		DecimalFormat dFormat = new DecimalFormat("0.00");
		System.out.println(inch1 + " inches is equal to " + dFormat.format(inch1*25.4) +" milimeters.");
		System.out.println(inch2 + " inches is equal to " + dFormat.format(inch3*25.4) +" milimeters.");
		System.out.println(inch3 + " inches is equal to " + dFormat.format(inch3*25.4) +" milimeters.");
	}
}
