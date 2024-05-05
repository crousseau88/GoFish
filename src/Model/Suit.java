package Model;
/**
 * Filename: Suit.java
 * Short description:Suit enum for program
 * IST 242 Assignment:GUI Programming Project
 * @author Chad Rousseau, Christopher Rusnak, Tyler Mascherino
 * @version 05/3/2024
 */
public enum Suit {
     SPADES("Spades"), HEARTS("Hearts"),  DIAMONDS("Diamonds"), CLUBS("Clubs");

    private String suit;

    //Constructor
    Suit(String suit){
        this.suit = suit;

    }
    @Override
    //toString method
    public String toString() {
        return suit;
    }
}
