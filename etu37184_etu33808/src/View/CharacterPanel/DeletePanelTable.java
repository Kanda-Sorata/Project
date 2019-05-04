package View.CharacterPanel;

import Controller.CharacterController;
import View.SearchPanel.AllCharactersModel;
import View.SearchPanel.UtilitiesPanelMethode;
import Exception.DataAccessException;
import Exception.DataException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DeletePanelTable extends JPanel {
    private ArrayList<String> characters;
    private JTable table;
    private  JScrollPane scrollPane;
    private String characterChoice;
    private CharacterController characterController;
    private JButton validationButton;
    private ButtonListener buttonListener;
    private UtilitiesPanelMethode utilitiesPanelMethode;
    private String pseudoChoice;
    private String gameChoice;
    private int numberChoice;
    private TableListener tableListener;

    public DeletePanelTable(){
        tableListener = new TableListener();
        validationButton = new JButton("Validation");
        buttonListener = new ButtonListener();
        validationButton.addActionListener(buttonListener);

        utilitiesPanelMethode = new UtilitiesPanelMethode();
        setLayout(new FlowLayout());
        characterController = new CharacterController();

        table = utilitiesPanelMethode.getJTableModelBlank();
        scrollPane = new JScrollPane(table);
        table.addMouseListener(tableListener);
        add(scrollPane);
        add(validationButton);
    }

    public void setJTable(String pseudoChoice, int numberChoice, String gameChoice) throws DataException, DataAccessException {
        setPseudo(pseudoChoice);
        setNumber(numberChoice);
        setGame(gameChoice);
        characters = characterController.getAllCharactersInAGame(pseudoChoice, numberChoice, gameChoice);
        remove(scrollPane);
        AllCharactersModel model = new AllCharactersModel(characters);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        revalidate();
        repaint();
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
     //       if() {
                try {
                    characterController.deleteACharacter(pseudoChoice, numberChoice, gameChoice, characterChoice);
                } catch (DataException dataException) {
                    JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (DataAccessException dataAccessException) {
                    JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
       //     }
        }
    }

    public class TableListener extends MouseAdapter {

        public void mouseClicked(MouseEvent event) {
            Point point = event.getPoint();
            int row = table.rowAtPoint(point);
            //todo listener
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
}