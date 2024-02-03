/*
 * Samuel Bartholomew
 * 2/2/2024
 * Scott Weiss
 * 
 * Custom app for super heros to calculate
 * distance from a location to another location in a city
 * using x and y cordinates
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class Lab2 implements ActionListener{
	int numStage = 0; 
	//0 = x1 1 = y1	2 = x2 3 = y2 	allows for 0s
	//this will be exandable over isX and is1
	boolean isNeg = false;
	
	//Initializing immutables
	JFrame frame = new JFrame("SuperHeroHelper - SHH");
	JTextField textFieldX, textFieldY, textFieldResult, textFieldTTT;//TTT = TimeToTravel
	JButton[] numButtons = new JButton[10];
	JButton[] useButtons = new JButton[5];
	JButton submitButton, deleteButton, negButton, restartButton, closeButton;
	JPanel panel;
	
	//Creating objects for DecimalFormat and Font
	DecimalFormat dFormat = new DecimalFormat("0.00");
	
	Font myFont = new Font("SansSerif", Font.BOLD, 30);
	
	//X1,X2,Y1,Y2 and result / timeToTravel initialized
	double x1=0,y1=0,x2=0,y2=0,result = 0,timeToTravel = 0;
	//Void to create frame and add buttons, grid/panel, and create listeners
	public void init()
	{
		//Reseting values
		numStage = 0;
		isNeg = false;
		
		x1=0;y1=0;x2=0;y2=0;result = 0; timeToTravel = 0;
		
		//frame = new JFrame("SuperHeroHelper - SHH");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,550);
		frame.setLayout(null);
		
		//Create TimeToTravel text field
		textFieldTTT = new JTextField();
		textFieldTTT.setBounds(50,100,300,50);
		textFieldTTT.setFont(myFont);
		textFieldTTT.setEditable(false);
		
		//Create result text field
		textFieldResult = new JTextField();
		textFieldResult.setBounds(50,25,300,50);
		textFieldResult.setFont(myFont);
		textFieldResult.setEditable(false);
		//Create "X" text field
		textFieldX = new JTextField();
		textFieldX.setBounds(50,25,145,50);
		textFieldX.setFont(myFont);
		textFieldX.setEditable(false);
		//Create "Y" text field
		textFieldY = new JTextField();
		textFieldY.setBounds(205,25,145,50);
		textFieldY.setFont(myFont);
		textFieldY.setEditable(false);
		
		//Set the starting text
		textFieldX.setText("X1: ");
		textFieldY.setText("Y1: ");
		
		//Initialize buttons you can use that will affect numbers or the program
		submitButton = new JButton("ENTER");
		deleteButton = new JButton("DEL");
		negButton = new JButton("-");
		restartButton = new JButton("RESTART");
		closeButton = new JButton("CLOSE");
		//Assign the buttons
		useButtons[0] = submitButton;
		useButtons[1] = deleteButton;
		useButtons[2] = negButton;
		useButtons[3] = restartButton;
		useButtons[4] = closeButton;
		
		//Loop through the useButtons to add listeners and apply the fonts automatically
		for(int i = 0; i < 5; i++)
		{
			useButtons[i].addActionListener(this);
			useButtons[i].setFont(myFont);
			useButtons[i].setFocusable(false);
		}
		//Loop through 0-9 to apply listeners and apply the text + font
		for(int i = 0; i < 10; i++)
		{
			numButtons[i] = new JButton(String.valueOf(i));
			numButtons[i].addActionListener(this);
			numButtons[i].setFont(myFont);
			numButtons[i].setFocusable(false);
		}
		//Set the position and size of delete / submit
		deleteButton.setBounds(50,430,145,50);
		submitButton.setBounds(205,430,145,50);
		//Set the position and size of restart / close
		restartButton.setBounds(50,430,300,50);
		closeButton.setBounds(50,350,300,50);
		
		//Create the panel with a grid to store the numbers
		panel = new JPanel();
		panel.setBounds(50,100,300,300);
		panel.setLayout(new GridLayout(4,3,10,10));
		//Add the numbers and negative button to the panel
		panel.add(numButtons[1]);
		panel.add(numButtons[2]);
		panel.add(numButtons[3]);
		panel.add(numButtons[4]);
		panel.add(numButtons[5]);
		panel.add(numButtons[6]);
		panel.add(numButtons[7]);
		panel.add(numButtons[8]);
		panel.add(numButtons[9]);
		panel.add(numButtons[0]);
		panel.add(useButtons[2]);
		
		//Add the panel buttons and text fields to the grid and make the frame visable
		frame.add(panel);
		frame.add(deleteButton);
		frame.add(submitButton);
		frame.add(textFieldX);
		frame.add(textFieldY);
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		//Create a new object of the lab and initizalize it
		Lab2 lab = new Lab2();
		
		lab.init();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//Check to see if delete is clicked
		if(e.getSource() == deleteButton)
		{
			//depending on which "stage" the calculator is on clear that section and keep the negative vavlue
			switch(numStage)
			{
			case 0://X1
				if(!isNeg)
				{
					textFieldX.setText("X1: ");
				}
				else
				{
					textFieldX.setText("X1: -");
				}
				break;
				
			case 1://Y1
				if(!isNeg)
				{
					textFieldY.setText("Y1: ");
				}
				else
				{
					textFieldY.setText("Y1: -");
				}
				break;
				
			case 2://X2
				if(!isNeg)
				{
					textFieldX.setText("X2: ");
				}
				else
				{
					textFieldX.setText("X2: -");
				}
				break;
				
			case 3://Y2
				if(!isNeg)
				{
					textFieldY.setText("Y2: ");
				}
				else
				{
					textFieldY.setText("Y2: -");
				}
				break;
			}
		}
		//Check to see if the close button is clicked and if so close the program
		if (e.getSource() == closeButton)
		{
			System.exit(1);
		}
		//Check to see if the restart button is clicked and if so delete all compotents
		//including panel, buttons, and text fields, then initialize a new version as
		//if the program was starting a new
		if (e.getSource() == restartButton)
		{
	        Container contentPane = frame.getContentPane();

	        // Loop through the compoents and delete all buttons panels and text fields
	        Component[] components = contentPane.getComponents();
	        for (Component component : components) 
	        {
	            if (component instanceof JButton || component instanceof JPanel || component instanceof JTextField) 
	            {
	                contentPane.remove(component);
	            }
	            
	        }
			//clean up any left over
	        contentPane.removeAll();

	        //refresh the layout
	        frame.revalidate();

	        //repaint the frame
	        frame.repaint();
	        
	        //initalize
			init();
		}
		//loop to see if any of the numbers were clocked
		for(int i = 0; i < 10; i++)
		{
			//if 0-9 were clicked add to the text field which number was clicked
			//depending on which stage the code is at
			if (e.getSource() == numButtons[i])
				{
				//0 = x1 1 = y1	2 = x2 3 = y2 
					switch(numStage)
					{
					case 0:
						textFieldX.setText(textFieldX.getText().concat(String.valueOf(i)));
						//x1 = Double.parseDouble(textFieldX.getText().replace("X1: ", ""));
						break;
						
					case 1:
						textFieldY.setText(textFieldY.getText().concat(String.valueOf(i)));
						//y1 = Double.parseDouble(textFieldY.getText().replace("Y1: ", ""));
						break;
						
					case 2:
						textFieldX.setText(textFieldX.getText().concat(String.valueOf(i)));
						//x2 = Double.parseDouble(textFieldX.getText().replace("X2: ", ""));
						break;
						
					case 3:
						textFieldY.setText(textFieldY.getText().concat(String.valueOf(i)));
						//y2 = Double.parseDouble(textFieldY.getText().replace("Y2: ", ""));
						break;
					}
				}
		}
		//Check to see if the negative button is clicked and apply a - to which ever stage
		//the program is at
		if(e.getSource() == negButton)
		{
			switch(numStage)
			{
			case 0:
				if(!isNeg)
				{
					textFieldX.setText(textFieldX.getText().replace("X1: ", "X1: -"));
					isNeg = true;
				}
				else
				{
					textFieldX.setText(textFieldX.getText().replace("X1: -", "X1: "));
					isNeg = false;
				}
				break;
				
			case 1:
				if(!isNeg)
				{
					textFieldY.setText(textFieldY.getText().replace("Y1: ", "Y1: -"));
					isNeg = true;
				}
				else
				{
					textFieldY.setText(textFieldY.getText().replace("Y1: -", "Y1: "));
					isNeg = false;
				}
				break;
				
			case 2:
				if(!isNeg)
				{
					textFieldX.setText(textFieldX.getText().replace("X2: ", "X2: -"));
					isNeg = true;
				}
				else
				{
					textFieldX.setText(textFieldX.getText().replace("X2: -", "X2: "));
					isNeg = false;
				}
				break;
				
			case 3:
				if(!isNeg)
				{
					textFieldY.setText(textFieldY.getText().replace("Y2: ", "Y2: -"));
					isNeg = true;
				}
				else
				{
					textFieldY.setText(textFieldY.getText().replace("Y2: -", "Y2: "));
					isNeg = false;
				}
				break;
			}
		}
		
		//Check to see if the enter / submit is clicked
		//depending on the stage different actions will happen
		//a check will happen to see if the string "matches" having and numbers or decimals
		//if the string / text field has had a number clicked
		//move to the next stage reset the negative number
		if(e.getSource() == submitButton)
		{
			switch(numStage)
			{
			case 0:
				
				//I added a message to tell the user that they must enter a number before submiting
				//help avoid the user getting confused
				
				
				if(textFieldX.getText().replace("X1: ", "").matches(".*\\d.*"))
				{
					numStage++;
					x1 = Double.parseDouble(textFieldX.getText().replace("X1: ", ""));
					//System.out.println(x1);
					//System.out.println(textFieldX.getText().replace("X1: ", ""));
					isNeg = false;
				}
				else {JOptionPane.showMessageDialog(null,"Please be sure to select a number before presing submit.");}
				break;
			case 1:
				//move to and prep textfields to Y2 and X2
				if(textFieldY.getText().replace("Y1: ", "").matches(".*\\d.*"))
				{
					numStage++;
					y1 = Double.parseDouble(textFieldY.getText().replace("Y1: ", ""));
					//Reset fields and go to 2's
					textFieldY.setText("Y2: ");
					textFieldX.setText("X2: ");
					isNeg = false;
				}
				else {JOptionPane.showMessageDialog(null,"Please be sure to select a number before presing submit.");}
				break;
			case 2:
				if(textFieldX.getText().replace("X2: ", "").matches(".*\\d.*"))
				{
					numStage++;
					x2 = Double.parseDouble(textFieldX.getText().replace("X2: ", ""));
					isNeg = false;
				}
				else {JOptionPane.showMessageDialog(null,"Please be sure to select a number before presing submit.");}
				break;
			case 3:
				if(textFieldY.getText().replace("Y2: ", "").matches(".*\\d.*")) 
				{
				y2 = Double.parseDouble(textFieldY.getText().replace("Y2: ", ""));
				isNeg = false;
				//apply the math equation provided by professor weiss to the data i have 
				//to get the proper result
				result = Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
				timeToTravel = result/30;
		/*Reference
		 * frame.add(panel);
		frame.add(deleteButton);
		frame.add(submitButton);
		frame.add(textFieldX);
		frame.add(textFieldY);
		 */
				//remove all of the old GUI / panels, buttons and text field
				frame.remove(panel);
				frame.remove(deleteButton);
				frame.remove(submitButton);
				frame.remove(textFieldX);
				frame.remove(textFieldY);
				
				//convert the result to a decimal format and TimeToTravel
				//Set text fields as well
				String formatTimeToTravel = dFormat.format(timeToTravel);
				textFieldTTT.setText("ETA: "+formatTimeToTravel+" HR(S)");
				
				String formatResult = dFormat.format(result);
				textFieldResult.setText("Distance: " + formatResult);
				//add the result text field and restart/close buttons
				frame.add(textFieldTTT);
				frame.add(textFieldResult);
				frame.add(restartButton);
				frame.add(closeButton);
				//refresh the frame
				//repaint / rerender the frame
				frame.revalidate();
				frame.repaint();
				}
				else {JOptionPane.showMessageDialog(null,"Please be sure to select a number before presing submit.");}
				break;
			}
		}
		
	}
}
