package View.CharacterPanel;

import Controller.CharacterController;
import Exception.DataAccessException;
import Exception.DataException;
import Model.DisplayCharacter;
import View.UtilitiesPanelMethod;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayPanelResult extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private UtilitiesPanelMethod utilitiesPanelMethod;
    private ArrayList<DisplayCharacter> characters;
    private CharacterController characterController;

    public DisplayPanelResult() throws DataAccessException, DataException {
        //Init
        characterController = new CharacterController();
        utilitiesPanelMethod = new UtilitiesPanelMethod();
        updateJTableNoSelection();
    }

    public void setJTable(String pseudoChoice, int numberChoice) throws DataAccessException, DataException {
        characters = characterController.getAllInfosCharacters(pseudoChoice, numberChoice);
        DisplayAllCharactersInfosFromAllPlayersModel model = new DisplayAllCharactersInfosFromAllPlayersModel(characters, true);
        setScrollPane(model);
    }

    public void updateJTableNoSelection() throws DataException, DataAccessException {
        characters = characterController.getAllInfosCharactersFromAllPlayers();
        DisplayAllCharactersInfosFromAllPlayersModel model = new DisplayAllCharactersInfosFromAllPlayersModel(characters, false);
        setScrollPane(model);
    }

    private void setScrollPane(DisplayAllCharactersInfosFromAllPlayersModel model) {
        if (scrollPane != null) {
            remove(scrollPane);
        }
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 400));
        add(scrollPane);
        revalidate();
        repaint();
    }
}
