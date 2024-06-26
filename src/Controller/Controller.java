package Controller;

/**
 * Filename: Controller.java
 * Short description:Controller class for program
 * IST 242 Assignment:GUI Programming Project
 *
 * @author Chad Rousseau, Christopher Rusnak, Tyler Mascherino
 * @version 05/3/2024
 */
import Model.Card;
import Model.Model;
import Model.Rank;
import View.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Controller{
    private Model model;
    private View view;

    //constructor
    public Controller(Model m, View v) {
        this.model = m;
        this.view = v;


        // Setup game initially
        setupGame();
        addGameListeners();
        if (this.view.getMf().getGamePanel() == null) {
            throw new IllegalStateException("GamePanel not initialized.");
        }
    }

    // method to get book count
    private void displayBookCount() {
        SwingUtilities.invokeLater(() -> {
            view.getMf().getGamePanel().setCompCount(model.getComputer().getBookCount());
            view.getMf().getGamePanel().setPlayerCount(model.getPlayer().getBookCount());
            view.getMf().getGamePanel().revalidate();
            view.getMf().getGamePanel().repaint();
        });
    }

    //sets up game
    private void setupGame() {
        view.getMf().getGamePanel().updatePlayerHand(model.getPlayer().getHand().getCards(), model.getDeck());
        model.getPlayer().checkForAndAddPairs();
        model.getComputer().checkForAndAddPairs();
        updateGameView();
    }

    //updates the game view
    private void updateGameView() {
        displayBookCount(); // Updates the book counts
        model.getPlayer().checkForAndAddPairs(); // Checks pairs for both players
        model.getComputer().checkForAndAddPairs();
        checkForGameEnd();
        SwingUtilities.invokeLater(() -> { //updates the game status and repaints
            //the invokeLater() method is a thread safe way to trigger an update event within Swing.
            view.getMf().getGamePanel().updatePlayerHand(model.getPlayer().getHand().getCards(), model.getDeck());
            String turnStatus = model.isPlayerTurn() ? "Player's turn." : "Computer's turn.";
            view.getMf().getGamePanel().updateGameStatus("It is: " + turnStatus);
            view.getMf().getGamePanel().revalidate();
            view.getMf().getGamePanel().repaint();
        });

    }

    //creates and adds game listeners
    private void addGameListeners() {
        setupCardClickListeners();
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
                displayComingSoon();
            }
        });


        view.getMf().getGamePanel().addDrawCardButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Draw Card button clicked.");
                model.drawCard(model.getPlayer());
                view.getMf().getGamePanel().updatePlayerHand(model.getPlayer().getHand().getCards(), model.getDeck());
                view.getMf().getGamePanel().updateGameStatus("Card Drawn. Your turn continues.");

                view.getMf().getGamePanel().revalidate();
                view.getMf().getGamePanel().repaint();
            }
        });

        view.getMf().getGamePanel().addEndTurnButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Player manually ends their turn
                model.getPlayer().checkForAndAddPairs();
                checkForGameEnd();
                model.endTurn();
                System.out.println("Player ends turn");
                // computer automatically takes its turn
                boolean uiNeedsUpdate = model.computerTurn();
                if (uiNeedsUpdate) {
                    SwingUtilities.invokeLater(() -> {
                        System.out.println("Computer ends turn");
                        view.getMf().getGamePanel().updateGameStatus("Player's turn.");
                        updateGameView();  // UI updates after the computer's turn
                    });
                }
            }
        });

        view.getTp().getRulesButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayRules();
            }
        });
    }


    //sets up card click listener
    private void setupCardClickListeners() {
        view.getMf().getGamePanel().addPropertyChangeListener("cardClicked", evt -> { //event listener lambda expression
            handleCardRequest((String) evt.getNewValue());
            view.getMf().getGamePanel().revalidate();
            view.getMf().getGamePanel().repaint();
        });
    }

    private void handleCardRequest(String rank) {
        Rank requestedRank = Rank.valueOf(rank);
        boolean hasCard = model.askComputerForRank(requestedRank);
        if (hasCard) {
            Card cardRecieved = model.getComputer().giveCard(requestedRank);
            view.getMf().getGamePanel().updateGameStatus("Computer has the card of rank: " + rank);
            model.getPlayer().addCardToHand(cardRecieved);
            model.checkForPairs();
            model.toggleTurn();
            displayMatchFound();


        } else {
            view.getMf().getGamePanel().updateGameStatus("Computer does not have the card of rank: " + rank);

        }
        view.getMf().getGamePanel().revalidate();
        view.getMf().getGamePanel().repaint();

    }


    private void displayComingSoon() {
        JFrame soonFrame = new JFrame("Coming Soon");
        soonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        soonFrame.setSize(400, 300);
        soonFrame.setLocationRelativeTo(null);

        JTextArea soonTextArea = new JTextArea();
        soonTextArea.setEditable(false);
        soonTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        soonTextArea.setBackground(new Color(0, 100, 0));
        soonTextArea.setForeground(Color.white);
        soonTextArea.setLineWrap(true);
        soonTextArea.setWrapStyleWord(true);

        String comingSoon = "This implementation is coming soon!\n" +
                "If you would like to play exit this window and select 2 card. \n"+
                "Thank you!";

        soonTextArea.setText(comingSoon);
        JScrollPane scrollPane = new JScrollPane(soonTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        soonFrame.add(scrollPane);
        soonFrame.setVisible(true);
    }

    private void displayMatchFound(){
        JFrame matchFrame = new JFrame("Match Found");
        matchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        matchFrame.setSize(400, 300);
        matchFrame.setLocationRelativeTo(null);

        JTextArea matchTextArea = new JTextArea();
        matchTextArea.setEditable(false);
        matchTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        matchTextArea.setBackground(new Color(0, 100, 0));
        matchTextArea.setForeground(Color.WHITE);
        matchTextArea.setLineWrap(true);
        matchTextArea.setWrapStyleWord(true);

        String matchText = "Match Found and added to book\n" + "Your book count is: " + model.getPlayer().getBookCount()
                + "\nComputers book count: " + model.getComputer().getBookCount() + " Computers hand count: " + model.getComputer().getHand().getCards().size();

        matchTextArea.setText(matchText);
        JScrollPane scrollPane = new JScrollPane(matchTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        matchFrame.add(scrollPane);
        matchFrame.setVisible(true);
    }
    private void displayRules() {
        JFrame rulesFrame = new JFrame("Go Fish Rules");
        rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rulesFrame.setSize(400, 300);
        rulesFrame.setLocationRelativeTo(null);

        JTextArea rulesTextArea = new JTextArea();
        rulesTextArea.setEditable(false);
        rulesTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        rulesTextArea.setBackground(new Color(0, 100, 0));
        rulesTextArea.setForeground(Color.WHITE);
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

    //method to check if game has ended
    private void checkForGameEnd() {
        if (model.getDeck().isDeckEmpty() && model.getPlayer().getHand().getCardCount() == 0 && model.getComputer().getHand().getCardCount() == 0) {
            displayWinner();
        }
    }

    //method to display popout when player or computer wins
    private void displayWinner() {
        String winnerMessage = model.determineWinner();
        JOptionPane.showMessageDialog(null, winnerMessage, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        view.getMf().dispose();
    }

}