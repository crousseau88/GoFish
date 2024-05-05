package View;
/**
 * Filename: GamePanel.java
 * Short description: Used for setup of the game labels/Buttons for user interaction
 * IST 242 Assignment:GUI Programming Project
 *
 *
 * @author Chad Rousseau, Christopher Rusnak, Tyler Mascherino
 * @version 05/3/2024
 */

import Model.Card;
import Model.Deck;
import Model.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {
    //Instance variables
    private JLabel gameStatusLabel;
    private JButton endTurnButton;
    private JButton drawCardButton;
    private JPanel playerHandPanel;
    private JLabel cCount;
    private JLabel computerStack;
    private JLabel pCount;
    private JLabel playerStack;
    private JLabel userNameLabel;
    private JLabel computerNameLabel;
    private int playerCount = 0;
    private int compCount = 0;

    public GamePanel(Player player1, Deck deck) {
        // Layout setup that is used to configure positions of instance variables
        setLayout(null);
        setBackground(new Color(0, 100, 0));

        // Username
        userNameLabel = new JLabel(player1.getUsername());
        userNameLabel.setForeground(Color.WHITE);
        userNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        userNameLabel.setBounds(50, getHeight() - 100, 200, 30);
        add(userNameLabel);

        // Computer name label
        computerNameLabel = new JLabel("Player: Computer");
        computerNameLabel.setForeground(Color.WHITE);
        computerNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        computerNameLabel.setBounds(getWidth() - 250, 50, 200, 30);
        add(computerNameLabel);

        // Game Status Label
        gameStatusLabel = new JLabel("Game Status: In Progress");
        gameStatusLabel.setForeground(Color.WHITE);
        gameStatusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(gameStatusLabel);

        // Draw Card Button
        ImageIcon drawIcon = new ImageIcon("Cards/b1fh.png");
        drawCardButton = new JButton(drawIcon);

        drawCardButton.addActionListener(this);
        add(drawCardButton);

        // End Turn Button
        endTurnButton = new JButton("End Turn");
        endTurnButton.addActionListener(this);
        add(endTurnButton);

        // Player Hand Panel
        playerHandPanel = new JPanel(new FlowLayout());
        playerHandPanel.setOpaque(false);
        playerHandPanel.setBounds(0, 350, 800, 100);
        add(playerHandPanel);

        // Initial update for player hand
        updatePlayerHand(player1.getHand().getCards(), deck);


        // Computer Config
        // Used for the computer Icon and match stack
        cCount = new JLabel("" + compCount);
        cCount.setForeground(Color.YELLOW);
        cCount.setFont(new Font("Arial", Font.BOLD, 20));
        add(cCount);

        ImageIcon cStackIcon = new ImageIcon("Cards/b1fv.png");
        computerStack = new JLabel(cStackIcon);
        add(computerStack);


        // Player Config
        // used for the Player match stack, displayable cards
        pCount = new JLabel("" + playerCount);
        pCount.setForeground(Color.YELLOW);
        pCount.setFont(new Font("Arial", Font.BOLD, 20));
        add(pCount);
        revalidate();
        repaint();

        ImageIcon pStackIcon = new ImageIcon("Cards/b2fv.png");
        playerStack = new JLabel(pStackIcon);
        add(playerStack);



        // Add a component listener to adjust component bounds on resize
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustComponentBounds();
            }
        });
    }

    public void updatePlayerName(String username) {
        JLabel userNameLabel = (JLabel) getComponent(0);
        userNameLabel.setText("Player: " + username);
    }


    private void adjustComponentBounds() {
        // Adjust the bounds of components based on the panel size
        Dimension size = getSize();
        endTurnButton.setBounds((size.width /2 - 90) , (size.height - 100) , 200, 20);
        gameStatusLabel.setBounds((size.width - 300) / 2, 0, 600, 50);
        drawCardButton.setBounds(size.width / 2 - 45, size.height - 460, 90, 65);
        playerHandPanel.setBounds(0, size.height - 300, size.width, 100);
        // Computer Config
        cCount.setBounds(size.width - 70, 10, 65, 90);
        computerStack.setBounds(size.width - 90, 10, 65, 90);
        computerStack.setOpaque(true);

        // Player Config
        playerStack.setBounds(2, getHeight()-250, 65, 90);
        pCount.setBounds(19, getHeight()-250,65 , 90);
        // Adjust the bounds of the userNameLabel and computerNameLabel
        userNameLabel.setBounds(0, getHeight() - 150, 200, 30);
        computerNameLabel.setBounds(getWidth() - 250, 50, 200, 30);
    }

    // Method used to update the array of displayed cards that are showed to the player
    public void updatePlayerHand(ArrayList<Card> hand, Deck deck) {
        playerHandPanel.removeAll();
        for (Card card : hand) {
            ImageIcon icon = deck.getCardImage(card);
            if (icon != null) {
                JButton cardButton = new JButton(icon);
                cardButton.setActionCommand(card.getRank().toString());  // Set command to the card's rank
                cardButton.addActionListener(e -> cardClicked(e.getActionCommand()));
                playerHandPanel.add(cardButton);
            }
            playerHandPanel.revalidate();
            playerHandPanel.repaint();
        }
        System.out.println("Player hand updated with " + hand.size() + " cards.");
    }

    private void cardClicked(String rank) {
        // Notify controller that a rank was clicked
        firePropertyChange("cardClicked", "", rank);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button actions
    }

    public void addDrawCardButtonListener(ActionListener listener) {
        drawCardButton.addActionListener(listener);
    }

    public void addEndTurnButtonListener(ActionListener listener) {
        endTurnButton.addActionListener(listener);
    }

    public void updateGameStatus(String status) {
        gameStatusLabel.setText(status);
    }

    // Sets and Gets

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
        pCount.setText(String.valueOf(playerCount));
        pCount.repaint();
    }

    public void setCompCount(int compCount) {
        this.compCount = compCount;
        cCount.setText(String.valueOf(compCount));
        cCount.repaint();
    }
}
