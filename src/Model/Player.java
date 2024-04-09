package Model;

public abstract class Player {

    //main class for Players
    private Hand hand;
    private Hand books = new Hand();
    //keep track of wins can write to file and rank players
    private int score;

    // Constructor
    public Player() {
        this.hand = new Hand();
        this.score = 0;
    }

    // Gets the player's hand of cardss
    public Hand getHand() {
        return hand;
    }



    // Add a card to the player's hand
    public void addCardToHand(Card card) {
        this.hand.addCard(card);
    }

    // Remove a card from the player's hand
    public boolean removeCardFromHand(Card card) {
        return this.hand.removeCard(card);
    }

    // Adds cards to books (pairs of cards)
    public void addBook(Card first, Card second) {
        this.books.addCard(first);
        this.books.addCard(second);
    }

    // Gets count of books (pairs) each player has
    public int getBookCount() {
        return this.books.getCardCount() / 2;
    }

    // Ask another player for a card
    // Code is here not LivePlayer in case future players added
    public Card askForCard(Player otherPlayer, Rank rank) {
        for (Card card : otherPlayer.getHand().getCards()) {
            if (card.getRank().equals(rank)) {
                otherPlayer.getHand().removeCard(card);
                this.hand.addCard(card);
                return card;
            }
        }
        return null;
    }

    // Respond to another player's request for a card
    public boolean respondToCardRequest(Card card) {
        return false;
    }

    // Increase player's score
    public void increaseScore(int points) {
        this.score += points;
    }

    // Get player's score
    public int getScore() {
        return score;
    }

    // Reset player's hand and score for a new game
    public void resetPlayer() {
        this.hand.clear();
        this.score = 0;
    }



}
