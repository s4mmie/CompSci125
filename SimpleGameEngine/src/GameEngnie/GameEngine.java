package GameEngnie;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameEngine extends JFrame
{
	Player p = new Player();
	
	private boolean isRunning = false;
	
	private BufferedImage image;
	
	static int SCREEN_WIDTH = 1280;
	static int SCREEN_HEIGHT = 720;
	static int PIXEL_SIZE = 2;
	
	short red = 0, green = 255, blue = 0, alpha = 255;
	
	
	
	//Get player XY from map
	//This can be done via take the map each 20 positions
	//will be 1 on Y
	//Player position below should be 9X 5Y
	//Map will start at 0,0
	private int wallSize = 16;
	private String map = "####################"//*STARTING AT 0* 19 WIDTH 10 HEIGHT
						+"#XXXXXXXXXXXXXXXXXX#"//# Blank space
						+"#XXXXXXXXXXXXXXXXXX#"//X Wall
						+"#XXXXXXXXXXXXXXXXXX#"//P Player
						+"#XXXXXXXXXXXXXXXXXX#"
						+"#XXXXXXXXPXXXXXXXXX#"
						+"#XXXXXXXXXXXXXXXXXX#"
						+"#XXXXXXXXXXXXXXXXXX#"
						+"#XXXXXXXXXXXXXXXXXX#"
						+"#XXXXXXXXXXXXXXXXXX#"
						+"####################";
	private int mapWidth = 20;
	private int mapHeight = 11;
	
	public GameEngine()
	{
		setTitle("Game");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Timer timer = new Timer(16, new ActionListener()
		{
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Update game logic and render the scene
                gameLoop();
                repaint(); // Trigger the paintComponent method
            }
		});
		timer.start();
	}
	
	public static void main(String[] args)
	{
		 SwingUtilities.invokeLater(new Runnable() 
		 {
	            @Override
	            public void run() {
	                new GameEngine().setVisible(true);
	            }
	        });
		GameEngine game = new GameEngine();
		game.init();
	}
	
	public static void init()
	{
		Scanner scanner = new Scanner(System.in);
	}
	
	
    public void paintComponent(Graphics g) 
    {
        super.paintComponents(g);

        // Render the game elements
        render(g);
    }
	
	private void render(Graphics g)
	{
		//This will be done by looping through the pixel width 
		while (isRunning) 
		{
			double fovDiv2 = p.fov/2f;
			double stepSize = p.fov / (SCREEN_WIDTH / PIXEL_SIZE);
			
			
			for(int x = 0; x < SCREEN_WIDTH/PIXEL_SIZE; x++)
			{
				double rayAngle = (p.yaw - fovDiv2) + (x * stepSize);
				double distanceToWall = 0;
				
				boolean isWallHit = false;
				
				double eyeX = Math.sin(rayAngle);
				double eyeY = Math.cos(rayAngle);
				
				while (!isWallHit && distanceToWall < mapWidth)
				{
					distanceToWall += 0.3f;
					int wTestX = (int)(p.x + eyeX * distanceToWall);//Wall Test X 
					int wTestY = (int)(p.y + eyeY * distanceToWall);
					
					if(wTestX < 0 || wTestX > mapWidth || wTestY < 0 || wTestY >= mapHeight)
					{
						isWallHit = true;
						distanceToWall = mapWidth;
					}
					else
					{
						if(map.charAt(wTestX*mapWidth+wTestY) == '#')
						{
							isWallHit = true;
						}
					}
				}
				
				double sCeiling = (double)((SCREEN_HEIGHT / PIXEL_SIZE) / 2f) - (SCREEN_HEIGHT / PIXEL_SIZE) / ((double)distanceToWall);
				double sFloor = (SCREEN_HEIGHT / PIXEL_SIZE) - sCeiling;
				
				short distanceR, distanceG, distanceB, distanceAmount;
				
				if(distanceToWall < mapWidth / 4)
				{
					distanceAmount = 200;
					distanceR = distanceAmount; 
					distanceG = distanceAmount;
					distanceB = distanceAmount;
				}
				else if (distanceToWall < mapWidth / 3)
				{
					distanceAmount = 150;
					distanceR = distanceAmount; 
					distanceG = distanceAmount;
					distanceB = distanceAmount;
				}
				else if (distanceToWall < mapWidth / 2)
				{
					distanceAmount = 100;
					distanceR = distanceAmount; 
					distanceG = distanceAmount;
					distanceB = distanceAmount;
				}
				else
				{
					distanceAmount = 0;
					distanceR = distanceAmount; 
					distanceG = distanceAmount;
					distanceB = distanceAmount;
				}
				
				for (int y = 0; y < (SCREEN_WIDTH / PIXEL_SIZE); y++)
				{
					if(y <= sCeiling)
					{
						red = 55;
						green = 55;
						blue = 55;
					}
					else if(y > sCeiling && y <= sFloor)
					{
						red = (short) (55 + distanceR);
						green = 0;
						blue = 0;
					}
					image = new BufferedImage(getWidth(), getHeight(),BufferedImage.TYPE_INT_ARGB);
					Color customRGB = new Color(red, green, blue);
					setPixelColor(x, y, customRGB);
				}
			}
		}
	}
	
	private void setPixelColor(int x, int y, Color color) 
	{
        // Check if the specified position is within the bounds of the image
        if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
            // Set the color of the pixel at (x, y)
            image.setRGB(x, y, color.getRGB());

            // Repaint the component to reflect the changes
            repaint();
        }
    }
	
	private void gameLoop()
	{
		
	}
	
	private void handleInput()
	{
		
	}
}
