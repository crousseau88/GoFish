package Model;
/**
 * Filename: Player.java
 * Short description:Player class for program
 * IST 242 Assignment:GUI Programming Project
 * @author Chad Rousseau, Christopher Rusnak, Tyler Mascherino
 * @version 05/3/2024
 */
import java.util.*;


public class Player {

    //main class for Players
    private Hand hand;
    private Hand books = new Hand();
    //keep track of wins can write to file and rank players not implemented
    private int score;
    private String username = "Computer";


    // Constructor
    public Player() {
        this.hand = new Hand();
        this.score = 0;

    }

    // Gets the player's hand of cards
    public Hand getHand() {
        return hand;
    }


    // Add a card to the player's hand
    public void addCardToHand(Card card) {
        this.hand.addCard(card);
        System.out.println("Player "+ hand.getHand().toString());
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

    // Asks other player for random rank from the current hand
    public Rank chooseRankToAskFor() {
        ArrayList<Card> cards = getHand().getCards();
        if (!cards.isEmpty()) {
            int index = new Random().nextInt(cards.size());
            return cards.get(index).getRank();
        }
        return null;
    }

//    // Respond to another player's request for a card
//    public boolean respondToCardRequest(Card card) {
//        return false;
//    }

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

    // Creates map where rank is the key and the card array list is the value
    // loops through hand to build hashmap
    // iterates through the map to identify pairs
    // removes and stores pairs in books
    public boolean checkForAndAddPairs() {
        Map<Rank, ArrayList<Card>> rankCount = new HashMap<>();
        boolean pairFound = false;

        for (Card card : new ArrayList<>(hand.getCards())) { // Uses a copy for safe removal
            rankCount.putIfAbsent(card.getRank(), new ArrayList<>());
            rankCount.get(card.getRank()).add(card);
        }

        List<Card> toRemove = new ArrayList<>();
        for (Map.Entry<Rank, ArrayList<Card>> entry : rankCount.entrySet()) {
            ArrayList<Card> cardsOfSameRank = entry.getValue();
            if (cardsOfSameRank.size() >= 2) {
                pairFound = true;
                while (cardsOfSameRank.size() >= 2) {
                    toRemove.add(cardsOfSameRank.get(0));
                    toRemove.add(cardsOfSameRank.get(1));
                    books.addCard(cardsOfSameRank.get(0));
                    books.addCard(cardsOfSameRank.get(1));
                    cardsOfSameRank.remove(0);
                    cardsOfSameRank.remove(0);
                }
            }
        }

        hand.getCards().removeAll(toRemove);
        return pairFound;
    }
    //method for checking if hand contains rank
    public boolean hasRank(Rank rank) {
        for (Card card : hand.getCards()) {
            if (card.getRank() == rank) {
                return true;
            }
        }
        return false;
    }
    //getters and setters
    public Hand getBooks() {
        return books;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //gives other player card from hand
    public Card giveCard(Rank rank) {
        for (Card card : hand.getCards()) {
            if (card.getRank() == rank) {
                hand.removeCard(card);
                return card;
            }
        }
        return null;
    }
}