/*
 * Samuel Bartholomew
 * 2/2/2024
 * Scott Weiss
 */

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Casino implements ActionListener
{
	String curScene = "enter";
						      //User  Balance Win Loss
	Bank bank = new Bank("Captain Jack", 1000, 0, 0);
	Random rand = new Random();
	
	JFrame frame = new JFrame("Pirate Casino");
	
	JTextArea instArea, bjArea;
	
	JLabel[] diceLabel = new JLabel[4];
	
	JLabel die1Label, die2Label, winLabel, totalLabel;
	
	JButton[] diceButtons = new JButton[7];
	JButton[] gameButtons = new JButton[2];
	
	JTextField bal;
	JFormattedTextField amountText;
	
	JButton removeAllB,remove10B,remove1B,addAllB, add10B, add1B, rollB;
	JButton homeButton, diceButton, blackjackButton, enterButton;
	
	Font mySuperBigFont = new Font("SansSerif", Font.BOLD, 40);
	Font myFontBig = new Font("SansSerif", Font.BOLD, 24);
	Font myFont = new Font("SansSerif", Font.BOLD, 12);

	int currentValue = 0;
	
	
	final int mathBWidth = 75;
	final int mathBHeight = 75;
	
	final int balanceX = 15;
	final int balanceY = 15;
	
	JPanel homePanel = new JPanel();
	JPanel dicePanel = new JPanel();
	
	
	void updateBal()
	{
		bal.setText("Balance: " + bank.balance);
	}
	
	void switchSceneTo(String scene)
	{
		System.out.println("S: "+scene);

		switch(scene)
		{
		case "dice":
			
			scene = "dice";
			removeAllB.setVisible(true);
			remove10B.setVisible(true);
			remove1B.setVisible(true);
			addAllB.setVisible(true);
			add10B.setVisible(true);
			add1B.setVisible(true);
			amountText.setVisible(true);
			rollB.setVisible(true);
			die1Label.setVisible(true);
			die2Label.setVisible(true);
			totalLabel.setVisible(true);
			winLabel.setVisible(true);
			instArea.setVisible(true);
			break;
		case "bj":
			scene = "bj";
			bjArea.setVisible(true);
			break;
		case "home":
			scene = "home";
			diceButton.setVisible(true);
			blackjackButton.setVisible(true);
			break;
		case "create":
			scene = "create";
			homeButton.setVisible(true);
			bal.setVisible(true);
			enterButton.setVisible(false);
			break;
		case "enter":
			System.out.println("runonce");
			scene = "enter";
						
			enterButton.setVisible(true);
			break;
		default:
	        frame.revalidate();
	        frame.repaint();
	        
		}
	}
	void wipe()
	{
		removeAllB.setVisible(false);
		remove10B.setVisible(false);
		remove1B.setVisible(false);
		addAllB.setVisible(false);
		add10B.setVisible(false);
		add1B.setVisible(false);
		amountText.setVisible(false);
		rollB.setVisible(false);
		die1Label.setVisible(false);
		die2Label.setVisible(false);
		totalLabel.setVisible(false);
		winLabel.setVisible(false);
		instArea.setVisible(false);
		diceButton.setVisible(false);
		blackjackButton.setVisible(false);
		bjArea.setVisible(false);
	}

	void init()
	{	
		die1Label = new JLabel("0");
		die2Label = new JLabel("0");
		winLabel = new JLabel("Tie");
		totalLabel = new JLabel("Total: 0");
		
		instArea = new JTextArea("2x Your bet with a 3, 6, 9.\nTie and get your $ back with a 12.\nLose with anything else.\nAreee you ready?");
		bjArea = new JTextArea("Blackjack coming soon!");
		
		bjArea.setBounds(150,100,350,300);
		bjArea.setLineWrap(true);
		bjArea.setFont(mySuperBigFont);
		
		instArea.setBounds(10,100,200,70);
		instArea.setLineWrap(true);
		instArea.setFont(myFont);
		
		die1Label.setBounds(300,100,100,100);
		die2Label.setBounds(405,100,100,100);
		totalLabel.setBounds(300,150,100,100);
		winLabel.setBounds(350,15,150,100);
		
		diceLabel[0] = die1Label;
		diceLabel[1] = die2Label;
		diceLabel[2] = totalLabel;
		diceLabel[3] = winLabel;
		
		
		for(int i = 0; i < 4; i++)
		{
			diceLabel[i].setFont(myFontBig);
			if(i<2)diceLabel[i].setFont(mySuperBigFont);
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(680,500);
		frame.setLayout(null);
		
		bal = new JTextField();
		bal.setBounds(15,15,250,75);
		bal.setFont(myFontBig);
		bal.setEditable(false);
		bal.setVisible(false);
		updateBal();
		
		NumberFormatter nForm = new NumberFormatter();
		nForm.setValueClass(Integer.class); // Set value class to Integer
		nForm.setMinimum(0); // Set minimum value allowed
		nForm.setMaximum(Integer.MAX_VALUE); // Set maximum value allowed
		nForm.setAllowsInvalid(false); // Disallow invalid input
		
		amountText = new JFormattedTextField(nForm);
		amountText.setBounds(235,320,200,80);//50+(i*100),400,80,80
		amountText.setFont(myFontBig);
		amountText.setEditable(false);
		amountText.setValue(0);
		currentValue = (Integer)amountText.getValue();
		
		diceButton = new JButton("Pirate Dice");
		blackjackButton = new JButton("Blackjack");
		
		gameButtons[0] = diceButton;
		gameButtons[1] = blackjackButton;
		//Loop through the useButtons to add listeners and apply the fonts automatically
				for(int i = 0; i < 2; i++)
				{
					gameButtons[i].addActionListener(this);
					gameButtons[i].setFont(myFont);
					gameButtons[i].setFocusable(false);
					gameButtons[i].setBounds(50+(i*150),320,140,40);
				}
		
		removeAllB = new JButton("<<<");
		remove10B = new JButton("-10");
		remove1B = new JButton("-1");
		addAllB = new JButton(">>>");
		add10B = new JButton("+10");
		add1B = new JButton("+1");
		rollB = new JButton("ROLL");
		
		enterButton = new JButton("ENTER THE PIRATES COVE");
		enterButton.addActionListener(this);
		enterButton.setFont(myFontBig);
		enterButton.setFocusable(false);
		enterButton.setBounds(frame.getWidth()/2-200,frame.getHeight()/2-50,400,100);
		
		homeButton = new JButton("HOME");
		homeButton.addActionListener(this);
		homeButton.setFont(myFont);
		homeButton.setFocusable(false);
		homeButton.setBounds(570,15,75,75);
		homeButton.setVisible(false);
		
		//Assign the buttons
		diceButtons[0] = removeAllB;
		diceButtons[1] = remove10B;
		diceButtons[2] = remove1B;
		diceButtons[5] = addAllB;
		diceButtons[4] = add10B;
		diceButtons[3] = add1B;
		diceButtons[6] = rollB;
		
		//Loop through the useButtons to add listeners and apply the fonts automatically
		for(int i = 0; i < 7; i++)
		{
			diceButtons[i].addActionListener(this);
			diceButtons[i].setFont(myFont);
			diceButtons[i].setFocusable(false);
			if(i<3 && i!=6)diceButtons[i].setBounds(15+(i*70),320,65,80);
			if(i>2 && i!=6)diceButtons[i].setBounds(15+225+(i*70),320,65,80);
			if(i==6)
			{
				diceButtons[i].setFont(myFontBig);
				diceButtons[i].setBounds(235,220,200,80);
			}
		}
	    
		frame.add(removeAllB);
		frame.add(remove10B);
		frame.add(remove1B);
		frame.add(addAllB);
		frame.add(add10B);
		frame.add(add1B);
		frame.add(amountText);
		frame.add(rollB);
		frame.add(die1Label);
		frame.add(die2Label);
		frame.add(totalLabel);
		frame.add(winLabel);
		frame.add(instArea);
		frame.add(diceButton);
		frame.add(blackjackButton);
		frame.add(homeButton);
		frame.add(bal);
		frame.add(enterButton);
		frame.add(bjArea);
		wipe();
		
		switchSceneTo("enter");

        frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}
	
	void diceRoll(int betAmount)
	{
		if(betAmount == 0)return;
		int with = bank.withdraw(betAmount);
		if(with == 0)
		{
			amountText.setValue(bank.balance);
            currentValue = (Integer)amountText.getValue();
            return;
		}
		int d1 = rand.nextInt(6);
		int d2 = rand.nextInt(6);
		int total = d1+d2;
		die1Label.setText(Integer.toString(d1));
		die2Label.setText(Integer.toString(d2));
		String totalIntToS = Integer.toString(total);
		totalLabel.setText("Total: "+totalIntToS);
		winLabel.setText("Lost");
		switch(total)
		{
		case 3:
			bank.totalWinnings+=betAmount;
			bank.deposit(betAmount*2);
			winLabel.setText("WINNER");
		break;
		case 6:
			bank.totalWinnings+=betAmount;
			bank.deposit(betAmount*2);
			winLabel.setText("WINNER");
		break;
		case 9:
			bank.totalWinnings+=betAmount;
			bank.deposit(betAmount*2);
			winLabel.setText("WINNER");
		break;
		case 12:
			bank.deposit(betAmount);
			winLabel.setText("TIE");
		break;
		}
		if(total!=3 && total!=6 && total!=9 && total!=12)bank.totalLosings+=betAmount;
		updateBal();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == enterButton)
		{
			enterButton.setVisible(false);
			switchSceneTo("create");
			switchSceneTo("home");
		}
		
		if(e.getSource() == diceButton)
		{
			wipe();
			switchSceneTo("dice");
		}
		
		if(e.getSource() == removeAllB)
			{
			amountText.setValue(0);
			currentValue = (Integer)amountText.getValue();
			}
		if(e.getSource() == remove10B)
		{
			currentValue = (Integer)amountText.getValue();
			if(currentValue <= 9) return;
            int newValue = currentValue - 10;
            amountText.setValue(newValue);
            currentValue = (Integer)amountText.getValue();
		}
		if(e.getSource() == remove1B)
		{
			currentValue = (Integer)amountText.getValue();
			if(currentValue <= 0) return;
            int newValue = currentValue - 1;
            amountText.setValue(newValue);
            currentValue = (Integer)amountText.getValue();
		}
		
		if(e.getSource() == addAllB)
			{
				amountText.setValue(bank.balance);
				currentValue = (Integer)amountText.getValue();
			}
		if(e.getSource() == add10B)
		{
			currentValue = (Integer)amountText.getValue();
			if(currentValue >= bank.balance-10) return;
            int newValue = currentValue + 10;
            amountText.setValue(newValue);
            currentValue = (Integer)amountText.getValue();
		}
		if(e.getSource() == add1B)
		{
			currentValue = (Integer)amountText.getValue();
			if(currentValue >= bank.balance) return;
            int newValue = currentValue + 1;
            amountText.setValue(newValue);
            currentValue = (Integer)amountText.getValue();
		}
		if(e.getSource() == homeButton)
		{
			wipe();
			switchSceneTo("home");
		}
		if(e.getSource() == rollB)
		{
			System.out.println("Current Bet:"+currentValue);
			diceRoll(currentValue);
		}
		if(e.getSource() == blackjackButton)
		{
			wipe();
			switchSceneTo("bj");
		}
		
		
	}
	
	public static void main(String[] args)
	{
		Casino casino = new Casino();
		casino.init();
		
	}
}
