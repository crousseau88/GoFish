package Model;

//TODO add comments / javadoc to code

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

        //currently unused console debug statements
//        System.out.println("Live Player hand: " + player.getHand().toString());//prints hand for player1
//        System.out.println("Computer hand: " + computer.getHand().toString());//prints hand for computer
//        System.out.println("-----------------------------------------------");
//        System.out.println("Live Player book count: " + player.getBookCount());
//        System.out.println("Computer book count: " + computer.getBookCount());
//        System.out.println("-----------------------------------------------");
//        System.out.println("Live Player hand: " + player.getHand().toString());//prints hand for player1
//        System.out.println("Computer hand: " + computer.getHand().toString());//prints hand for computer
//        System.out.println("-----------------------------------------------");
//        System.out.println("Username:" + player.getUsername());
//        System.out.println("-----------------------------------------------");

    }


    private void dealInitialCards() {
        for (int i = 0; i < 7; i++) {
            player.addCardToHand(deck.dealCard());
            computer.addCardToHand(deck.dealCard());
        }
    }

    //checks for book pairs not currently working as designed need to ts
    public void checkForPairs() {
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
            System.out.println(player.getUsername() + " draws a " + drawnCard);//check this line too printing computer when player draws
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
            drawCard(player);
        }
    }

//    public void playerTurn() {
//        checkForPairs();
//
//    }

    public void toggleTurn() {
        System.out.println("Before toggle: It is " + (isPlayerTurn ? "player's" : "computer's") + " turn.");
        isPlayerTurn = !isPlayerTurn;
        System.out.println("After toggle: It is now " + (isPlayerTurn ? "player's" : "computer's") + " turn.");

    }



    public void endTurn() {
        isPlayerTurn = !isPlayerTurn;
        System.out.println("Turn Ended, it is now " + (isPlayerTurn ? "Player" : "Computer") + "'s turn.");

    }

    public boolean computerTurn() {
        System.out.println("Computer's turn started.");
        boolean actionTaken = false;

        while (!actionTaken && !deck.isDeckEmpty()) {
            Rank chosenRank = computer.chooseRankToAskFor();
            if (chosenRank != null) {
                Card receivedCard = computer.askForCard(player, chosenRank);

                if (receivedCard != null) {

                    computer.getHand().addCard(receivedCard);
                    System.out.println("Computer successfully took " + receivedCard + " from player.");
                    actionTaken = true;
                    if (computer.checkForAndAddPairs()) {
                        System.out.println("Pair formed by computer. Checking for more...");
                        continue;
                    }
                }
            }
            break;
        }

        if (!actionTaken && !deck.isDeckEmpty()) {
            Card drawnCard = deck.dealCard();
            computer.getHand().addCard(drawnCard);
            System.out.println("Computer drew " + drawnCard + " from the deck.");
            actionTaken = true;
            computer.checkForAndAddPairs();
        }

        System.out.println("Computer's hand: " + computer.getHand().toString());
        System.out.println("Computer's books: " + computer.getBookCount());

        endTurn();
        toggleTurn();

        return actionTaken;
    }




    public boolean isPlayerTurn() {
        checkForPairs();
        return isPlayerTurn;
    }

    public boolean askComputerForRank(Rank rank) {
        return computer.hasRank(rank);
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


    public String determineWinner() {
        if (player.getHand().getCardCount() == 0 && computer.getHand().getCardCount() == 0) {
            return "Game over. " + (player.getBookCount() > computer.getBookCount() ? "Player wins!" : "Computer wins!");
        }
        return "Game still in progress.";
    }
}
