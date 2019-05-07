package View.CharacterPanel;

import Controller.CharacterController;
import Exception.DataAccessException;
import Exception.DataException;
import View.SearchPanel.AllCharactersModel;
import View.SearchPanel.UtilitiesPanelMethode;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class DeletePanelTable extends JPanel {
    private ArrayList<String> characters;
    private JTable table;
    private  JScrollPane scrollPane;
    private CharacterController characterController;
    private UtilitiesPanelMethode utilitiesPanelMethode;
    private String pseudoChoice;
    private String gameChoice;
    private int numberChoice;
    private String characterChoice;
    private TableListener tableListener;

    public DeletePanelTable(){
        setLayout(new FlowLayout());

        tableListener = new TableListener();

        utilitiesPanelMethode = new UtilitiesPanelMethode();
        setLayout(new FlowLayout());
        characterController = new CharacterController();

        table = utilitiesPanelMethode.getJTableModelBlank();
        scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public void setJTable(String pseudoChoice, int numberChoice, String gameChoice) throws DataException, DataAccessException {
        setPseudo(pseudoChoice);
        setNumber(numberChoice);
        setGame(gameChoice);
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

    public class TableListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            if(!listSelectionEvent.getValueIsAdjusting()){
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();
                setCharacterChoice(table.getValueAt(row, column).toString());
                String input = JOptionPane.showInputDialog(null, "Do you really want to delete this character PERMANENTLY?\nInsert \"DELETE\" to continue.", "Delete", JOptionPane.WARNING_MESSAGE);
                if (input == null || !input.equals("DELETE")) {
                    JOptionPane.showMessageDialog(null, "Delete has been cancelled.", "Delete - Cancelled", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    if(input.equals("DELETE")) {
                        int state = 0;
                        try {
                            state = characterController.deleteACharacter(pseudoChoice, numberChoice, gameChoice, characterChoice);
                        } catch (DataException dataException) {
                            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (DataAccessException dataAccessException) {
                            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        if(state > 0){
                            table.revalidate();
                            table.repaint();
                            JOptionPane.showMessageDialog(null, "The character has been deleted successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        }
    }

    public void setPseudo(String pseudoChoice){
        this.pseudoChoice = pseudoChoice;
    }

    public void setNumber(int numberChoice){
        this.numberChoice = numberChoice;
    }

    public void setGame(String gameChoice){
        this.gameChoice = gameChoice;
    }

    public void setCharacterChoice(String characterChoice) {
        this.characterChoice = characterChoice;
    }
}