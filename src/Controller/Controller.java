package Controller;

import Model.Model;
import View.View;
import Model.Player;
import View.GamePanel;
import Model.Rank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;
    private Rank rank;

    public Controller(Model m, View v) {
        this.model = m;
        this.view = v;

        // Setup game initially
        setupGame();
        addGameListeners();
        if (this.view == null || this.view.getMf().getGamePanel() == null) {
            throw new IllegalStateException("View or GamePanel not initialized.");
        }
    }

    private void setupGame() {

        view.getMf().getGamePanel().updatePlayerHand(model.getPlayer().getHand().getCards(), model.getDeck());
    }
    private void initializeGame() {
        model.startGame(); // Shuffles deck, deals cards, checks initial pairs
        updateGameView();
    }


    private void updateGameView() {
        view.getMf().getGamePanel().updatePlayerHand(model.getPlayer().getHand().getCards(), model.getDeck());
        String turnStatus = model.isPlayerTurn() ? "Player's turn." : "Computer's turn.";
        view.getMf().getGamePanel().updateGameStatus("Game Started: " + turnStatus);    }


    private void addGameListeners() {

        JTextField userNameField = view.getTp().getUserNameField();
        userNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userNameField.getText().trim();
                if (!username.isEmpty()) {
                    model.getPlayer().setUsername(username);
                    System.out.println("Username set to: " + model.getPlayer().getUsername());
                } else {
                    System.out.println("No username entered");
                }
            }
        });

        view.getTp().getTwoPairButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.startGame();
                view.getMf().showGamePanel();
                view.getMf().getGamePanel().updatePlayerHand(model.getPlayer().getHand().getCards(), model.getDeck());
                view.getMf().getGamePanel().updateGameStatus("Game Started: It's your turn!");
            }
        });
        view.getTp().getFourPairButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.getMf().showGamePanel();
            }
        });


        view.getMf().getGamePanel().addDrawCardButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Draw Card button clicked.");
                model.drawCard();
                ;
                view.getMf().getGamePanel().updatePlayerHand(model.getPlayer().getHand().getCards(), model.getDeck());
                view.getMf().getGamePanel().updateGameStatus("Card Drawn. Your turn continues.");
            }
        });

        view.getMf().getGamePanel().addEndTurnButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.endTurn();
                view.getMf().getGamePanel().updateGameStatus("End of Turn. It is now computer's turn.");

            }
        });

        view.getTp().getRulesButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayRules();
            }
        });
    }

    private void setupCardClickListeners() {
        view.getMf().getGamePanel().addPropertyChangeListener("cardClicked", evt -> {
            handleCardRequest((String) evt.getNewValue());
        });
    }

    private void handleCardRequest(String rank) {
        Rank requestedRank = Rank.valueOf(rank);
        boolean hasCard = model.askComputerForRank(requestedRank);
        if (hasCard) {
            view.getMf().getGamePanel().updateGameStatus("Computer has the card of rank: " + rank);

        } else {
            view.getMf().getGamePanel().updateGameStatus("Computer does not have the card of rank: " + rank);

        }
    }

    private void displayRules() {
        JFrame rulesFrame = new JFrame("Go Fish Rules");
        rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rulesFrame.setSize(400, 300);
        rulesFrame.setLocationRelativeTo(null);

        JTextArea rulesTextArea = new JTextArea();
        rulesTextArea.setEditable(false);
        rulesTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        rulesTextArea.setLineWrap(true);
        rulesTextArea.setWrapStyleWord(true);

        String rulesText = "Go Fish Rules:\n" +
                "1. The game is played with a standard 52-card deck.\n" +
                "2. Each player is dealt a hand of 7 cards.\n" +
                "3. Players take turns asking opponents for a specific rank of card.\n" +
                "4. If the opponent has the requested card(s), they must give it to the player.\n" +
                "5. If the opponent does not have the requested card(s), the player must 'Go Fish' and draw a card from the deck.\n" +
                "6. If the drawn card matches the requested rank, the player continues their turn.\n" +
                "7. If the drawn card does not match the requested rank, the turn passes to the next player.\n" +
                "8. The game continues until all 13 sets of 4 cards have been collected.\n" +
                "9. The player with the most sets of 4 cards at the end of the game wins.";
        rulesTextArea.setText(rulesText);
        JScrollPane scrollPane = new JScrollPane(rulesTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rulesFrame.add(scrollPane);
        rulesFrame.setVisible(true);
    }
}
