package Model;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand = new ArrayList<>();

    public Hand() {}

    public void addCard(Card card) {
        hand.add(card);
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
        return hand.remove(card);
    }

    public int getCardCount() {
        return hand.size();
    }

}
