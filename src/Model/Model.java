package Model;

public class Model {
    private Player player1;
    private Player computer;
    private Deck deck;
    private Hand hand;


    public Model() {
        player1 = new LivePlayer();
        computer = new ComputerPlayer();
        deck = new Deck();
        hand = new Hand();


    }

    public void startGame() {
        deck.shuffleDeck();

        dealInitialCards();

//

        System.out.println(player1.getHand().toString());//prints hand for player1
        System.out.println(computer.getHand().toString());//prints hand for computer
        checkInitialPairs();
        System.out.println(player1.getBookCount());
        System.out.println(computer.getBookCount());
        drawCards(player1);
        drawCards(computer);
        System.out.println(player1.getHand().toString());
        System.out.println(computer.getHand().toString());

    }

    private void dealInitialCards() {
        for (int i = 0; i < 7; i++) {
            player1.addCardToHand(deck.dealCard());
            computer.addCardToHand(deck.dealCard());
        }
    }

    private void checkInitialPairs() {
        player1.checkForAndAddPairs();
        computer.checkForAndAddPairs();
    }

    public void playerTurn(Rank chosenRank) {
        Card receivedCard = player1.askForCard(computer, chosenRank);
        boolean drawCard = false;

        if (receivedCard == null && !deck.isDeckEmpty()) {
            player1.getHand().addCard(deck.dealCard());
            drawCard = true;  // Indicate that a card was drawn because the asked-for card wasn't received.
        }

        // If a pair is formed after drawing from the deck, draw an extra card.
        if (drawCard && player1.checkForAndAddPairs()) {
            drawExtraCards(player1); // Assuming the requirement is to draw two extra cards.
        }
    }

    public void computerTurn() {
        boolean drawCard = false;
        Rank chosenRank = computer.chooseRankToAskFor();
        Card receivedCard = computer.askForCard(player1, chosenRank);

        if (receivedCard == null && !deck.isDeckEmpty()) {
            computer.getHand().addCard(deck.dealCard());
            drawCard = true;
        }

        if (drawCard && computer.checkForAndAddPairs()) {
            drawExtraCards(computer);
        }
    }

    // Draws extra cards when player needs to draw from deck not sure if works
    private void drawExtraCards(Player player) {
        for (int i = 0; i < 2 && !deck.isDeckEmpty(); i++) {
            player.getHand().addCard(deck.dealCard());
        }
    }

    private void drawCards(Player player) {
        while (player.getHand().getCardCount() < 7 && !deck.isDeckEmpty()) {
            player.getHand().addCard(deck.dealCard());
            System.out.println("test deal");
        }
        if (player.checkForAndAddPairs()) {
            System.out.println("test draw");
            drawExtraCards(player);
        }
    }

    public boolean isGameOver() {
        return player1.getHand().getCardCount() == 0 && computer.getHand().getCardCount() == 0;
    }
}
