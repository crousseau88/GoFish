package View;

import Model.Player;
import Model.Card;
import Model.Deck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {
    private JLabel gameStatusLabel;
    private JButton endTurnButton;
    private JButton drawCardButton;
    private JPanel playerHandPanel;
    private int test = 10;

    public GamePanel(Player player1, Deck deck) {
        setLayout(null);
        setBackground(new Color(0, 100, 0));


        // Username
        JLabel userName = new JLabel(player1.getUsername());
        userName.setForeground(Color.WHITE);
        userName.setFont(new Font("Arial", Font.PLAIN, 20));
        userName.setBounds(0, 400, 200, 30);
        add(userName);

        // Computer name label
        JLabel computerName = new JLabel("Player: Computer");
        computerName.setForeground(Color.WHITE);
        computerName.setFont(new Font("Arial", Font.PLAIN, 20));
        computerName.setBounds(630, 10, 200, 150);
        add(computerName);

        // Game Status Label
        gameStatusLabel = new JLabel("Game Status: In Progress");
        gameStatusLabel.setForeground(Color.WHITE);
        gameStatusLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gameStatusLabel.setBounds(250, 0, 300, 50);
        add(gameStatusLabel);

        // Draw Card Button
        ImageIcon drawIcon = new ImageIcon("Cards/b1fh.png");
        drawCardButton = new JButton(drawIcon);
        drawCardButton.setBounds(340, 50, 90, 65);
        drawCardButton.addActionListener(this);
        add(drawCardButton);

        // End Turn Button
        endTurnButton = new JButton("End Turn");
        endTurnButton.setBounds(0, 510, 800, 20);
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
        ImageIcon cStackIcon = new ImageIcon("Cards/b1fv.png");
        JLabel computerStack = new JLabel(cStackIcon);
        computerStack.setBounds(new Rectangle(710, 110, 65, 90));

        JLabel cCount = new JLabel("" + test);
        cCount.setForeground(Color.YELLOW);
        cCount.setFont(new Font("Arial", Font.PLAIN, 25));
        cCount.setBounds(new Rectangle(725, 110, 65, 90));
        add(cCount);

        add(computerStack);


        // Player Config
        // used for the Player match stack, displayable cards
        ImageIcon pStackIcon = new ImageIcon("Cards/b2fv.png");
        JLabel playerStack = new JLabel(pStackIcon);
        playerStack.setBounds(new Rectangle(0, 310, 65, 90));


        JLabel pCount = new JLabel("" + test);
        pCount.setForeground(Color.YELLOW);
        pCount.setFont(new Font("Arial", Font.PLAIN, 25));
        pCount.setBounds(new Rectangle(19, 310, 65, 90));

        add(pCount);
        add(playerStack);


    }

    public void updatePlayerHand(ArrayList<Card> hand, Deck deck) {
        playerHandPanel.removeAll();
        for (Card card : hand) {
            ImageIcon icon = deck.getCardImage(card);
            if (icon != null) {
                JLabel cardLabel = new JLabel(icon);
                playerHandPanel.add(cardLabel);
            }
            playerHandPanel.revalidate();
            playerHandPanel.repaint();
        }
        playerHandPanel.revalidate();
        playerHandPanel.repaint();

    }
    private void cardClicked(String rank) {
        // Notify controller that a rank was clicked
        firePropertyChange("cardClicked", "", rank);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

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
}