package Model;
/**
 * Filename: Hand.java
 * Short description:Hand class for program
 * IST 242 Assignment:GUI Programming Project
 * @author Chad Rousseau, Christopher Rusnak, Tyler Mascherino
 * @version 05/3/2024
 */
import java.util.ArrayList;

public class Hand {
    //creates an array list of cards to be used as the hand
    private ArrayList<Card> hand = new ArrayList<>();


    public Hand() {}

    //adds cards to the hand
    public void addCard(Card card) {
        hand.add(card);
        System.out.println("Added: " + card + " | Hand Size: " + getCardCount()); //debug statement

    }

    //used to clear the hand of cards, not currently used but can be integrated in future versions to clear hands and restart game if desired.
    public void clear() {
        hand.removeAll(hand);
    }

    //gets and sets
    public ArrayList<Card> getCards() {
        return hand;
    }
    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }


    //toString method for hand used in debugging
    @Override
    public String toString() {
        return " " + hand;
    }

    //shows card is removed from hand during gameplay
    public boolean removeCard(Card card) {
        boolean removed = hand.remove(card);
        System.out.println("Removed: " + card + " | Hand Size: " + getCardCount() + " | Successful: " + removed); //debug statement
        return removed;
    }

    public int getCardCount() {
        return hand.size();
    }

}
