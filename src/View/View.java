/**
 * Filename: View.java
 * Short description: Manages the user interface of the game
 * IST 242 Assignment: Semester Programming Project - GoFish
 * Authors: Tyler Mascherino, Chris Rusnak, Chad Rousseau
 * Version: 5/3/2024
 */
package View;
public class View {
    // Components
    private MainFrame mf;
    private TitlePanel tp;
    private GamePanel gp;

    // Constructor
    public View() {
        mf = new MainFrame();
        tp = mf.getIp().getTp();
        gp = mf.getGamePanel();

    }
    // Setsup game panel
    public void setupGamePanel() {
        gp = mf.getGamePanel();

    }
    // Retrieves the main frame
    public MainFrame getMf() {
        return mf;
    }
    // Retrieves the title panel
    public TitlePanel getTp() {
        return tp;
    }
    // Retrieves the game panel
    public GamePanel getGp() {
        return gp;
    }
    // Sets the title panel
    public void setTp(TitlePanel tp) {
        this.tp = tp;
    }
    // Sets the game panel
    public void setGp(GamePanel gp) {
        this.gp = gp;
    }
    // Sets the main frame
    public void setMf(MainFrame mf) {
        this.mf = mf;
    }
}
