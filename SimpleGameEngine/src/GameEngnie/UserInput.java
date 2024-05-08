package GameEngnie;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
//A CLASS TO HANDLE USER INPUT
public class UserInput implements KeyListener,MouseMotionListener
{
	private GameEngine gameEngine; //
	int prevX;

	public UserInput(GameEngine gameEngine) 
	{
        this.gameEngine = gameEngine; //Take game engine class so i can call vars / functions
    }
	
	//On mouse Drag
	public void mouseDragged(MouseEvent e) 
	{
        //System.out.println("Mouse dragged at " + e.getX() + ", " + e.getY());
    }

	//		On mouse move call
	//Take how much the cursor moved on the X-Axis and move the camera that much 
    public void mouseMoved(MouseEvent e) 
    {

		int curX = e.getX();
		int deltaX = curX - prevX;
		if(gameEngine.isCursorLocked)//If cursor is supposed to be locked, move camera
		{
				gameEngine.p.rotateYaw(deltaX * 0.01f); //.01F is the sensitivity
		}
		prevX = curX;
    	
    }
    	
    //On key presse
	public void keyPressed(KeyEvent e) 
	{
		//If key a certain key is pressed do an action
		//System.out.println(e);
		 if (e.getKeyCode() == KeyEvent.VK_ESCAPE)		//Toggle cursor
		 {
	            gameEngine.toggleCursorVisibility();
	            //System.out.println("Switch Cursor");
	     }
		 if (e.getKeyCode() == KeyEvent.VK_W) 
		 {
			 gameEngine.move(1);						//Move Forward
	     }
		 if (e.getKeyCode() == KeyEvent.VK_A) 
		 {
			 gameEngine.move(2);						//Move Left
	     }
		 if (e.getKeyCode() == KeyEvent.VK_S) 
		 {
			 gameEngine.move(3);						//Move Back
	     }
		 if (e.getKeyCode() == KeyEvent.VK_D) 
		 {
			 gameEngine.move(4);						//Move Right
	     }
		 if (e.getKeyCode() == KeyEvent.VK_SPACE) 
		 {												//Print the mini map and the user's position
			 gameEngine.miniMap.placePlayer((int)gameEngine.p.x, (int)gameEngine.p.y);
			 gameEngine.miniMap.printMap();
			 System.out.println(gameEngine.p.x + " || " + gameEngine.p.y);
	     }
		 if (e.getKeyCode() == KeyEvent.VK_1) 
		 {												//Switch Color to Red
			 //gameEngine.miniMap.printMap();
			 gameEngine.whichColor = 0;
	     }
		 if (e.getKeyCode() == KeyEvent.VK_2) 
		 {															
			 gameEngine.whichColor = 1;					//Switch Color to Green
	     }
		 if (e.getKeyCode() == KeyEvent.VK_3) 
		 {
			 gameEngine.whichColor = 2;					//Switch Color to Blue
	     }
	}
	
	
	//UNUSED
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
