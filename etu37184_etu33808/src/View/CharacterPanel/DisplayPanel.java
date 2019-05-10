package View.CharacterPanel;

import View.Frame;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private DisplayPanelPlayers displayPanelPlayers;
    private DisplayPanelResult displayPanelResult;
    private Frame frame;

    public DisplayPanel(Frame frame) {
        setLayout(new BorderLayout());

        this.frame = frame;
        frame.setTitle("");
        frame.setTitle(frame.getTitle() + "- Display all characters");

        displayPanelResult = new DisplayPanelResult();
        displayPanelPlayers = new DisplayPanelPlayers(displayPanelResult);

        add(displayPanelPlayers, BorderLayout.NORTH);
        add(displayPanelResult, BorderLayout.CENTER);
    }
}
