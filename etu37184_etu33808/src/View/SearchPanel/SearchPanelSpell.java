package View.SearchPanel;

import Controller.AccountPlayerController;
//todo changer les tableaux et mettre arraylist.toArray()
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Exception.*;


public class SearchPanelSpell extends JPanel {
    private JComboBox playerAccountCombo;
    private String[] pseudos;
    private JLabel playerAccount;

    private String pseudoChoice;
    private String numberChoice;

    private UtilitiesPanelMethode utilitiesPanelMethode;
    private SpellPanel spellPanel;

    public SearchPanelSpell(SpellPanel spellPanel) throws AllAccountException, NbAccountException{
        utilitiesPanelMethode = new UtilitiesPanelMethode();
        this.spellPanel = spellPanel;

        //Add properties
        setLayout(new GridLayout(1, 2, 5, 15));
        setBorder(new EmptyBorder(150, 0, 250, 250)); //top, left, bottom, right

        //Add components
        playerAccount = new JLabel("Player Account");
        playerAccount.setHorizontalAlignment(SwingConstants.RIGHT);

       // pseudos = utilitiesPanelMethode.setPseudos();
        playerAccountCombo = new JComboBox(pseudos);
        playerAccountCombo.addActionListener(new ComboBocListener());

        add(playerAccount);
        add(playerAccountCombo);
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
            if(!pseudos[playerAccountCombo.getSelectedIndex()].equals(pseudos[0])){
                setPseudoChoice(pseudos[playerAccountCombo.getSelectedIndex()].split("#")[0]);
                setNumberChoice(pseudos[playerAccountCombo.getSelectedIndex()].split("#")[1]);
                try {
                    spellPanel.setJtable();
                }catch(AllSpellsException allSpellsException) {
                    JOptionPane.showMessageDialog(null, allSpellsException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
