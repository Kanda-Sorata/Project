package View.CharacterPanel;

import Controller.GameController;
import Exception.DataAccessException;
import Exception.DataException;
import View.UtilitiesPanelMethod;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class DeletePanelCombo extends JPanel {
    private ArrayList<String> playerAccounts;
    private ArrayList<String> games;
    private ArrayList<String> gamesTemp;
    private JComboBox playerAccountCombo;
    private JComboBox gameCombo;
    private ComboBoxListener comboBoxListener;
    private JLabel playerAccountLabel;
    private JLabel gameLabel;
    private GameController gameController;
    private UtilitiesPanelMethod utilitiesPanelMethod;
    private String pseudoChoice;
    private int numberChoice;
    private String gameChoice;
    private DeletePanelTable deletePanelTable;


    public DeletePanelCombo(DeletePanelTable deletePanelTable) throws DataAccessException, DataException {
        //Add properties
        setLayout(new GridLayout(2, 2, 5, 15));
        setBorder(new EmptyBorder(225, 100, 275, 150)); //Top, left, bottom, right

        this.deletePanelTable = deletePanelTable;
        utilitiesPanelMethod = new UtilitiesPanelMethod();
        gameController = new GameController();

        playerAccounts = utilitiesPanelMethod.setPlayerAccountsPseudo();

        //Listener
        comboBoxListener = new ComboBoxListener();

        //Add components
        playerAccountLabel = new JLabel("Player account");
        playerAccountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        playerAccountCombo = new JComboBox(playerAccounts.toArray());
        playerAccountCombo.addItemListener(comboBoxListener);

        gameLabel = new JLabel("Game");
        gameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        games = new ArrayList<>();
        games.add("No selection");
        gameCombo = new JComboBox(games.toArray());
        gameCombo.addItemListener(comboBoxListener);
        gameCombo.setEnabled(false);

        add(playerAccountLabel);
        add(playerAccountCombo);
        add(gameLabel);
        add(gameCombo);
    }

    private class ComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if(itemEvent.getSource() == playerAccountCombo){
                if (playerAccountCombo.getSelectedIndex() == 0) {
                    gameCombo.setSelectedIndex(0);
                    gameCombo.setEnabled(false);
                } else {
                    pseudoChoice = playerAccounts.get(playerAccountCombo.getSelectedIndex()).split("#")[0];
                    numberChoice = Integer.parseInt(playerAccounts.get(playerAccountCombo.getSelectedIndex()).split("#")[1]);
                    try {
                        gamesTemp = gameController.getAllGamesName(pseudoChoice, numberChoice);
                        games = new ArrayList<>();
                        games.add("No selection");
                        games.addAll(gamesTemp);
                        gameCombo.setModel(new DefaultComboBoxModel(games.toArray()));
                    } catch (DataException dataException) {
                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (DataAccessException dataAccessException) {
                        JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    gameCombo.setEnabled(true);
                }
            } else if(itemEvent.getSource() == gameCombo){
                if(gameCombo.getSelectedIndex() != 0){
                    gameChoice = games.get(gameCombo.getSelectedIndex());
                    try {
                        deletePanelTable.setJTable(pseudoChoice, numberChoice, gameChoice);
                    } catch (DataException dataException) {
                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (DataAccessException dataAccessException) {
                        JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        }
    }

}
