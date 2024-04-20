package Model;

public class Model {
    private Player player;
    private Player computer;
    private Deck deck;
    private Hand hand;
    private boolean isPlayerTurn;


    public Model() {
        player = new Player();
        computer = new Player();

        deck = new Deck();
        hand = new Hand();


    }

    public void startGame() {
        deck.shuffleDeck();
        dealInitialCards();
        checkForPairs(player);
        checkForPairs(computer);
        isPlayerTurn = true;
        playTurn();

        //tests logic
        System.out.println("Live Player hand: " + player.getHand().toString());//prints hand for player1
        System.out.println("Computer hand: " + computer.getHand().toString());//prints hand for computer
        System.out.println("-----------------------------------------------");
        System.out.println("Live Player book count: " + player.getBookCount());
        System.out.println("Computer book count: " + computer.getBookCount());
        System.out.println("Live Player hand: " + player.getHand().toString());//prints hand for player1
        System.out.println("Computer hand: " + computer.getHand().toString());//prints hand for computer


    }

    private void manageTurns() {
        while (!isGameOver()) {
            if (isPlayerTurn) {
                playerTurn();
            } else {
                computerTurn();
            }
            isPlayerTurn = !isPlayerTurn;  // Toggle turns
        }
        System.out.println(determineWinner() + " wins the game!");
    }
    public void playTurn() {
        if (isPlayerTurn) {
            playerTurn();
        } else {
            computerTurn();
        }
    }
    private void dealInitialCards() {
        for (int i = 0; i <= 7; i++) {
            player.addCardToHand(deck.dealCard());
            computer.addCardToHand(deck.dealCard());
        }
    }

    //checks for book pairs
    private void checkForPairs(Player player) {
        boolean playerFoundPair = player.checkForAndAddPairs();
        boolean computerFoundPair = computer.checkForAndAddPairs();

        if (playerFoundPair) {
            System.out.println(player.getUsername() + " has made some pairs.");
            drawCardsToMinimum(player, 7);
        }
        if (computerFoundPair) {
            System.out.println("Computer has made some pairs.");
            drawCardsToMinimum(computer, 7);
        }
    }


    private void drawCardsToMinimum(Player player, int minimumCardCount) {
        while (player.getHand().getCardCount() < minimumCardCount && !deck.isDeckEmpty()) {
            Card drawnCard = deck.dealCard();
            player.getHand().addCard(drawnCard);
        }
    }


    private void askForCardFromComputer() {
        Rank requestedRank = player.chooseRankToAskFor();
        Card receivedCard = computer.giveCard(requestedRank);
        if (receivedCard != null) {
            player.addCardToHand(receivedCard);
            System.out.println("Player received " + receivedCard + " from computer.");
            checkForPairs(player);
        } else {
            System.out.println("Go Fish");
            drawCard();
        }
    }

    public void playerTurn() {
        if (!player.checkForAndAddPairs()) {
            askForCardFromComputer();
        }

    }

    public void toggleTurn() {
        isPlayerTurn = !isPlayerTurn;
    }

    public void computerTurn() {
            boolean canPlay = false;

            Rank chosenRank = computer.chooseRankToAskFor();
            if (chosenRank != null) {
                Card receivedCard = computer.askForCard(player, chosenRank);

                if (receivedCard != null) {
                    computer.getHand().addCard(receivedCard);
                    System.out.println("Computer received " + receivedCard + " from player.");
                    canPlay = computer.checkForAndAddPairs();  // Check if adding this card created a pair
                }
            }

            if (!canPlay && !deck.isDeckEmpty()) {
                // Computer draws a card from the deck if it cannot play
                Card drawnCard = deck.dealCard();
                computer.getHand().addCard(drawnCard);
                System.out.println("Computer draws a " + drawnCard + " from deck.");
                canPlay = computer.checkForAndAddPairs();
            }

            if (!canPlay) {
                System.out.println("Computer has no moves left and ends its turn.");
            }
            toggleTurn();
            if (isPlayerTurn) {
                playerTurn();
            }

    }

    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }
    private void drawExtraCards(Player player) {
        for (int i = 0; i < 2 && !deck.isDeckEmpty(); i++) {
            player.getHand().addCard(deck.dealCard());
        }
    }

    public void drawCard() {
        if (!deck.isDeckEmpty()) {
            Card drawnCard = deck.dealCard();
            player.addCardToHand(drawnCard);
        }
    }

    public boolean askComputerForRank(Rank rank) {
        return computer.hasRank(rank);
    }


    public void setPlayerUsername(String username) {
        player.setUsername(username);
    }

    public boolean isGameOver() {
        return player.getHand().getCardCount() == 0 && computer.getHand().getCardCount() == 0;
    }


    public Player getComputer() {
        return computer;
    }

    public void setComputer(Player computer) {
        this.computer = computer;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Player getPlayer() {
        return player;
    }


    public void endTurn() {
        isPlayerTurn = !isPlayerTurn;
        System.out.println((isPlayerTurn ? "Player" : "Computer") + "'s turn.");
    }

    public String determineWinner() {
        if (player.getBookCount() > computer.getBookCount()) {
            return "Player";
        } else if (computer.getBookCount() > player.getBookCount()) {
            return "Computer";
        } else {
            return "It's a tie!";
        }
    }
}
