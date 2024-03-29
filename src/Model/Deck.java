package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<>();


    public Deck() {

        for(Suit suit : Suit.values()) {
            for(Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }

    }
    public int checkDeckSize() {
        return deck.size();
    }
    public void shuffleDeck() {
        Collections.shuffle(deck);

    }
    public Card dealCard() {
        return deck.remove(0);
    }

}
