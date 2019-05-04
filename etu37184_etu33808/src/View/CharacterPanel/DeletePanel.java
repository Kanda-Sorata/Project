package View.CharacterPanel;

import Controller.GameController;
import Controller.CharacterController;
import Exception.DataAccessException;
import Exception.DataException;
import View.SearchPanel.UtilitiesPanelMethode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeletePanel extends JPanel {
    private DeletePanelCombo deletePanelCombo;
    private DeletePanelTable deletePanelTable;


    public DeletePanel(){

        //Add properties
        setLayout(new GridLayout(2, 2, 5, 15));

        //Add components
        deletePanelTable = new DeletePanelTable();
        deletePanelCombo = new DeletePanelCombo(deletePanelTable);

        add(deletePanelCombo);
        add(deletePanelTable);

    }

}