import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstProgram {

    private JFrame frame;
    private JPanel homePanel;
    private JPanel gamePanel;

    public FirstProgram() {
        frame = new JFrame("Scene Switching Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create home panel with a button to go to the game
        JButton goToGameButton = new JButton("Go to Game");
        goToGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(gamePanel);
                frame.revalidate();
            }
        });
        homePanel = new JPanel();
        homePanel.add(goToGameButton);

        // Create game panel with a button to go back home
        JButton backToHomeButton = new JButton("Back to Home");
        backToHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(homePanel);
                frame.revalidate();
            }
        });
        gamePanel = new JPanel();
        gamePanel.add(backToHomeButton);

        // Show the home panel initially
        frame.setContentPane(homePanel);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FirstProgram();
            }
        });
    }
}