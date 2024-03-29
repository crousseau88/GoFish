package Model;

import java.util.ArrayList;

public abstract class Hand {
    private ArrayList<Card> hand = new ArrayList<>();

    public Hand() {}

    public void addCard(Card card) {
        hand.add(card);
    }

    public void clear() {
        hand.removeAll(hand);
    }
    public ArrayList<Card> getCard() {
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
}
