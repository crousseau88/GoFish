package Model;

public class Model {
    private Player player;
    private Player computer;
    private Deck deck;
    private Hand hand;


    public Model() {
        player = new Player();
        computer = new Player();

        deck = new Deck();
        hand = new Hand();


    }

    public void startGame() {
        deck.shuffleDeck();


        dealInitialCards();

        //tests logic
        System.out.println("Live Player hand: " + player.getHand().toString());//prints hand for player1
        System.out.println("Computer hand: " + computer.getHand().toString());//prints hand for computer
        checkForPairs(player);
        checkForPairs(computer);
        System.out.println("-----------------------------------------------");
        System.out.println("Live Player book count: " + player.getBookCount());
        System.out.println("Computer book count: " + computer.getBookCount());

        System.out.println("Live Player hand: " + player.getHand().toString());//prints hand for player1
        System.out.println("Computer hand: " + computer.getHand().toString());//prints hand for computer



    }


    private void dealInitialCards() {
        for (int i = 0; i < 7; i++) {
            player.addCardToHand(deck.dealCard());
            computer.addCardToHand(deck.dealCard());
        }
    }

    //checks for book pairs
    private void checkForPairs(Player player) {
        player.checkForAndAddPairs();
        if (player.getBooks().getCardCount() > 0) {
            System.out.println(player.getUsername() + " has made some pairs.");
        }
    }
    private void askForCardFromComputer() {
        Rank requestedRank = player.chooseRankToAskFor(); // This could be triggered by UI interaction
        Card receivedCard = computer.giveCard(requestedRank);
        if (receivedCard != null) {
            player.addCardToHand(receivedCard);
            System.out.println("Player received " + receivedCard + " from computer.");
            checkForPairs(player);
        } else {
            System.out.println("Go Fish");
            drawCard(player);
        }
    }

    public void playerTurn() {
        if (!player.checkForAndAddPairs()) {
            askForCardFromComputer();
        }

    }

    public void computerTurn() {
        boolean drawCard = false;
        Rank chosenRank = computer.chooseRankToAskFor();
        Card receivedCard = computer.askForCard(player, chosenRank);

        if (receivedCard == null && !deck.isDeckEmpty()) {
            computer.getHand().addCard(deck.dealCard());
            drawCard = true;
        }

        if (drawCard && computer.checkForAndAddPairs()) {
            drawCards(computer);
        }
    }

    private void drawExtraCards(Player player) {
        for (int i = 0; i < 2 && !deck.isDeckEmpty(); i++) {
            player.getHand().addCard(deck.dealCard());
        }
    }

    private void drawCards(Player player) {
        boolean foundPairs = false;
        while (player.getHand().getCardCount() < 7 && !deck.isDeckEmpty()) {
            player.getHand().addCard(deck.dealCard());

            if (player.checkForAndAddPairs()) {
                foundPairs = true;
            }
        }

        if (foundPairs) {
            System.out.println("Extra cards drawn due to forming pairs");
            drawCards(player);
        }
    }

    private void drawCard(Player player) {
        if (!deck.isDeckEmpty()) {
            Card drawnCard = deck.dealCard();
            player.addCardToHand(drawnCard);
            checkForPairs(player);
        }
    }

    public void setPlayerUsername(String username) {
        player.setUsername(username);
    }

    public boolean isGameOver() {
        return player.getHand().getCardCount() == 0 && computer.getHand().getCardCount() == 0;
    }

    public Player getPlayer1() {
        return player;
    }

    public void setPlayer1(Player player1) {
        this.player = player1;
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

    public void playerDrawCard() {
        if (!deck.isDeckEmpty()) {
            Card drawnCard = deck.dealCard();
            player.addCardToHand(drawnCard);

            boolean pairFound = player.checkForAndAddPairs();
            if (pairFound) {
                drawExtraCards(player);
            }
        }
    }

    public void endTurn() {
        boolean isPlayerTurn = true;
        if (!isPlayerTurn) {
            computerTurn();
        }

        if (isGameOver()) {
            determineWinner();
        }
    }

    private String determineWinner() {
        if (player.getBookCount() > computer.getBookCount()) {
            return "Player";
        } else if (computer.getBookCount() > player.getBookCount()) {
            return "Computer";
        } else {
            return "It's a tie!";
        }
    }
}
