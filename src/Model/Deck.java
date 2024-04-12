package Model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<>();
    //create arraylist of image icons
    private ArrayList<ImageIcon> img = new ArrayList<>();




    public Deck() {
        imageIcons();
        int counter = 0;
        for(Suit suit : Suit.values()) {
            for(Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank, counter));
                counter++;
            }
        }
        System.out.println(deck);//prints out deck
        System.out.println(deck.size());//tests deck contains 52 cards



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

    private void imageIcons(){
        img.add(new ImageIcon("Cards/1.png", "Ace of Spades"));
        img.add(new ImageIcon("Cards/2.png", "2 of Spades"));
        img.add(new ImageIcon("Cards/3.png", "3 of Spades"));
        img.add(new ImageIcon("Cards/4.png", "4 of Spades"));
        img.add(new ImageIcon("Cards/5.png", "5 of Spades"));
        img.add(new ImageIcon("Cards/6.png", "6 of Spades"));
        img.add(new ImageIcon("Cards/7.png", "7 of Spades"));
        img.add(new ImageIcon("Cards/8.png", "8 of Spades"));
        img.add(new ImageIcon("Cards/9.png", "9 of Spades"));
        img.add(new ImageIcon("Cards/10.png","10 of Spades"));
        img.add(new ImageIcon("Cards/11.png","J of Spades"));
        img.add(new ImageIcon("Cards/12.png","Q of Spades"));
        img.add(new ImageIcon("Cards/13.png", "K of Spades"));
        img.add(new ImageIcon("Cards/14.png", "A of Hearts"));
        img.add(new ImageIcon("Cards/15.png", "2 of Hearts"));
        img.add(new ImageIcon("Cards/16.png", "3 of Hearts"));
        img.add(new ImageIcon("Cards/17.png", "4 of Hearts"));
        img.add(new ImageIcon("Cards/18.png", "5 of Hearts"));
        img.add(new ImageIcon("Cards/19.png", "6 of Hearts"));
        img.add(new ImageIcon("Cards/20.png", "7 of Hearts"));
        img.add(new ImageIcon("Cards/21.png", "8 of Hearts"));
        img.add(new ImageIcon("Cards/22.png", "9 of Hearts"));
        img.add(new ImageIcon("Cards/23.png", "10 of Hearts"));
        img.add(new ImageIcon("Cards/24.png", "J of Hearts"));
        img.add(new ImageIcon("Cards/25.png", "Q of Hearts"));
        img.add(new ImageIcon("Cards/26.png", "K of Hearts"));
        img.add(new ImageIcon("Cards/27.png", "A of Diamonds"));
        img.add(new ImageIcon("Cards/28.png", "2 of Diamonds"));
        img.add(new ImageIcon("Cards/29.png", "3 of Diamonds"));
        img.add(new ImageIcon("Cards/30.png", "4 of Diamonds"));
        img.add(new ImageIcon("Cards/31.png", "5 of Diamonds"));
        img.add(new ImageIcon("Cards/32.png", "6 of Diamonds"));
        img.add(new ImageIcon("Cards/33.png", "7 of Diamonds"));
        img.add(new ImageIcon("Cards/34.png", "8 of Diamonds"));
        img.add(new ImageIcon("Cards/35.png", "9 of Diamonds"));
        img.add(new ImageIcon("Cards/36.png", "10 of Diamonds"));
        img.add(new ImageIcon("Cards/37.png", "J of Diamonds"));
        img.add(new ImageIcon("Cards/38.png", "Q of Diamonds"));
        img.add(new ImageIcon("Cards/39.png", "K of Diamonds"));
        img.add(new ImageIcon("Cards/40.png", "A of Clubs"));
        img.add(new ImageIcon("Cards/41.png", "2 of Clubs"));
        img.add(new ImageIcon("Cards/42.png", "3 of Clubs"));
        img.add(new ImageIcon("Cards/43.png", "4 of Clubs"));
        img.add(new ImageIcon("Cards/44.png", "5 of Clubs"));
        img.add(new ImageIcon("Cards/45.png", "6 of Clubs"));
        img.add(new ImageIcon("Cards/46.png", "7 of Clubs"));
        img.add(new ImageIcon("Cards/47.png", "8 of Clubs"));
        img.add(new ImageIcon("Cards/48.png", "9 of Clubs"));
        img.add(new ImageIcon("Cards/49.png", "10 of Clubs"));
        img.add(new ImageIcon("Cards/50.png", "J of Clubs"));
        img.add(new ImageIcon("Cards/51.png", "Q of Clubs"));
        img.add(new ImageIcon("Cards/52.png", "K of Clubs"));

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
