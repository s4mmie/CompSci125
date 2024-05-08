//Samuel Bartholomew
//Scott Weiss
//Final Project

//Problem: Turn a randomized string into a 3d enviorment where you can move and look around


package GameEngnie;
//A CLASS TO IMPLEMENT A MINIMAP
public class minimap 
{
    private char[][] grid;
    private String mapM;
    private int width;
    private int height;
    private int playerX;
    private int playerY;
    private char playerSymbol = 'P';
    private float degrees;

    public minimap(String map, float degree)
    {
    	degrees = degree;
    	mapM = map;
    	String[] lines = splitString(map,20); //Split string by 20 (Size)
        height = lines.length;
        width = lines[0].length();
        grid = new char[height][width];
        initializeGrid();//Init Grid
    }
    
    //Take the height and width of the map and loop through each character and if it contains a wall replace the .
    public void initializeGrid() 
    {
        for (int i = 0; i < height; i++) 
        {
            for (int j = 0; j < width; j++) 
            {
                if(mapM.charAt(i*20+j) == '#')
                	grid[i][j] = '#';
                else
                	grid[i][j] = '.';
            }
        }
    }
    //Place the player into the Grid
    public void placePlayer(int x, int y) 
    {
        playerX = x;
        playerY = y;
        grid[playerY][playerX] = playerSymbol;
    }
    //Unused, was used in testing originally
    public void rotatePlayer(int degree) 
    {
    	degrees += degree;
    	if(degrees > 360)
    		degrees = 0;
    	if(degrees < 0)
    		degrees = 360;

    	if(degrees > 290 || degrees < 40)
                playerSymbol = 'P';
    	if(degrees > 41 && degrees < 140)
                playerSymbol = '>';
    	if(degrees > 141 && degrees < 230)
                playerSymbol = 'V';
    	if(degrees > 231 && degrees < 289)
                playerSymbol = '<';
        
        grid[playerY][playerX] = playerSymbol;
    }
    //Loop through the map and print each grid
    public void printMap() 
    {
        for (int i = 0; i < height; i++) 
        {
            for (int j = 0; j < width; j++) 
            {
                System.out.print(grid[i][j] + "");
            }
            System.out.println();
        }
    }
    
    //Split the string (input) by the splitSize
    public static String[] splitString(String input, int splitSize) 
    {
        // Calculate the number of chunks required
        int numOfLines = (int) Math.ceil((double) input.length() / splitSize);
        // Create an array to store the substrings
        String[] substrings = new String[numOfLines];

        // Split the string into substrings of length splitSize
        for (int i = 0; i < numOfLines; i++)//Loop through the string and store each splitSize into a string
        {
            int startIndex = i * splitSize;
            int endIndex = Math.min((i + 1) * splitSize, input.length());
            substrings[i] = input.substring(startIndex, endIndex);
        }
        return substrings;
    }
}
