package GameEngnie;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameEngine extends JFrame
{
	
	private int mapWidth = 20;
	private int mapHeight = 20;
	private int spawnX = 0;
	private int spawnY = 0;
	
	private String map = generateRandomMap(mapWidth);/*
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
			+"#XXXXXX######XXXXXX#"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"#XXXXXXXXXXXXXXXXXX#"
			+"####################";*/

	char[][] mapArray = strTo2DArr(map, mapWidth, mapHeight);
	
	Player p = new Player();
	minimap miniMap = new minimap(map, p.yaw); // Creating a 10x10 minimap
	
	private static boolean isRunning = false;
	static int PIXEL_SIZE = 3;
	static int SCREEN_WIDTH = 400*PIXEL_SIZE;
	static int SCREEN_HEIGHT = 300*PIXEL_SIZE;
	
	
	public static BufferedImage image = new BufferedImage(SCREEN_WIDTH*PIXEL_SIZE, SCREEN_HEIGHT*PIXEL_SIZE,BufferedImage.TYPE_INT_ARGB);
	
	short red = 0, green = 0, blue = 0, alpha = 255;
	
	
	
	//Get player XY from map
	//This can be done via take the map each 20 positions
	//will be 1 on Y
	//Player position below should be 9X 5Y
	//Map will start at 0,0
	public UserInput userInput;
	public boolean isCursorLocked;
	int whichColor = 0;
	
	public GameEngine()
	{
		setTitle("Game");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		userInput = new UserInput(this);
		addKeyListener(userInput);
        addMouseMotionListener(userInput);
        isCursorLocked = false;
        toggleCursorVisibility();
        // Update game logic and render the scene
        gameLoop();
        repaint(); // Trigger the paintComponent method
        
        

	}
	
	private String generateRandomMap(int size) 
	{
	    String myMap = "";
	    Random rand = new Random();
	    for(int i = 0; i < size; i++)
	    {
	    	myMap += '#';
	    }
	    
	    for(int l = 0; l < size-2; l++)
	    {
	    	for(int i = 0; i < size; i++)
	    	{
	    		
		    	
		    	if(i == 0 || i == size-1)
		    	{
		    		myMap += '#';
		    	}
		    	else
		    	{
		    		int randNum = rand.nextInt(10) + 1;
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
	    for(int i = 0; i < size; i++)
	    {
	    	myMap += '#';
	    }
	    System.out.println(myMap);
	    return myMap;
	}
	
	private void lockCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("");
        Point hotspot = new Point(0, 0);
        Cursor cursor = toolkit.createCustomCursor(image, hotspot, "InvisibleCursor");
        setCursor(cursor);
    }
	
	private void unlockCursor() {
        setCursor(Cursor.getDefaultCursor());
    }
	
	
	private void render(Graphics g)
	{
		//This will be done by looping through the pixel width 
			double fovDiv2 = p.fov / 2.0f;
			double stepSize = p.fov / (double)(SCREEN_WIDTH / PIXEL_SIZE);
			
			
			for(int x = 0; x < SCREEN_WIDTH/PIXEL_SIZE; x++)
			{
				double rayAngle = (p.yaw - fovDiv2) + (x * stepSize);
				double distanceToWall = 0;
				
				boolean isWallHit = false;
				
				double eyeX = Math.sin(rayAngle);
				double eyeY = Math.cos(rayAngle);
				
				while (!isWallHit && distanceToWall < mapWidth)
				{
					distanceToWall += 0.1f;
					int wTestX = (int)(p.x + eyeX * distanceToWall);//Wall Test X 
					int wTestY = (int)(p.y + eyeY * distanceToWall);
					//System.out.println("WTESTX: " + wTestX + " || " + "WTESTY: " + wTestY);
					
					if(wTestX < 0 || wTestX >= mapWidth || wTestY < 0 || wTestY >= mapHeight)
					{
						isWallHit = true;
						distanceToWall = mapWidth*2;
					}

						
					if((int)(wTestX*mapWidth+wTestY) < map.length()-1 && (int)(wTestX*mapWidth+wTestY) >= 0)
					{
						if(map.charAt((int)(wTestX*mapWidth+wTestY)) == '#')
						{
							isWallHit = true;
							//System.out.println("MATH: " + (int)(wTestX*mapWidth+wTestY));
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
			g.drawImage(image, 0, 0, null);
			//System.out.println("render done");


	}
	
	private void setPixelColor(int x, int y, Color color) 
	{
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
	                image.setRGB(pixelX, pixelY, color.getRGB());
        		}
	        }  
	    }
	    repaint();
    }
	
	
	private void gameLoop()
	{
		miniMap.initializeGrid();
		if(spawnX == 0)
			findSpawn();
		//miniMap.placePlayer((int)p.x, (int)p.y);
	}
	
	
	///////////////////////////////////////////////////
	public void toggleCursorVisibility() {
        if (isCursorLocked) {
            unlockCursor();
        } else {
            lockCursor();
        }
        isCursorLocked = !isCursorLocked;
    }
	
	public void xYCheck(int prevX, int prevY)
	{
		if(p.x > mapWidth || p.x < 0)
		{
			p.x = 6;
		}
		if(p.y > mapHeight || p.y < 0)
		{
			p.y = 5;
		}
		if(mapArray[(int)(p.x)][(int)(p.y)] == '#')
		{
			System.out.println("Wall @: [" + (int)p.x + "] , [" + (int)p.y + "]");
			p.x = prevX;
			p.y = prevY;
		}
	}
	
	public char[][] strTo2DArr(String mapString, int width, int height) 
	{
	    char[][] mapArray = new char[height][width];
	    int index = 1;
	    
	    for (int i = 0; i < height; i++) 
	    {
	        for (int j = 0; j < width; j++) 
	        {
	        	if(index > mapString.length()-1)
	        		index = mapString.length()-1;
	            mapArray[i][j] = mapString.charAt(index);
	            index += 1;
	        }
	    }
	    return mapArray;
	}

	public void move(int dir)
	{
		// 1 W 
		// 2 A
		// 3 S 
		// 4 D
		float degrees = p.yaw;
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
	}
	
	public static void init()
	{
		
		isRunning = true;
	}
	
	public void findSpawn()
	{
		while(spawnX == 0 && spawnY == 0)
		{
			Random rand = new Random();
			spawnX = rand.nextInt(19)+1;
			spawnY = rand.nextInt(19)+1;
			
			if(mapArray[spawnX][spawnY] == '#')
			{
				System.out.println("Wall @: [" + spawnX + "] , [" + spawnY + "]");
				spawnX = 0;
				spawnY = 0;
			}
			p.x = spawnX;
			p.y = spawnY;
		}
	}
	
	public void paint(Graphics g) 
	{
		gameLoop();
	    render(g);
	}
	
	
	public static void main(String[] args)
	{
		
    	GameEngine game = new GameEngine();
        game.setVisible(true);
        game.init();	
	}

}
