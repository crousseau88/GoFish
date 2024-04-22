package Model;

import java.util.*;

public class Player {

    //main class for Players
    private Hand hand;
    private Hand books = new Hand();
    //keep track of wins can write to file and rank players
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
        for (Card card : hand.getCards()) {
            rankCount.putIfAbsent(card.getRank(), new ArrayList<>());
            rankCount.get(card.getRank()).add(card);
        }
        Iterator<Map.Entry<Rank, ArrayList<Card>>> iterator = rankCount.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Rank, ArrayList<Card>> entry = iterator.next();
            ArrayList<Card> cardsOfSameRank = entry.getValue();

            if (cardsOfSameRank.size() == 2) {
                pairFound = true;
                for (Card card : cardsOfSameRank) {
                    books.addCard(card);
                    hand.removeCard(card);
                }
                increaseScore(1);
                iterator.remove();
            }
        }
        return pairFound;
    }
    public boolean hasRank(Rank rank) {
        for (Card card : hand.getCards()) {
            if (card.getRank() == rank) {
                return true;
            }
        }
        return false;
    }
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