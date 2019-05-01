package View.SearchPanel;

//todo changer les tableaux et mettre arraylist.toArray()

import Exception.DataAccessException;
import Exception.DataException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class SearchPanelSpell extends JPanel {
    private JComboBox playerAccountCombo;
    private ArrayList<String> pseudos;
    private JLabel playerAccount;

    private String pseudoChoice;
    private String numberChoice;

    private UtilitiesPanelMethode utilitiesPanelMethode;
    private SpellPanel spellPanel;

    public SearchPanelSpell(SpellPanel spellPanel){
        try {
            utilitiesPanelMethode = new UtilitiesPanelMethode();
            pseudos = utilitiesPanelMethode.setPlayerAccountsPseudo();
            this.spellPanel = spellPanel;

            //Add properties
            setLayout(new GridLayout(1, 2, 5, 15));
            setBorder(new EmptyBorder(150, 0, 250, 250)); //top, left, bottom, right

            //Add components
            playerAccount = new JLabel("Player Account");
            playerAccount.setHorizontalAlignment(SwingConstants.RIGHT);

            playerAccountCombo = new JComboBox(pseudos.toArray());
            playerAccountCombo.addActionListener(new ComboBocListener());

            add(playerAccount);
            add(playerAccountCombo);
        }catch(DataException dataException){
            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch(DataAccessException dataAccessException){
            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getPseudoChoice() {
        return pseudoChoice;
    }

    public void setPseudoChoice(String pseudoChoice) {
        this.pseudoChoice = pseudoChoice;
    }

    public String getNumberChoice() {
        return numberChoice;
    }

    public void setNumberChoice(String numberChoice) {
        this.numberChoice = numberChoice;
    }

    private class ComboBocListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(!pseudos.get(playerAccountCombo.getSelectedIndex()).equals(pseudos.get(0))){
                setPseudoChoice(pseudos.get(playerAccountCombo.getSelectedIndex()).split("#")[0]);
                setNumberChoice(pseudos.get(playerAccountCombo.getSelectedIndex()).split("#")[1]);
                try {
                    spellPanel.setJtable(pseudoChoice, numberChoice);
                }catch(DataException dataException) {
                    JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }catch (DataAccessException dataAccessException){
                    JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
