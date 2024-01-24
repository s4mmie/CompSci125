package GameEngnie;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameEngine extends JFrame
{
	private int playerX = 0;
	private int playerY = 0;
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
	
	public GameEngine()
	{
		setTitle("Game");
		setSize(1280, 720);
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
		 SwingUtilities.invokeLater(new Runnable() {
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
	
	
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        // Render the game elements
        render(g);
    }
	
	private void render(Graphics g)
	{
		//This will be done by looping through the pixel width 
	}
	
	private void gameLoop()
	{
		
	}
	
	private void handleInput()
	{
		
	}
}
