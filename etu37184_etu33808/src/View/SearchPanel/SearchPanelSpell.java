package View.SearchPanel;

import Exception.DataAccessException;
import Exception.DataException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;


public class SearchPanelSpell extends JPanel {
    private JComboBox playerAccountCombo;
    private ArrayList<String> pseudos;
    private JLabel playerAccount;

    private String pseudoChoice;
    private int numberChoice;

    private UtilitiesPanelMethode utilitiesPanelMethode;
    private ResultSpellPanel resultSpellPanel;

    public SearchPanelSpell(ResultSpellPanel resultSpellPanel){
        try {
            utilitiesPanelMethode = new UtilitiesPanelMethode();
            pseudos = utilitiesPanelMethode.setPlayerAccountsPseudo();
            this.resultSpellPanel = resultSpellPanel;

            //Add properties
            setLayout(new GridLayout(1, 2, 5, 15));
            setBorder(new EmptyBorder(250, 0, 300, 250)); //top, left, bottom, right

            //Add components
            playerAccount = new JLabel("Player Account");
            playerAccount.setHorizontalAlignment(SwingConstants.RIGHT);

            playerAccountCombo = new JComboBox(pseudos.toArray());
            playerAccountCombo.addItemListener(new ComboBoxListener());

            add(playerAccount);
            add(playerAccountCombo);
        }catch (DataAccessException dataAccessException){
            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch(DataException dataException) {
            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getPseudoChoice() {
        return pseudoChoice;
    }

    public void setPseudoChoice(String pseudoChoice) {
        this.pseudoChoice = pseudoChoice;
    }

    public int getNumberChoice() {
        return numberChoice;
    }

    public void setNumberChoice(int numberChoice) {
        this.numberChoice = numberChoice;
    }

    private class ComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if(playerAccountCombo.getSelectedIndex() != 0){
                setPseudoChoice(pseudos.get(playerAccountCombo.getSelectedIndex()).split("#")[0]);
                setNumberChoice(Integer.parseInt(pseudos.get(playerAccountCombo.getSelectedIndex()).split("#")[1]));
                try {
                    resultSpellPanel.setJtable(pseudoChoice, numberChoice);
                }catch (DataAccessException dataAccessException){
                    JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }catch(DataException dataException) {
                    JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
