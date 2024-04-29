package Model;

import java.awt.*;
import java.util.Objects;
//TODO add comments / javadoc to code

public class Card {
    //Instance variables
    private Suit suit;
    private Rank rank;
    private int img;

    //image associated with card as string then use string to bring up image file/icon


    public Card(Suit suit, Rank rank, int img) {
        this.rank = rank;
        this.suit = suit;
        this.img = img;


    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getValue() {
        return rank.getValue();
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Card other = (Card) obj;
        return rank == other.rank && suit == other.suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

}
