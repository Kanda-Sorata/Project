package View.CharacterPanel;

import Controller.AccountPlayerController;
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
    private AccountPlayerController playerAccountController;
    private DisplayPanelResult displayPanelResult;
    private UtilitiesPanelMethod utilitiesPanelMethod;
    private String pseudoChoice;
    private int numberChoice;

    public DisplayPanelPlayers(DisplayPanelResult displayPanelResult) {
        //Init
        this.displayPanelResult = displayPanelResult;
        utilitiesPanelMethod = new UtilitiesPanelMethod();

        try {
            playerAccountLabel = new JLabel("Player account");
            comboBoxListener = new ComboBoxListener();
            playerAccountController = new AccountPlayerController();
            allPlayers = utilitiesPanelMethod.setPlayerAccountsPseudo();
            playerAccountCombo = new JComboBox(allPlayers.toArray());
            playerAccountCombo.addActionListener(comboBoxListener);

            add(playerAccountLabel);
            add(playerAccountCombo);
        } catch (DataAccessException dataAccessException) {
            utilitiesPanelMethod.removeAllFromResultPanel(this.displayPanelResult);
            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DataException dataException) {
            utilitiesPanelMethod.removeAllFromResultPanel(this.displayPanelResult);
            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class ComboBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if (playerAccountCombo.getSelectedIndex() > 0) {
                setPseudoChoice(allPlayers.get(playerAccountCombo.getSelectedIndex()).split("#")[0]);
                setNumberChoice(Integer.parseInt(allPlayers.get(playerAccountCombo.getSelectedIndex()).split("#")[1]));
                try {
                    displayPanelResult.setJTable(pseudoChoice, numberChoice);
                } catch (DataException dataException) {
                    JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (DataAccessException dataAccessException) {
                    JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                displayPanelResult.updateJTableNoSelection();
            }
        }
    }

    public void setPseudoChoice(String pseudoChoice) {
        this.pseudoChoice = pseudoChoice;
    }

    public void setNumberChoice(int numberChoice) {
        this.numberChoice = numberChoice;
    }
}
