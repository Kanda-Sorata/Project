package View.CharacterPanel;

import Controller.CharacterController;
import Exception.DataAccessException;
import Exception.DataException;
import Model.DeleteCharacter;
import View.SearchPanel.AllCharactersModel;
import View.UtilitiesPanelMethod;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class DeletePanelTable extends JPanel {
    private ArrayList<DeleteCharacter> characters;
    private JTable table;
    private JScrollPane scrollPane;
    private CharacterController characterController;
    private UtilitiesPanelMethod utilitiesPanelMethod;
    private String pseudoChoice;
    private String gameChoice;
    private int numberChoice;
    private String characterChoice;
    private TableListener tableListener;

    public DeletePanelTable(){
        //Add properties
        setLayout(new FlowLayout());

        //Init
        tableListener = new TableListener();
        utilitiesPanelMethod = new UtilitiesPanelMethod();
        characterController = new CharacterController();

        //Add components
        table = utilitiesPanelMethod.getJTableModelBlank();
        scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public void setJTable(String pseudoChoice, int numberChoice, String gameChoice) throws DataException, DataAccessException {
        this.pseudoChoice = pseudoChoice;
        this.numberChoice = numberChoice;
        this.gameChoice = gameChoice;
        updateJTable();
    }

    public class TableListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            if(!listSelectionEvent.getValueIsAdjusting()){
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();
                characterChoice = table.getValueAt(row, column).toString();
                String input = JOptionPane.showInputDialog(null, "Do you really want to delete this character PERMANENTLY?\nInsert \"DELETE\" to continue.", "Warning - DeleteCharacter", JOptionPane.WARNING_MESSAGE);
                if (input == null || !input.equals("DELETE")) {
                    JOptionPane.showMessageDialog(null, "DeleteCharacter has been cancelled.", "DeleteCharacter - Cancelled", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    int state = 0;
                    try {
                        state = characterController.deleteACharacter(pseudoChoice, numberChoice, gameChoice, characterChoice);

                    } catch (DataException dataException) {
                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (DataAccessException dataAccessException) {
                        JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    if (state > 0) {
                        try {
                            updateJTable();
                        } catch (DataException dataException) {
                            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (DataAccessException dataAccessException) {
                            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        JOptionPane.showMessageDialog(null, "The character has been deleted successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }

    public void updateJTable() throws DataAccessException, DataException {
        characters = characterController.getAllCharactersInAGame(pseudoChoice, numberChoice, gameChoice);
        remove(scrollPane);
        AllCharactersModel model = new AllCharactersModel(characters);
        table = new JTable(model);
        table.getSelectionModel().addListSelectionListener(tableListener);
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        revalidate();
        repaint();
    }
}