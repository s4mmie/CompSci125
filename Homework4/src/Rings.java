	/*
	 * Samuel Bartholomew
	 * Professor: Scott Weiss
	 * Homework 4 part 3
	 */

	import java.awt.*;
	import javax.swing.*;
	
public class Rings extends JFrame {

	public Rings() // constructor
		{
			setSize(500, 500); // pick a size for the window
			setVisible(true); // make it so we can see the window
		}


		public void paint(Graphics g)
		{
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			
			g2.setStroke(new BasicStroke(4f));
			
			final int ringRadius = 100;
			final int ring1X = 50;
			final int ring2X = (int)(ring1X*2);
			final int ring3X = (int)(ring1X*3.2);
			final int ring4X = (int)(ring1X*4.2);
			final int ring5X = (int)(ring1X*5.4);
			
			final int ringTopY = 150;
			final int ringBottomY = (int)(150*1.4);
			
			g.drawRect(0, 0, getWidth(), getHeight());
			
			//Blue ring drawn
			g.setColor(Color.BLUE);
			g.drawOval(ring1X, ringTopY, ringRadius, ringRadius);
			
			//Yellow ring drawn
			g.setColor(Color.YELLOW);
			g.drawOval(ring2X, ringBottomY , ringRadius, ringRadius);
			
			//Blue Over drawn
			g.setColor(Color.BLUE);								//right-middle 45 degrees
			g2.drawArc(ring1X, ringTopY, ringRadius, ringRadius, 320, 45);
			
			//Black ring drawn
			g.setColor(Color.BLACK);
			g.drawOval(ring3X, ringTopY, ringRadius, ringRadius);
			
			//Yellow over drawn
			g.setColor(Color.YELLOW);								//right-upper 40 degrees
			g2.drawArc(ring2X, ringBottomY, ringRadius, ringRadius, 40, 40);
			
			//Green ring drawn
			g.setColor(Color.GREEN);
			g.drawOval(ring4X, ringBottomY, ringRadius, ringRadius);
			
			//Black over drawn
			g.setColor(Color.BLACK);								//right-middle 40 degrees
			g2.drawArc(ring3X, ringTopY, ringRadius, ringRadius, 320, 40);
			
			//Red ring drawn
			g.setColor(Color.RED);
			g.drawOval(ring5X, ringTopY, ringRadius, ringRadius);
			
			//Green over drawn
			g.setColor(Color.GREEN);								//right-upper 40 degrees
			g2.drawArc(ring4X, ringBottomY, ringRadius, ringRadius, 40, 40);
		}
		
		
		public static void main(String[] args) 
		{
			Rings rings = new Rings();
		}
}
