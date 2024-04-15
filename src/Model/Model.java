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

        //tests logic
        System.out.println("Live Player hand: " + player1.getHand().toString());//prints hand for player1
        System.out.println("Computer hand: " + computer.getHand().toString());//prints hand for computer
        checkForPairs();
        System.out.println("-----------------------------------------------");
        System.out.println("Live Player book count: " + player1.getBookCount());
        System.out.println("Computer book count: " + computer.getBookCount());
        drawCards(player1);
        drawCards(computer);
        System.out.println("-----------------------------------------------");
        System.out.println("Live Player hand: " + player1.getHand().toString());
        System.out.println("Computer hand: " + computer.getHand().toString());
        checkForPairs();
        System.out.println("-----------------------------------------------");
        System.out.println("Live Player book count: " + player1.getBookCount());
        System.out.println("Computer book count: " + computer.getBookCount());

        drawCards(player1);
        drawCards(computer);
        System.out.println("-----------------------------------------------");
        System.out.println("Live Player hand: " + player1.getHand().toString());
        System.out.println("Computer hand: " + computer.getHand().toString());
        System.out.println("-----------------------------------------------");
        System.out.println("Live Player books: " + player1.getBooks().toString());
        System.out.println("Computer books: " + computer.getBooks().toString());


    }


    private void dealInitialCards() {
        for (int i = 0; i < 7; i++) {
            player1.addCardToHand(deck.dealCard());
            computer.addCardToHand(deck.dealCard());
        }
    }

    //checks for book pairs
    private void checkForPairs() {
        player1.checkForAndAddPairs();
        computer.checkForAndAddPairs();
    }

    public void playerTurn(Rank chosenRank) {
        Card receivedCard = player1.askForCard(computer, chosenRank);
        boolean drawCard = false;

        if (receivedCard == null && !deck.isDeckEmpty()) {
            player1.getHand().addCard(deck.dealCard());
            drawCard = true;
        }


        if (drawCard && player1.checkForAndAddPairs()) {
            drawExtraCards(player1);
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

    public boolean isGameOver() {
        return player1.getHand().getCardCount() == 0 && computer.getHand().getCardCount() == 0;
    }
}
