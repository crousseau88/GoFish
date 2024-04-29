package Model;

import java.util.ArrayList;
//TODO add comments / javadoc to code

public class Hand {
    private ArrayList<Card> hand = new ArrayList<>();

    public Hand() {}

    public void addCard(Card card) {
        hand.add(card);
        System.out.println("Added: " + card + " | Hand Size: " + getCardCount());

    }

    public void clear() {
        hand.removeAll(hand);
    }
    public ArrayList<Card> getCards() {
        return hand;
    }
    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    @Override
    public String toString() {
        return " " + hand;
    }

    public boolean removeCard(Card card) {
        boolean removed = hand.remove(card);
        System.out.println("Removed: " + card + " | Hand Size: " + getCardCount() + " | Successful: " + removed);
        return removed;
    }

    public int getCardCount() {
        return hand.size();
    }

}
