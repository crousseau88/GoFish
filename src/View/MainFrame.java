package View;

import Model.LivePlayer;

import javax.swing.*;

public class MainFrame extends JFrame {
    private InitialPanel ip;
    private GamePanel gamePanel; // Added GamePanel reference

    public MainFrame(){
        super("GUI Tester for GoFish!");

        // Create initial panel
        ip = new InitialPanel(this);
        add(ip);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    // Method to show the GamePanel
    public void showGamePanel() {
        // Remove the current panel
        getContentPane().remove(ip);

        // Create and add the GamePanel
        gamePanel = new GamePanel(new LivePlayer()); // You might need to pass any necessary parameters here
        getContentPane().add(gamePanel);

        // Repaint and validate the frame
        revalidate();
        repaint();
    }

    public InitialPanel getIp() {
        return ip;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}