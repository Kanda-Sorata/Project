package View.CharacterPanel;

import Controller.GameController;
import Exception.DataAccessException;
import Exception.DataException;
import View.SearchPanel.UtilitiesPanelMethode;

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
    private UtilitiesPanelMethode utilitiesPanelMethode;
    private String pseudoChoice;
    private int numberChoice;
    private String gameChoice;
    private DeletePanelTable deletePanelTable;


    public DeletePanelCombo(DeletePanelTable deletePanelTable){
        //Add properties
        setLayout(new GridLayout(2, 2, 5, 15));
        setBorder(new EmptyBorder(225, 100, 275, 150)); //Top, left, bottom, right

        //Add components
        try{
            this.deletePanelTable = deletePanelTable;
            utilitiesPanelMethode = new UtilitiesPanelMethode();
            comboBoxListener = new ComboBoxListener();
            gameController = new GameController();

            playerAccountLabel = new JLabel("Player account");
            playerAccountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            playerAccounts = new ArrayList<>();
            playerAccounts = utilitiesPanelMethode.setPlayerAccountsPseudo();
            playerAccountCombo = new JComboBox(playerAccounts.toArray());
            playerAccountCombo.addItemListener(comboBoxListener);

            gameLabel = new JLabel("Game name");
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

        } catch (DataException dataException) {
            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DataAccessException dataAccessException) {
            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class ComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if(itemEvent.getSource() == playerAccountCombo){
                if (playerAccountCombo.getSelectedIndex() == 0) {
                    gameCombo.setSelectedIndex(0);
                    gameCombo.setEnabled(false);
                } else {
                    setPseudoChoice(playerAccounts.get(playerAccountCombo.getSelectedIndex()).split("#")[0]);
                    setNumberChoice(Integer.parseInt(playerAccounts.get(playerAccountCombo.getSelectedIndex()).split("#")[1]));
                    try {
                        gamesTemp = gameController.getAllGamesName(pseudoChoice, numberChoice);
                        games = new ArrayList();
                        games.add("No selection");
                        int gameSize = gamesTemp.size();
                        for(int iGame = 0; iGame < gameSize; iGame++){ games.add(gamesTemp.get(iGame)); }
                        gameCombo.setModel(new DefaultComboBoxModel(games.toArray()));
                        gameCombo.revalidate();
                        gameCombo.repaint();
                    } catch (DataException dataException) {
                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (DataAccessException dataAccessException) {
                        JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    gameCombo.setEnabled(true);
                }
            } else if(itemEvent.getSource() == gameCombo){
                if(gameCombo.getSelectedIndex() != 0){
                    setGameChoice(games.get(gameCombo.getSelectedIndex()));
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



    public void setPseudoChoice(String pseudoChoice) {
        this.pseudoChoice = pseudoChoice;
    }

    public void setNumberChoice(int numberChoice) {
        this.numberChoice = numberChoice;
    }

    public void setGameChoice(String gameChoice){
        this.gameChoice = gameChoice;
    }

}
