package Model;

public class LivePlayer extends Player{
    private String username;
    private int score;

    public LivePlayer(String username) {
        super();
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
