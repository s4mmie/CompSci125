/*
 * Samuel Bartholomew
 * Professor: Scott Weiss
 * Homework 3 part 5
 * Write a Java program that draws the flag of a country other than the United States. 
 * The picture should have some text giving the name of the country. 
 * I recommend drawing a flag of Italy, Japan, or Germany, but feel free to shoot for 
 * something more complex. Call your file Flag.java.
 */



import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import javax.swing.JFrame;


public class Flag extends JFrame {
	
public Flag() // constructor
	{
		setSize(500, 500); // pick a size for the window
		setVisible(true); // make it so we can see the window
	}


	public void paint(Graphics g)
	{

		final int cornerX = 100; // x-coordinate of upper left
		final int cornerY = 50; // y-coordinate of upper left
		final int stripeHeight = 8; // height of a stripe
		final int flagWidth = (int)(2*13*stripeHeight);
		final int flagHeight = (int)(stripeHeight*13);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.BLACK);
		g.drawLine(cornerX, cornerY, cornerX, cornerY + 39*stripeHeight); //flag pole
		g.drawRect(cornerX, cornerY, flagWidth, flagHeight); //flag outline
		
		for(int i = 0; i < 13;i++)
		{
			if(i % 2 ==0)
			{
				g.setColor(Color.RED);
				g.fillRect(cornerX+1, cornerY+1+(i*stripeHeight),  flagWidth-1, stripeHeight-1);
			}
		}
		
		g.setColor(Color.BLUE);
		g.fillRect(cornerX+1, cornerY+1, (int)(flagWidth*.4), (int)(flagHeight*.4725)-1);
		
		g.setColor(Color.WHITE);//drawing 5x6 stars
		for(int x = 0; x < 6; x++)
		{
			for(int y = 0; y < 5; y++)
			{
				g.fillRect(cornerX+(int)(flagWidth*.4*.06)+(int)(x*(flagWidth*.4*.15)), cornerY+ (int)(flagHeight*.05) + (int)(y*flagHeight*.4725*.2),6,2);
			}
		}
		
		for(int x = 0; x < 5; x++)//drawing the inner 4x5 stars
		{
			for(int y = 0; y < 4; y++)
			{
				g.fillRect(cornerX+(int)(flagWidth*.4*.12)+(int)(x*(flagWidth*.4*.15)), cornerY+ (int)(flagHeight*.1) + (int)(y*flagHeight*.4725*.2),6,2);
			}
		}
		g.setColor(Color.BLACK);
		g.drawString("This is a flag of The United States of America.", cornerX, cornerY+150);
		/*
		final int cornerX = 100; // x-coordinate of upper left
		final int cornerY = 50; // y-coordinate of upper left
		final int stripeHeight = 8; // height of a stripe
		final int flagWidth = (int)(2*13*stripeHeight);
		final int flagHeight = (int)(stripeHeight*13);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.BLACK);
		g.drawLine(cornerX, cornerY, cornerX, cornerY + 39*stripeHeight); //flag pole
		g.drawRect(cornerX, cornerY, flagWidth, flagHeight); //flag outline
		
		g.fillRect(cornerX, cornerY, flagWidth, flagHeight/3);
		
		g.setColor(Color.RED);
		g.fillRect(cornerX, cornerY+(int)(flagHeight/3), flagWidth, flagHeight/3);
		
		g.setColor(Color.YELLOW);
		g.fillRect(cornerX, cornerY+(int)(flagHeight/3)+(int)(flagHeight/3), flagWidth, flagHeight/3+2);
		
		g.setColor(Color.BLACK);
		g.drawString("This is a flag of Germany.", cornerX, cornerY+150);*/
	}
	
	
	public static void main(String[] args) 
	{
		Flag flag = new Flag();
	}
}
