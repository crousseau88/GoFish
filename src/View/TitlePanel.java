package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TitlePanel extends JPanel implements ActionListener {
    private MainFrame mainFrame;

    public TitlePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);
        setBackground(new Color(0, 100, 0));

        JButton twoPair = new JButton("2 Pair");
        JButton fourPair = new JButton("4 Pair");
        JButton rules = new JButton("Rules");

        twoPair.setBounds(new Rectangle(440, 260, 100, 30));
        fourPair.setBounds(new Rectangle(200, 260, 100, 30));
        rules.setBounds(new Rectangle(320, 260, 100, 30));

        twoPair.addActionListener(this);
        fourPair.addActionListener(this);
        rules.addActionListener(this);

        add(twoPair);
        add(fourPair);
        add(rules);

        JLabel title = new JLabel("Welcome to GoFish!");
        title.setFont(new Font("Arial", Font.BOLD, 38));
        title.setForeground(Color.WHITE);
        title.setBounds(new Rectangle(200, 30, 400, 30));
        add(title);

        ImageIcon icon = new ImageIcon("GoFishIMAGE.jpg");
        JLabel logo = new JLabel(icon);
        logo.setBounds(new Rectangle(220, 70, 300, 200));
        add(logo);

        JLabel userAsk = new JLabel("Enter a Name");
        userAsk.setFont(new Font("Monotype Corsiva", Font.BOLD + Font.ITALIC, 20));
        userAsk.setForeground(Color.WHITE);
        userAsk.setBounds(330, 380, 200, 30);
        add(userAsk);

        JTextField userName = new JTextField(10);
        userName.setBounds(new Rectangle(280, 410, 200, 30));
        userName.setForeground(Color.GRAY);
        add(userName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            if (button.getText().equals("2 Pair") || button.getText().equals("4 Pair")) {
                // Switch to GamePanel
                mainFrame.showGamePanel();
            } else if (button.getText().equals("Rules")) {
                displayRules();
            }
        }
    }

    private void displayRules() {
        JFrame rulesFrame = new JFrame("Go Fish Rules");
        rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rulesFrame.setSize(400, 300);
        rulesFrame.setLocationRelativeTo(null);

        JTextArea rulesTextArea = new JTextArea();
        rulesTextArea.setEditable(false);
        rulesTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        rulesTextArea.setLineWrap(true);
        rulesTextArea.setWrapStyleWord(true);

        String rulesText = "Go Fish Rules:\n" +
                "1. The game is played with a standard 52-card deck.\n" +
                "2. Each player is dealt a hand of 7 cards.\n" +
                "3. Players take turns asking opponents for a specific rank of card.\n" +
                "4. If the opponent has the requested card(s), they must give it to the player.\n" +
                "5. If the opponent does not have the requested card(s), the player must 'Go Fish' and draw a card from the deck.\n" +
                "6. If the drawn card matches the requested rank, the player continues their turn.\n" +
                "7. If the drawn card does not match the requested rank, the turn passes to the next player.\n" +
                "8. The game continues until all 13 sets of 4 cards have been collected.\n" +
                "9. The player with the most sets of 4 cards at the end of the game wins.";
        rulesTextArea.setText(rulesText);
        JScrollPane scrollPane = new JScrollPane(rulesTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rulesFrame.add(scrollPane);
        rulesFrame.setVisible(true);
    }
}