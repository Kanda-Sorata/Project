package View;

import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel {
    private Frame frame;

    public HelpPanel(Frame frame){
        this.frame = frame;
        setLayout(new FlowLayout());

        frame.setTitle("");
        frame.setTitle(frame.getTitle() + "- Help");
    }
}
