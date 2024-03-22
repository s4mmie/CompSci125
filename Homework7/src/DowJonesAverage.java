/* Samuel Bartholomew
 * 
 * Homework 7 part 3
 * 
 * Write a program that inputs 7 double values from a file dja.txt that represent the Dow Jones Average for 7 days. 
 * Your program should output the lowest value for those 7 days and the number of the day on which the lowest value 
 * occurred. For this program, instead of setting the initial minimum value to the first value in the file, 
 * use Double.MAX_VALUE. 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DowJonesAverage 
{
	public static void main(String[] args)
	{
        double lowestValue = Double.MAX_VALUE;
        int lowestDay = 0;
        String currentDirectory = System.getProperty("user.dir");
        currentDirectory = currentDirectory + File.separator +"src"+ File.separator+"dja.txt";
        System.out.println(currentDirectory);
        try 
        {
            //Open file
            File file = new File(currentDirectory);
            Scanner scanner = new Scanner(file);

            //Read values from file
            int day = 1; //where to start scanning
            
            //Loop through file until no more doubles
            while (scanner.hasNextDouble()) 
            {
                double value = scanner.nextDouble();
                
                //If current value less than lowestValue
                if (value < lowestValue) 
                {
                    lowestValue = value;
                    lowestDay = day;
                }

                //Next line
                day++;
            }
            System.out.println("Lowest value: " + lowestValue);
            System.out.println("Day of lowest value: " + lowestDay);
        }
        catch(FileNotFoundException e)
        {
        	System.out.println("File not found.");
        }
	}
}
