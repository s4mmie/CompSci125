package GameEngnie;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
//CLASS FOR MAIN / GAME ENGINE
public class GameEngine extends JFrame
{
	//Map Width & Map Height must be the same
	private int mapWidth = 20;
	private int mapHeight = 20;
	private int spawnX = 0;
	private int spawnY = 0;
	//Generate Map
	private String map = generateRandomMap(mapWidth);/*
	
				EXAMPLE OF MAP
				-# is a wall
				-X is an empty spot
				
			 "####################"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"####################";*/
	
	
	//Take map and store it into a 2d array
	char[][] mapArray = strTo2DArr(map, mapWidth, mapHeight);
	
	//Init player and Minimap
	Player p = new Player();
	minimap miniMap = new minimap(map, p.yaw); // Creating a 10x10 minimap
	
	//Create static variables
	static int PIXEL_SIZE = 3;
	static int SCREEN_WIDTH = 400*PIXEL_SIZE;
	static int SCREEN_HEIGHT = 300*PIXEL_SIZE;
	
	public static BufferedImage image = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB);
	
	//Init UserInput class
	public UserInput userInput;
	
	//Bools
	public boolean isCursorLocked;
	private boolean isRunning = false;
	
	//Colors
	short red = 0, green = 0, blue = 0, alpha = 255;
	int whichColor = 0; //1 2 3 RED GREEN BLUE
	
	//		Constructor
	//
	public GameEngine()
	{
		//init window
		setTitle("Sam B Final - 'Doom' ");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//init user input with listeners
		userInput = new UserInput(this);
		addKeyListener(userInput);
        addMouseMotionListener(userInput);
        
        //Toggle cursor
        isCursorLocked = false;
        toggleCursorVisibility();
        
        //Start gameloop
        gameLoop();
        repaint(); // Trigger the paintComponent method
	}
	
	//String to generate the random map
	private String generateRandomMap(int size) 
	{
	    String myMap = "";//init map to return
	    Random rand = new Random(); //init random
	    for(int i = 0; i < size; i++)		//create top row
	    {
	    	myMap += '#';
	    }
	    
	    for(int l = 0; l < size-2; l++)		//Draw the middle of the map
	    {
	    	for(int i = 0; i < size; i++)
	    	{
		    	if(i == 0 || i == size-1)
		    	{
		    		myMap += '#'; //if on edges of map draw a wall
		    	}
		    	else
		    	{
		    		int randNum = rand.nextInt(10) + 1; //have a 10% chance to randomly put a wall in each grid
		    		if(randNum == 3)
		    		{
		    			myMap += '#';
		    		}
		    		else
		    		{
		    			myMap += 'X';
		    		}
		    	}
	    	}
	    }
	    
	    
	    for(int i = 0; i < size; i++)		//Draw bottom row
	    {
	    	myMap += '#';
	    }
	    //DEBUGGING System.out.println(myMap);
	    return myMap; //Return map
	}
	
	//Set the cursor to an invisble image
	private void lockCursor() 
	{
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("");
        Point hotspot = new Point(0, 0);
        Cursor cursor = toolkit.createCustomCursor(image, hotspot, "InvisibleCursor");
        setCursor(cursor);
    }
	
	//Set cursor back to default
	private void unlockCursor() 
	{
        setCursor(Cursor.getDefaultCursor());
    }
	
	//Render the walls from the string
	private void render(Graphics g)
	{
		double fovDiv2 = p.fov / 2.0f; //Get half of FOV
		double stepSize = p.fov / (double)(SCREEN_WIDTH / PIXEL_SIZE); //Determine step Size
		
		//Loop thorugh screen width
		for(int x = 0; x < SCREEN_WIDTH/PIXEL_SIZE; x++)
		{
			double rayAngle = (p.yaw - fovDiv2) + (x * stepSize); //Ray angle (Ray for each pixel)
			double distanceToWall = 0;
			
			boolean isWallHit = false;
			
			double eyeX = Math.sin(rayAngle); //Set eyeX to sin of ray Angle
			double eyeY = Math.cos(rayAngle); //Set eyeY to cos of ray Angle
			
			while (!isWallHit && distanceToWall < mapWidth) //Loop until a wall is hit
			{
				distanceToWall += 0.1f;
				int wTestX = (int)(p.x + eyeX * distanceToWall);//Wall Test X 
				int wTestY = (int)(p.y + eyeY * distanceToWall);//Wall Test Y
				//If either tester is out  of bounds it missed a wall so count it as hitting "air"
				if(wTestX < 0 || wTestX >= mapWidth || wTestY < 0 || wTestY >= mapHeight)
				{
					isWallHit = true;
					distanceToWall = mapWidth*2;
				}
				//if WTestX hit a wall return it, and remember the distance
				if((int)(wTestX*mapWidth+wTestY) < map.length()-1 && (int)(wTestX*mapWidth+wTestY) >= 0)
				{
					if(map.charAt((int)(wTestX*mapWidth+wTestY)) == '#')
					{
						isWallHit = true;
						//DEBUG System.out.println("MATH: " + (int)(wTestX*mapWidth+wTestY));
					}
				}
			}
			
			//Determine where the ceiling and floor is
			double sCeiling = (double)((SCREEN_HEIGHT / PIXEL_SIZE) / 2f) - (SCREEN_HEIGHT / PIXEL_SIZE) / ((double)distanceToWall);
			double sFloor = (SCREEN_HEIGHT / PIXEL_SIZE) - sCeiling;
			
			//Init distanceRGB and distance Amount
			short distanceR, distanceG, distanceB, distanceAmount;
			
			//Depending on how far away determine color
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
			
			//Draw color depending on which color is chosen
			
			//Draw Roof
			//Draw Middle Wall
			//Draw Floor
			switch(whichColor)
			{
			case 0:
				for (int y = 0; y < (SCREEN_WIDTH / PIXEL_SIZE); y++)
				{
					if(y <=  sCeiling)
					{
						red = 55;
						green = 55;
						blue = 55;
					}
					else if(y > sCeiling && y <= sFloor)
					{
						if(distanceToWall == mapWidth*2)
						{
							red = 255;
							green = 182;
							blue=192;
						}
						else
						{
							red = (short) (55 + distanceR);
							green = 0;
							blue = 0;
						}
					}
					else
					{
						red = 255;
						green = 182;
						blue=192;
					}
					Color customRGB = new Color(red, green, blue);
					//System.out.println("Custom RGB:"+customRGB);
					setPixelColor(x, y, customRGB);
					}
				break;
			case 1:
				for (int y = 0; y < (SCREEN_WIDTH / PIXEL_SIZE); y++)
				{
					if(y <=  sCeiling)
					{
						red = 55;
						green = 55;
						blue = 55;
					}
					else if(y > sCeiling && y <= sFloor)
					{
						if(distanceToWall == mapWidth*2)
						{
							red = 255;
							green = 182;
							blue=192;
						}
						else
						{
							red = 0;
							green = (short) (55 + distanceR);
							blue = 0;
						}
					}
					else
					{
						red = 100;
						green = 255;
						blue=150;
					}
					Color customRGB = new Color(red, green, blue);
					//System.out.println("Custom RGB:"+customRGB);
					setPixelColor(x, y, customRGB);
				}
				break;
			case 2:
				for (int y = 0; y < (SCREEN_WIDTH / PIXEL_SIZE); y++)
				{
					if(y <=  sCeiling)
					{
						red = 55;
						green = 55;
						blue = 55;
					}
					else if(y > sCeiling && y <= sFloor)
					{
						if(distanceToWall == mapWidth*2)
						{
							red = 255;
							green = 182;
							blue=192;
						}
						else
						{
							red = 0;
							green = 0;
							blue = (short) (55 + distanceR);;
						}
					}
					else
					{
						red = 100;
						green = 165;
						blue=255;
					}
					Color customRGB = new Color(red, green, blue);
					//System.out.println("Custom RGB:"+customRGB);
					setPixelColor(x, y, customRGB);
				}
				break;
			}
		}
		//Draw the image of the buffered image
		g.drawImage(image, 0, 0, null);
	}
	
	//Set the color in the buffered image
	private void setPixelColor(int x, int y, Color color) 
	{
		//To create a bigger image each pixel will scale by pixel size
		int scaledX = x * PIXEL_SIZE;
	    int scaledY = y * PIXEL_SIZE;

	    // Check if the specified position is within the bounds of the image
	    for (int i = 0; i < PIXEL_SIZE; i++) 
	    {
	        for (int j = 0; j < PIXEL_SIZE; j++) 
	        {
	            int pixelX = scaledX + i;
	            int pixelY = scaledY + j;
		
	            if (pixelX >= 0 && pixelX < image.getWidth() && pixelY >= 0 && pixelY < image.getHeight()) 
	            {
	            	//Set RGB of pixels within the pixel scale
	                image.setRGB(pixelX, pixelY, color.getRGB());
        		}
	        }  
	    }
	    repaint();
    }
	
	//Game Loop 
	private void gameLoop()
	{
		miniMap.initializeGrid();
		if(spawnX == 0)
			findSpawn(mapHeight);
		miniMap.placePlayer((int)p.x, (int)p.y);
	}
	
	
	///////////////////////////////////////////////////
	//		PUBLIC FUNCTIONS
	//////////////////////////////////////////////////
	
	//Toggle if the cursor should be locked or unlocked 
	public void toggleCursorVisibility() 
	{
        if (isCursorLocked) 
        {
            unlockCursor();
        } 
        else 
        {
            lockCursor();
        }
        isCursorLocked = !isCursorLocked;
    }
	
	//Check to make sure the player is not outside of the map
	//Check to make sure the player doesn't move into a wall
	public void xYCheck(int prevX, int prevY)
	{
		//If outside of map send back to spawn (we known spawn will have no walls)
		if(p.x > mapWidth || p.x < 0)
		{
			p.x = spawnX;
		}
		if(p.y > mapHeight || p.y < 0)
		{
			p.y = spawnY;
		}
		//Check for walls in, if wall set back to previous
		if(mapArray[(int)(p.x)][(int)(p.y)] == '#')
		{
			//DEBUG System.out.println("Wall @: [" + (int)p.x + "] , [" + (int)p.y + "]");
			p.x = prevX;
			p.y = prevY;
		}
	}
	
	//Take a string and store it into a 2d array
	public char[][] strTo2DArr(String mapString, int width, int height) 
	{
	    char[][] mapArray = new char[height][width]; //Init new aray
	    int index = 1;
	    
	    for (int i = 0; i < height; i++) 
	    {
	        for (int j = 0; j < width; j++) 
	        {
	        	if(index > mapString.length()-1)//Take each row and store it into maparray
	        		index = mapString.length()-1;
	            mapArray[i][j] = mapString.charAt(index);
	            index += 1;
	        }
	    }
	    return mapArray;
	}

	//Move the player depending on the given direciton
	public void move(int dir)
	{
		// 1 W 
		// 2 A
		// 3 S 
		// 4 D
		int prevX = (int)p.x;
		int prevY = (int)p.y;
		switch(dir)
		{
		case 1:
			p.x += Math.sin(p.yaw) * 0.1f;
			p.y += Math.cos(p.yaw) * 0.1f; 
	    	break;
		case 2:
			p.x -= Math.cos(p.yaw) * 0.1f;
		    p.y += Math.sin(p.yaw) * 0.1f;
			break;
		case 3:
			p.x -= Math.sin(p.yaw) * 0.1f;
			p.y -= Math.cos(p.yaw) * 0.1f; 
			break;
		case 4:
			
		    p.x += Math.cos(p.yaw) * 0.1f;
		    p.y -= Math.sin(p.yaw) * 0.1f;
			break;
		}
		xYCheck(prevX, prevY);
		//Check collision
	}
	
	//Find a place to spawn
	public void findSpawn(int size)
	{
		//if spawn X and Y are 0, rnadomly choose a place to spawn based off the map size.
		while(spawnX == 0 && spawnY == 0)
		{
			Random rand = new Random();
			spawnX = rand.nextInt(size-1)+1;
			spawnY = rand.nextInt(size-1)+1;
			
			//Check to see if the given spot has a wall if so reset
			if(mapArray[spawnX][spawnY] == '#')
			{
				//Debug System.out.println("Wall @: [" + spawnX + "] , [" + spawnY + "]");
				spawnX = 0;
				spawnY = 0;
			}
			else	//move player if spot isn't a wlal
			{
				p.x = spawnX;
				p.y = spawnY;
			}
		}
	}
	//Paint will call the game loop, and then render
	public void paint(Graphics g) 
	{
		gameLoop();
	    render(g);
	}
	
	//Main used to init game engine
	public static void main(String[] args)
	{
    	GameEngine game = new GameEngine();
        game.setVisible(true);
	}
}
