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
    public boolean isDeckEmpty() {
        return deck.isEmpty();
    }
    public void shuffleDeck() {
        Collections.shuffle(deck);

    }
    //deals cards from deck to players
    public Card dealCard() {
        if (!isDeckEmpty()) {
            return deck.remove(0);
        } else {
            throw new IllegalStateException("Cannot deal from an empty deck.");
        }
    }
}
