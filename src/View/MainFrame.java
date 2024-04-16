package View;


import javax.swing.*;

public class MainFrame extends JFrame {
    private InitialPanel ip;
    private GamePanel gamePanel;

    public MainFrame(){
        super("GUI Tester for GoFish!");

        // Create initial panel
        ip = new InitialPanel(this);
        add(ip);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }


    public void showGamePanel() {

        getContentPane().remove(ip);


        gamePanel = new GamePanel();
        getContentPane().add(gamePanel);


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