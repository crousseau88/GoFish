/**
 * Filename: TitlePanel.java
 * Short description: Title panel of thr game, contains buttons and user input fields
 * IST 242 Assignment: GUI Programming Project
 * Authors: Tyler Mascherino, Chris Rusnak, Chad Rousseau
 * Version: 5/3/2024
 */
package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
public class TitlePanel extends JPanel {
    // Components
    private MainFrame mainFrame;
    private JButton twoPairButton, fourPairButton, rulesButton;
    private JTextField userNameField;
    private JLabel title, logo, userAsk;
    /**
     * Constructs a TitlePanel object
     * @param mainFrame The main frame of the application
     */
    public TitlePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);
        setBackground(new Color(0, 100, 0));
        // Buttons
        twoPairButton = new JButton("2 Card");
        fourPairButton = new JButton("4 Card"); // may implement later on
        rulesButton = new JButton("Rules");
        // Set button bounds
        twoPairButton.setBounds(new Rectangle(440, 260, 100, 30));
        fourPairButton.setBounds(new Rectangle(200, 260, 100, 30));
        rulesButton.setBounds(new Rectangle(320, 260, 100, 30));
        // Add buttons to panel
        add(twoPairButton);
        add(fourPairButton);
        add(rulesButton);
        //Title label
        title = new JLabel("Welcome to GoFish!");
        title.setFont(new Font("Arial", Font.BOLD, 38));
        title.setForeground(Color.WHITE);
        add(title);
        // Logo
        ImageIcon icon = new ImageIcon("GoFishIMAGE.jpg");
        logo = new JLabel(icon);
        add(logo);
        // USer Ask Label
        userAsk = new JLabel("Enter a Name");
        userAsk.setFont(new Font("", Font.PLAIN + Font.PLAIN, 20));
        userAsk.setForeground(Color.WHITE);
        add(userAsk);
        //Username text field
        userNameField = new JTextField(10);
        userNameField.setForeground(Color.GRAY);
        add(userNameField);
        // Add a component listener to adjust component bounds on resize
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustComponentBounds(getWidth(), getHeight());
            }
        });
    }
    public void adjustComponentBounds(int width, int height) {
        // Adjust component bounds based on the width and height of the panel
        twoPairButton.setBounds((width - 100) / 2, 260, 100, 30);
        fourPairButton.setBounds((width - 100) / 4, 260, 100, 30);
        rulesButton.setBounds((width - 100) / 4 * 3, 260, 100, 30);
        title.setBounds((width - 400) / 2, 30, 400, 30);
        logo.setBounds((width - 300) / 2, 70, 300, 200);
        userAsk.setBounds((width - 200) / 2, 380, 200, 30);
        userNameField.setBounds((width - 200) / 2, 410, 200, 30);
        int userAskWidth = userAsk.getPreferredSize().width;
        int userAskX = (width - userAskWidth) / 2;
        userAsk.setBounds(userAskX, 380, userAskWidth, 30);
    }
    // Get methods for text field and buttons
    public JTextField getUserNameField() {
        return userNameField;
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