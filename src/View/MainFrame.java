package View;


import Model.Deck;
import Model.Player;

import javax.swing.*;
//TODO add comments / javadoc to code

public class MainFrame extends JFrame {
    private InitialPanel ip;
    private GamePanel gamePanel;

    public MainFrame() {
        super("GoFish!");

        // Create initial panel
        ip = new InitialPanel(this);
        add(ip);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }


    public void showGamePanel() {
        getContentPane().remove(ip);
        String username = ip.getTp().getUserNameField().getText();
        gamePanel = getGamePanel();
        JLabel userNameLabel = (JLabel) gamePanel.getComponent(0);
        userNameLabel.setText("Player: " + username);

        getContentPane().add(gamePanel);
        revalidate();
        repaint();
    }

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