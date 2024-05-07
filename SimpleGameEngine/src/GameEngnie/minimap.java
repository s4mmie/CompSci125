package GameEngnie;
public class minimap {
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
    	String[] lines = splitString(map,20);
        height = lines.length;
        width = lines[0].length();
        grid = new char[height][width];
        initializeGrid();
        //placePlayer(width / 2, height / 2); // Place player at the center initially
    }

    public void initializeGrid() 
    {
        for (int i = 0; i < height; i++) 
        {
            for (int j = 0; j < width; j++) 
            {
                grid[i][j] = '.';
                if(mapM.charAt(i*20+j) == '#')
                	grid[i][j] = '#';
            }
        }
    }

    public void placePlayer(int x, int y) 
    {
        playerX = x;
        playerY = y;
        grid[playerY][playerX] = playerSymbol;
    }

    public void rotatePlayer(int degree) 
    {
    	degrees += degree;
    	if(degrees > 360)
    		degrees = 0;
    	if(degrees < 0)
    		degrees = 360;
    	//System.out.println("Degrees:"+degrees);
    	
    	
        // Implement player rotation logic here
        // For simplicity, let's just rotate the player symbol
        // in a clockwise direction by a certain number of degrees
        // For example, 'P' for 0 degrees, '>' for 90 degrees, 'V' for 180 degrees, '<' for 270 degrees.
        //280-40
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
    
    public static String[] splitString(String input, int chunkSize) 
    {
    	
        // Calculate the number of chunks required
        int numOfChunks = (int) Math.ceil((double) input.length() / chunkSize);
        // Create an array to store the substrings
        String[] substrings = new String[numOfChunks];

        // Split the string into substrings of length chunkSize
        for (int i = 0; i < numOfChunks; i++) 
        {
            int startIndex = i * chunkSize;
            int endIndex = Math.min((i + 1) * chunkSize, input.length());
            substrings[i] = input.substring(startIndex, endIndex);
        }

        return substrings;
    }

}
