package Model;

public class Model {
    private Player player1;
    private Player computer;
    private Deck deck;
    private Rank rank;

    public Model() {
        player1 = new LivePlayer();
        computer = new ComputerPlayer();

    }

    public void startGame() {
        deck.shuffleDeck();
        deck.dealCard();
    }


    public void playerTurn(Rank chosenRank) {

        Card receivedCard = player1.askForCard(computer, chosenRank);

        if (receivedCard != null) {
        } else {
            Card drawnCard = deck.dealCard();
            player1.getHand().addCard(drawnCard);

        }

    }

    public void computerTurn() {
        Rank chosenRank = computer.chooseRankToAskFor();
        if (chosenRank != null) {
            Card receivedCard = computer.askForCard(player1, chosenRank);
            if (receivedCard == null) {

                Card drawnCard = deck.dealCard();
                computer.getHand().addCard(drawnCard);

            }
        }
    }
}
