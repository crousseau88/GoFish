package Model;

public class ComputerPlayer extends Player {
    private Hand hand;

    @Override
    public Card askForCard() {
        return null;
    }

    //constructor

    public ComputerPlayer(Hand hand) {
        this.hand = hand;
    }


    //set and gets

    @Override
    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }


    //toString


}
