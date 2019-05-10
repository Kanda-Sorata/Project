package View.CharacterPanel;

import Exception.DataAccessException;
import Exception.DataException;

import javax.swing.*;
import java.awt.*;

public class DeletePanel extends JPanel {
    private DeletePanelCombo deletePanelCombo;
    private DeletePanelTable deletePanelTable;

    public DeletePanel() throws DataAccessException, DataException {
        //Add properties
        setLayout(new GridLayout(1, 2, 5, 15));

        //Add components
        deletePanelTable = new DeletePanelTable();
        deletePanelCombo = new DeletePanelCombo(deletePanelTable);

        add(deletePanelCombo);
        add(deletePanelTable);
    }

}