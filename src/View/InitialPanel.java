package View;

import javax.swing.*;
import java.awt.*;

public class InitialPanel extends JPanel {
    private TitlePanel tp;

    public InitialPanel(MainFrame mainFrame) {
        setLayout(new BorderLayout());

        // Create the panel
        tp = new TitlePanel(mainFrame);
        add(tp, BorderLayout.CENTER);
    }

    public TitlePanel getTp() {
        return tp;
    }

    public void setTp(TitlePanel tp) {
        this.tp = tp;
    }
}