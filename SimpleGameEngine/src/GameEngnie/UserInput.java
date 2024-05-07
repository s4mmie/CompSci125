package GameEngnie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;

public class UserInput implements KeyListener,MouseMotionListener
{
	private GameEngine gameEngine;
	int prevX;

	public UserInput(GameEngine gameEngine) 
	{
        this.gameEngine = gameEngine;
    }
	
	
	public void mouseDragged(MouseEvent e) 
	{
        //System.out.println("Mouse dragged at " + e.getX() + ", " + e.getY());
    }

	
    public void mouseMoved(MouseEvent e) 
    {

		int curX = e.getX();
		int deltaX = curX - prevX;
		if(gameEngine.isCursorLocked)
		{
				gameEngine.p.rotateYaw(deltaX * 0.01f); // Adjust the factor as needed for sensitivity
				//System.out.println(gameEngine.p.yaw);
		}
		prevX = curX;
    	
    }
    	


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void keyPressed(KeyEvent e) 
	{
		//System.out.println(e);
		 if (e.getKeyCode() == KeyEvent.VK_ESCAPE) 
		 {
	            gameEngine.toggleCursorVisibility();
	            //System.out.println("Switch Cursor");
	     }
		 if (e.getKeyCode() == KeyEvent.VK_W) 
		 {
			 gameEngine.move(1);
	     }
		 if (e.getKeyCode() == KeyEvent.VK_A) 
		 {
			 gameEngine.move(2);
	     }
		 if (e.getKeyCode() == KeyEvent.VK_S) 
		 {
			 gameEngine.move(3);
	     }
		 if (e.getKeyCode() == KeyEvent.VK_D) 
		 {
			 gameEngine.move(4);
	     }
		 if (e.getKeyCode() == KeyEvent.VK_SPACE) 
		 {
			 gameEngine.miniMap.placePlayer((int)gameEngine.p.x, (int)gameEngine.p.y);
			 gameEngine.miniMap.printMap();
			 System.out.println(gameEngine.p.x + " || " + gameEngine.p.y);
	     }
		 if (e.getKeyCode() == KeyEvent.VK_1) 
		 {
			 //gameEngine.miniMap.printMap();
			 gameEngine.whichColor = 0;
	     }
		 if (e.getKeyCode() == KeyEvent.VK_2) 
		 {
			 gameEngine.whichColor = 1;
	     }
		 if (e.getKeyCode() == KeyEvent.VK_3) 
		 {
			 gameEngine.whichColor = 2;
	     }
		 
		 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
