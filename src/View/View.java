package View;

public class View {
    private MainFrame mf;
    private TitlePanel tp;
    private GamePanel gp;

    // Constructor
    public View(){
        mf = new MainFrame();
        tp = mf.getIp().getTp();
        gp = mf.getGamePanel(); // Accessing the game panel from the main frame

    }

    public MainFrame getMf() {
        return mf;
    }

    public TitlePanel getTp() {
        return tp;
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setTp(TitlePanel tp) {
        this.tp = tp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public void setMf(MainFrame mf) {
        this.mf = mf;
    }
}
