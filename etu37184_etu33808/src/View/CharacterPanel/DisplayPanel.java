package View.CharacterPanel;

import Exception.DataAccessException;
import Exception.DataException;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private DisplayPanelPlayers displayPanelPlayers;
    private DisplayPanelResult displayPanelResult;

    public DisplayPanel() throws DataAccessException, DataException {
        //Add properties
        setLayout(new BorderLayout());

        //Add component
        displayPanelResult = new DisplayPanelResult();
        displayPanelPlayers = new DisplayPanelPlayers(displayPanelResult);

        add(displayPanelPlayers, BorderLayout.NORTH);
        add(displayPanelResult, BorderLayout.CENTER);
    }
}
