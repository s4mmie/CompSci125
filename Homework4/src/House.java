/*Samuel Bartholomew
 * Scott Weiss
 * Homework 4 part 4
 * 
 * Write a program that draws a house.  
 * Your house should have a triangular roof, at least two windows, and a door. 
 * Call the class House. Use at least three different colors. 
 * The location and size should be controlled by variables in the code. 
 * Changing those variables should change how the house is drawn. 
 */

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class House extends JFrame {
	public House() // constructor
	{
		setSize(800,800); // pick a size for the window
		setVisible(true); // make it so we can see the window
	}


	public void paint(Graphics g)
	{
		final int houseX = 150; //house top left and right with how wide and tall
		final int houseY = 200;
		final int houseWidth = (int)(houseX * 2.5);
		final int houseHeight = (int)(houseY * 2);
		
		final int win1X = houseX+50; //window 1& 2 X+Y and the width and height
		final int win1Y = houseY+60;
		final int win2X = houseX+250;
		final int win2Y = houseY+60;
		final int winWidth = houseX/2;
		final int winHeight = houseY/2;
		//door width height x y
		final int doorWidth = (int)(houseWidth/3);
		final int doorX = houseX+(int)(houseWidth/2)-(int)(doorWidth/2);
		final int doorY = houseY+houseHeight - (int)(houseHeight/2.5);
		final int doorHeight = houseY+houseHeight-doorY;
		//roof and the points to draw the roof
		final int roofHeight = houseY - (int)(houseHeight/3);
		final int[] xPoints = { houseX, houseX + (int)(houseWidth/2), houseX+houseWidth};
		final int[] yPoints = { houseY, roofHeight, houseY};
		
		g.setColor(Color.WHITE);
		g.fillRect(0,0, getWidth(), getHeight());//draw blank canvas
		
		g.setColor(Color.GRAY);
		g.fillRect(houseX, houseY, houseWidth, houseHeight);//draw house wall
		
		g.setColor(Color.RED);
		g.fillPolygon(xPoints, yPoints, 3);//draw house roof
		
		g.setColor(Color.BLACK);
		g.drawRect(win1X, win1Y, winWidth, winHeight);//draw window 1
		g.drawRect(win2X, win2Y, winWidth, winHeight); //draw window 2
		
		g.fillRect(doorX, doorY, doorWidth, doorHeight);//draw door
		
		g.drawOval(200,200,200,200);
	}
	
	
	public static void main(String[] args) 
	{
		House house = new House();
	}
}
