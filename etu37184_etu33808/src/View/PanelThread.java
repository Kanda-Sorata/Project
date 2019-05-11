package View;

import javax.swing.*;
import java.awt.*;

public class PanelThread extends JPanel {
    private ThreadCounts thread;

    public PanelThread(){
        setLayout(new GridLayout(3, 1, 5, 15));
        thread = new ThreadCounts(this);
        thread.start();
        repaint();
    }
}
