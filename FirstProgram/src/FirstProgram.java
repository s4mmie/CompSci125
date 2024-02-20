import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstProgram {

    public static void main(String[] args)
    {
    	boolean a = true; 
    	int b = 4;
    	int c = 2;
    	if(b < 10 && c < 10)
    	{
    		a = false;
    		b = 10;
    		c = 10;
    	}
    	else
    	{
    		a = true;
    	}
    }
}