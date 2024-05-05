package View;

/**
 * Filename: InitialPanel.java
 * Short description: Used for setup of the TitlePanel
 * IST 242 Assignment:GUI Programming Project
 *
 *
 * @author Chad Rousseau, Christopher Rusnak, Tyler Mascherino
 * @version 05/3/2024
 */

import javax.swing.*;
import java.awt.*;
public class InitialPanel extends JPanel {
    // Instance variables
    private TitlePanel tp;

    public InitialPanel(MainFrame mainFrame) {
        // Set Layout
        setLayout(new BorderLayout());

        // Create the panel
        tp = new TitlePanel(mainFrame);
        add(tp, BorderLayout.CENTER);
    }
    // Sets and Gets

    public TitlePanel getTp() {
        return tp;
    }

    public void setTp(TitlePanel tp) {
        this.tp = tp;
    }
}