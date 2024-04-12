package Model;

public enum Suit {
     SPADES("Spades"), HEARTS("Hearts"),  DIAMONDS("Diamonds"), CLUBS("Clubs");

    private String suit;

    Suit(String suit){
        this.suit = suit;

    }
    @Override

    public String toString() {
        return suit;
    }
}
