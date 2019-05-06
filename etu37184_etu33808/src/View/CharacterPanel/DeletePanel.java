package View.CharacterPanel;

import javax.swing.*;
import java.awt.*;

public class DeletePanel extends JPanel {
    private DeletePanelCombo deletePanelCombo;
    private DeletePanelTable deletePanelTable;


    public DeletePanel(Frame frame){

        //Add properties
        setLayout(new GridLayout(1, 2, 5, 15));

        frame.setTitle("");
        frame.setTitle(frame.getTitle() + "- Delete a character");


        //Add components
        deletePanelTable = new DeletePanelTable();
        deletePanelCombo = new DeletePanelCombo(deletePanelTable);

        add(deletePanelCombo);
        add(deletePanelTable);

    }

}