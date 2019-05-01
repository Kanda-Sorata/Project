package View.CharacterPanel;

import Controller.AccountPlayerController;
import View.SearchPanel.UtilitiesPanelMethode;

import javax.swing.*;
import Exception.*;

public class FormPanel extends JPanel {
    private JComboBox playerAccountCombo;
    private JComboBox gameCombo;
    private JComboBox serverCombo;
    private JComboBox characterClassCombo;

    private String [] pseudos;
    private String [] games;
    private String [] servers;
    private String [] characterClasses;

    private JTextField nameField;
    private JTextField healthPointField;
    private JCheckBox isStuffedCheckBoc;
    private JSpinner creationDate;
    private JTextField petNameField;
    private JTextField damagePerSecond;


    private UtilitiesPanelMethode utilitiesPanelMethode;
    private AccountPlayerController accountPlayerController;

    public FormPanel(){
        utilitiesPanelMethode = new UtilitiesPanelMethode();
        accountPlayerController = new AccountPlayerController();

        try {
            pseudos = utilitiesPanelMethode.setPseudos();
            playerAccountCombo = new JComboBox();
        }catch(NbAccountException nbAccountException){
            JOptionPane.showMessageDialog(null, nbAccountException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch(AllAccountException allAccountException){
            JOptionPane.showMessageDialog(null, allAccountException.getMessage(), "Errot", JOptionPane.ERROR_MESSAGE);
        }
    }



}
