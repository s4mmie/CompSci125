

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Casino implements ActionListener
{
						      //User  Balance Win Loss
	Bank bank = new Bank("Captain Jack", 1000, 0, 0);
	Random rand = new Random();
	
	JFrame frame = new JFrame("Pirate Casino");
	JButton[] mathButtons = new JButton[6];
	JButton removeAllB,remove10B,remove1B,addAllB, add10B, add1B;
	JButton homeButton;
	
	Font myFontBig = new Font("SansSerif", Font.BOLD, 30);
	Font myFont = new Font("SansSerif", Font.BOLD, 15);


	final int mathBWidth = 75;
	final int mathBHeight = 75;
	
	final int balanceX = 15;
	final int balanceY = 15;
	
	JPanel homePanel = new JPanel();
	JPanel dicePanel = new JPanel();
	
	void init()
	{
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setLayout(null);
		
		
		removeAllB = new JButton("<<<");
		remove10B = new JButton("-10");
		remove1B = new JButton("-1");
		addAllB = new JButton(">>>");
		add10B = new JButton("+10");
		add1B = new JButton("+1");
		
		homeButton = new JButton("HOME");
		homeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		homeButton.setFont(myFontBig);
		homeButton.setFocusable(false);
		
		//Assign the buttons
		mathButtons[0] = removeAllB;
		mathButtons[1] = remove10B;
		mathButtons[2] = remove1B;
		mathButtons[3] = addAllB;
		mathButtons[4] = add10B;
		mathButtons[5] = add1B;
		
		//Loop through the useButtons to add listeners and apply the fonts automatically
		for(int i = 0; i < 5; i++)
		{
			mathButtons[i].addActionListener(this);
			mathButtons[i].setFont(myFontBig);
			mathButtons[i].setFocusable(false);
		}
		
		
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		
	}
	
	void diceRoll(int betAmount)
	{
		if(betAmount == 0)return;
		int with = bank.withdraw(betAmount);
		if(with == 0)return;
		int d1 = rand.nextInt(6);
		int d2 = rand.nextInt(6);
		int total = d1+d2;
		
		switch(total)
		{
		case 3->bank.deposit(betAmount*2);
		case 6->bank.deposit(betAmount*2);
		case 9->bank.deposit(betAmount*2);
		case 12->bank.deposit(betAmount);
		default->d1 = 0;//play losing animation
		
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
