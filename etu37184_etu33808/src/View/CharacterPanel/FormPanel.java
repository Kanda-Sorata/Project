package View.CharacterPanel;

import Controller.GameController;
import View.SearchPanel.UtilitiesPanelMethode;

import javax.swing.*;
import Exception.*;

import java.util.ArrayList;

public class FormPanel extends JPanel {
    private JComboBox playerAccountCombo;
    private JComboBox gameCombo;
    private JComboBox serverCombo;
    private JComboBox characterClassCombo;

    private ArrayList<String> pseudos;
    private ArrayList<String> games;
    private ArrayList<String> servers;
    private ArrayList<String> characterClasses;

    private JTextField nameField;
    private JTextField healthPointField;
    private JCheckBox isStuffedCheckBoc;
    private JSpinner creationDate;
    private JTextField petNameField;
    private JTextField damagePerSecond;


    private String pseudoChoice;
    private String numberChoice;

    private UtilitiesPanelMethode utilitiesPanelMethode;
    private GameController gameController;

    public FormPanel(){
        utilitiesPanelMethode = new UtilitiesPanelMethode();
        gameController = new GameController();

        try {
            pseudos = utilitiesPanelMethode.setPlayerAccountsPseudo();
            playerAccountCombo = new JComboBox(pseudos.toArray());



        }catch(NbAccountException nbAccountException){
            JOptionPane.showMessageDialog(null, nbAccountException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch(AllAccountException allAccountException){
            JOptionPane.showMessageDialog(null, allAccountException.getMessage(), "Errot", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setPseudoChoice(String pseudoChoice) {
        this.pseudoChoice = pseudoChoice;
    }

    public void setNumberChoice(String numberChoice) {
        this.numberChoice = numberChoice;
    }

    public void setGamesName(String pseudoChoice, String numberChoice) throws AllGamesException {
        ArrayList<String> temp = gameController.getAllGamesName(pseudoChoice, numberChoice);
        temp.add("No selection");
        int size = temp.size();
        for(int iGame = 0; iGame < size; iGame++){ games.add(temp.get(iGame)); }
    }

   /* public void setServersName(String pseudoChoice, String numberChoice, String Game) throws AllServerException {

    }*/



}
