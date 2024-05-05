package View;
import Model.Deck;
import Model.Player;
import javax.swing.*;

/**
 * Filename: MainFrame.java
 * Short description: Used for setup of the frame where the gamePanel and titlePanel will be displayed
 * IST 242 Assignment:GUI Programming Project
 *
 *
 * @author Chad Rousseau, Christopher Rusnak, Tyler Mascherino
 * @version 05/3/2024
 */
public class MainFrame extends JFrame {
    // Instance Variables
    private InitialPanel ip;
    private GamePanel gamePanel;

    public MainFrame() {
        // Title
        super("GoFish!");

        // Creates initial panel
        ip = new InitialPanel(this);
        add(ip);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }


    public void showGamePanel() {
        // Used for the display of gamepanel and the input from the player for "username"
        getContentPane().remove(ip);
        String username;
        username = ip.getTp().getUserNameField().getText();
        gamePanel = getGamePanel();
        JLabel userNameLabel = (JLabel) gamePanel.getComponent(0);
        userNameLabel.setText("Player: " + username);

        getContentPane().add(gamePanel);
        revalidate();
        repaint();
    }

    // Sets / Gets
    public InitialPanel getIp() {
        return ip;
    }

    public GamePanel getGamePanel() {
        if (gamePanel == null) {
            gamePanel = new GamePanel(new Player(), new Deck());
        }
        return gamePanel;
    }
}