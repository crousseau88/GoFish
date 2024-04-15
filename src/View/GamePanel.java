package View;

import Model.LivePlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private JLabel gameStatusLabel;
    private JButton endTurnButton;
    private LivePlayer player1;
    private JLabel computerPlayerLabel;

    public GamePanel(LivePlayer player1) {
        this.player1 = player1;
        setLayout(new BorderLayout());
        setBackground(new Color(100, 100, 200));

        // Create components
        gameStatusLabel = new JLabel("Game Status: In Progress");
        gameStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameStatusLabel.setFont(new Font("Arial", Font.BOLD, 24));

        endTurnButton = new JButton("End Turn");

        // Create labels to display player name and hand
        JLabel playerNameLabel = new JLabel("Player: " + player1.getUsername());
        computerPlayerLabel = new JLabel("Computer");

        // Create label to display player hand
        JLabel playerHandLabel = new JLabel("Player's Hand: " + player1.getHand());

        // Add components to panel
        add(gameStatusLabel, BorderLayout.NORTH);
        add(playerNameLabel, BorderLayout.WEST);
        add(computerPlayerLabel, BorderLayout.EAST);
        add(playerHandLabel, BorderLayout.CENTER);
        add(endTurnButton, BorderLayout.SOUTH);
    }

    // Method to update the game status label
    public void updateGameStatus(String status) {
        gameStatusLabel.setText("Game Status: " + status);
    }

    // Method to add action listener to the end turn button
    public void addEndTurnButtonListener(ActionListener listener) {
        endTurnButton.addActionListener(listener);
    }
}