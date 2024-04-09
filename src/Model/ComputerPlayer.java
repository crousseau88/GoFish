package Model;

import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player {
    private String username = "Computer";

    //Constructor
    public ComputerPlayer() {
        super();
    }

    // Asks other player for random rank from the current hand
    public Rank chooseRankToAskFor() {
        ArrayList<Card> cards = getHand().getCards();
        if (!cards.isEmpty()) {
            int index = new Random().nextInt(cards.size());
            return cards.get(index).getRank();
        }
        return null;
    }
}
