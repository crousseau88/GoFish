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
        checkForPairs();
//        isPlayerTurn = true;
//        playTurn();

        //tests logic
        System.out.println("Live Player hand: " + player.getHand().toString());//prints hand for player1
        System.out.println("Computer hand: " + computer.getHand().toString());//prints hand for computer
        System.out.println("-----------------------------------------------");
        System.out.println("Live Player book count: " + player.getBookCount());
        System.out.println("Computer book count: " + computer.getBookCount());
        System.out.println("-----------------------------------------------");
        System.out.println("Live Player hand: " + player.getHand().toString());//prints hand for player1
        System.out.println("Computer hand: " + computer.getHand().toString());//prints hand for computer
        System.out.println("-----------------------------------------------");
        System.out.println("Username:" + player.getUsername());
        System.out.println("-----------------------------------------------");



    }

    public void playTurn() {
        if (isPlayerTurn) {
            playerTurn();
        } else {
            computerTurn();
        }
    }

    private void dealInitialCards() {
        for (int i = 0; i < 7; i++) {
            player.addCardToHand(deck.dealCard());
            computer.addCardToHand(deck.dealCard());
        }
    }

    //checks for book pairs not currently working as designed need to ts
    private void checkForPairs() {
        boolean playerFoundPair = player.checkForAndAddPairs();
        boolean computerFoundPair = computer.checkForAndAddPairs();

        System.out.println("Pairs checked. Player found: " + playerFoundPair + ", Computer found: " + computerFoundPair);

        // Only draw cards if pairs were found to avoid unnecessary drawing
        if (playerFoundPair) {
            System.out.println("Player pairs made. Replenishing...");
            drawCardsToMinimum(player, 7);
        }
        if (computerFoundPair) {
            System.out.println("Computer pairs made. Replenishing...");
            drawCardsToMinimum(computer, 7);
        }
    }


    private void drawCardsToMinimum(Player player, int minimumCardCount) {
        System.out.println("Starting draw to minimum for " + player.getUsername() + " needing " + minimumCardCount);

        while (player.getHand().getCardCount() < minimumCardCount && !deck.isDeckEmpty()) {
            drawCard(player);
        }
        System.out.println("Ending draw to minimum for " + player.getUsername() + " with " + player.getHand().getCardCount() + " cards");

    }

    //draws cards
    public void drawCard(Player player) {
        if (!deck.isDeckEmpty()) {
            Card drawnCard = deck.dealCard();
            player.addCardToHand(drawnCard);
            System.out.println("-----------------------------------------------");
            System.out.println(player.getUsername() + " draws a " + drawnCard);
            System.out.println("-----------------------------------------------");
        }
    }

    private void askForCardFromComputer() {
        Rank requestedRank = player.chooseRankToAskFor();
        Card receivedCard = computer.giveCard(requestedRank);
        if (receivedCard != null) {
            player.addCardToHand(receivedCard);
            System.out.println("-----------------------------------------------");
            System.out.println("Player received " + receivedCard + " from computer.");
            System.out.println("-----------------------------------------------");
            checkForPairs();
        } else {
            System.out.println("-----------------------------------------------");
            System.out.println("Go Fish");
            System.out.println("-----------------------------------------------");
            drawCard(player);  // Ensures the correct player is specified
        }
    }

    public void playerTurn() {
        checkForPairs();
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
                canPlay = computer.checkForAndAddPairs();
            }
        }

        if (!canPlay && !deck.isDeckEmpty()) {
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
