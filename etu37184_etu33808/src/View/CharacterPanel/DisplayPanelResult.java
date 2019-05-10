package View.CharacterPanel;

import Controller.CharacterController;
import Exception.DataAccessException;
import Exception.DataException;
import Model.DisplayCharacter;
import View.UtilitiesPanelMethode;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

public class DisplayPanelResult extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private UtilitiesPanelMethode utilitiesPanelMethode;
    private ArrayList<DisplayCharacter> characters;
    private CharacterController characterController;

    public DisplayPanelResult() {
        setLayout(new FlowLayout());
        characterController = new CharacterController();
        utilitiesPanelMethode = new UtilitiesPanelMethode();
        table = utilitiesPanelMethode.getJTableModelBlank();
        scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public void setJTable(String pseudoChoice, int numberChoice) throws DataAccessException, DataException {
        characters = characterController.getAllInfosCharacters(pseudoChoice, numberChoice);
        DisplayAllCharactersInfosModel model = new DisplayAllCharactersInfosModel(characters);
        remove(scrollPane);
        table = new JTable(model);
        TableColumnModel modelColumn = table.getColumnModel();
        int nbColumn = model.getColumnCount();
        for (int iColumn = 0; iColumn < nbColumn; iColumn++) {
            modelColumn.getColumn(iColumn).setMaxWidth(this.getWidth() / 5);
            modelColumn.getColumn(iColumn).setMinWidth(this.getWidth() / 10);
        }
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        add(scrollPane);
        revalidate();
        repaint();
    }

    public void updateJTableNoSelection(){
        remove(scrollPane);
        table = utilitiesPanelMethode.getJTableModelBlank();
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        revalidate();
        repaint();
    }
}
