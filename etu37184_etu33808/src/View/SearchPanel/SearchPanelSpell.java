package View.SearchPanel;

import Exception.DataAccessException;
import Exception.DataException;
import View.UtilitiesPanelMethod;

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

    private UtilitiesPanelMethod utilitiesPanelMethod;
    private ResultSpellPanel resultSpellPanel;

    public SearchPanelSpell(ResultSpellPanel resultSpellPanel) throws DataAccessException, DataException {
        //Add properties
        setLayout(new GridLayout(1, 2, 5, 15));
        setBorder(new EmptyBorder(250, 0, 300, 250)); //top, left, bottom, right

        //Init
        utilitiesPanelMethod = new UtilitiesPanelMethod();
        this.resultSpellPanel = resultSpellPanel;

        pseudos = utilitiesPanelMethod.setPlayerAccountsPseudo();

        //Add components
        playerAccount = new JLabel("Player Account");
        playerAccount.setHorizontalAlignment(SwingConstants.RIGHT);

        playerAccountCombo = new JComboBox(pseudos.toArray());
        playerAccountCombo.addItemListener(new ComboBoxListener());

        add(playerAccount);
        add(playerAccountCombo);
    }

    public String getPseudoChoice() {
        return pseudoChoice;
    }


    public int getNumberChoice() {
        return numberChoice;
    }

    private class ComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if(playerAccountCombo.getSelectedIndex() != 0){
                pseudoChoice = pseudos.get(playerAccountCombo.getSelectedIndex()).split("#")[0];
                numberChoice = Integer.parseInt(pseudos.get(playerAccountCombo.getSelectedIndex()).split("#")[1]);
                try {
                    resultSpellPanel.setJTable(pseudoChoice, numberChoice);
                }catch (DataAccessException dataAccessException){
                    JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }catch(DataException dataException) {
                    JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
