package Lab0;

import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.JOptionPane;

public class RandNums {
 /**
  * @param args
  */
	public static void main(String[] args)
	{
		int N;
		double sum = 0;
		final int LOW = 60;
		final int HIGH = 100;
		int max = LOW-1;
		int min = HIGH+1;
		
		Random myRand = new Random();
		while (true)
		{
			String input = JOptionPane.showInputDialog(null, "How many numbers:");
			try 
			{
				N = Integer.parseInt(input);	
				break;
			} 
			catch (Exception e) 
			{
				System.out.println("Exception: " + e);
				if(input==null)
				{
					System.exit(1);
				}
				JOptionPane.showMessageDialog(null, "Provide a whole numberic number. (EXAMPLE 4)");
			}
		}
		
		
		for(int i = 1; i<=N; i++)
		{
			int number = myRand.nextInt(HIGH-LOW+1)+LOW;
			System.out.println("Number " + i + " is " + number);
			sum += number;
			if (number < min)
			{
				min = number;
			}
			if (number > max)
			{
				max = number;
			}
		}
		DecimalFormat df = new DecimalFormat("0.000");
		
		System.out.println("The average is " + df.format(sum/N));
		JOptionPane.showMessageDialog(null, "The minimum is " + min + "\nThe maximum is " + max);
		
	}
}
