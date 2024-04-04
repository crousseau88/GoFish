package Model;

public abstract class Player {

    //main class for Players
    private Hand hand;
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

    // Ask another player for a card
    public abstract Card askForCard();

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
