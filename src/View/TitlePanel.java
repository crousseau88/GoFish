package View;

import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitlePanel extends JPanel {
    private MainFrame mainFrame;
    private JButton twoPairButton, fourPairButton, rulesButton;

    public TitlePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);
        setBackground(new Color(0, 100, 0));

        twoPairButton = new JButton("2 Card");
        fourPairButton = new JButton("4 Card");//may implement later on
        rulesButton = new JButton("Rules");

        twoPairButton.setBounds(new Rectangle(440, 260, 100, 30));
        fourPairButton.setBounds(new Rectangle(200, 260, 100, 30));
        rulesButton.setBounds(new Rectangle(320, 260, 100, 30));

        add(twoPairButton);
        add(fourPairButton);
        add(rulesButton);

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

    public JButton getTwoPairButton() {
        return twoPairButton;
    }

    public JButton getFourPairButton() {
        return fourPairButton;
    }

    public JButton getRulesButton() {
        return rulesButton;
    }
}
