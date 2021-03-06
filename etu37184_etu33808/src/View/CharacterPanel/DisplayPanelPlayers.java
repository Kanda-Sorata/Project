package View.CharacterPanel;

import Controller.PlayerAccountController;
import Exception.DataAccessException;
import Exception.DataException;
import View.UtilitiesPanelMethod;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DisplayPanelPlayers extends JPanel {
    private JComboBox playerAccountCombo;
    private JLabel playerAccountLabel;
    private ComboBoxListener comboBoxListener;
    private ArrayList<String> allPlayers;
    private PlayerAccountController playerAccountController;
    private DisplayPanelResult displayPanelResult;
    private UtilitiesPanelMethod utilitiesPanelMethod;
    private String pseudoChoice;
    private int numberChoice;

    public DisplayPanelPlayers(DisplayPanelResult displayPanelResult) throws DataAccessException, DataException {
        //Init
        this.displayPanelResult = displayPanelResult;
        utilitiesPanelMethod = new UtilitiesPanelMethod();

        //Add components
        playerAccountLabel = new JLabel("Player account");
        comboBoxListener = new ComboBoxListener();
        playerAccountController = new PlayerAccountController();
        allPlayers = utilitiesPanelMethod.setPlayerAccountsPseudo();
        playerAccountCombo = new JComboBox(allPlayers.toArray());
        playerAccountCombo.addActionListener(comboBoxListener);

        add(playerAccountLabel);
        add(playerAccountCombo);
    }

    private class ComboBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if (playerAccountCombo.getSelectedIndex() > 0) {
                    pseudoChoice = allPlayers.get(playerAccountCombo.getSelectedIndex()).split("#")[0];
                    numberChoice = Integer.parseInt(allPlayers.get(playerAccountCombo.getSelectedIndex()).split("#")[1]);

                    displayPanelResult.setJTable(pseudoChoice, numberChoice);
                } else {
                    displayPanelResult.updateJTableNoSelection();
                }
            } catch (DataException dataException) {
                JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (DataAccessException dataAccessException) {
                JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
