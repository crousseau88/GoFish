package Model;
/**
 * Filename: Rank.java
 * Short description:Rank enum for program
 * IST 242 Assignment:GUI Programming Project
 * @author Chad Rousseau, Christopher Rusnak, Tyler Mascherino
 * @version 05/3/2024
 */
public enum Rank {
    ACE(1),TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9),
    TEN(10), JACK(10), QUEEN(10), KING(10);

    private int value;

    //constructor
    private Rank(int value) {
        this.value = value;
    }
    //getter for value
    public int getValue() {
        return value;
    }
}
