package View.CharacterPanel;

import Controller.GameController;
import Controller.ServerController;
import Exception.ConflictDataException;
import View.SearchPanel.UtilitiesPanelMethode;

import javax.swing.*;
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
    private ServerController serverController;

    public FormPanel(){
        utilitiesPanelMethode = new UtilitiesPanelMethode();
        gameController = new GameController();
        serverController = new ServerController();

        try {
            pseudos = utilitiesPanelMethode.setPlayerAccountsPseudo();
            playerAccountCombo = new JComboBox(pseudos.toArray());
        }catch(ConflictDataException conflictDataException){
            JOptionPane.showMessageDialog(null, conflictDataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setPseudoChoice(String pseudoChoice) {
        this.pseudoChoice = pseudoChoice;
    }

    public void setNumberChoice(String numberChoice) {
        this.numberChoice = numberChoice;
    }

    public void setGamesName(String pseudoChoice, String numberChoice) throws ConflictDataException {
        ArrayList<String> temp = gameController.getAllGamesName(pseudoChoice, numberChoice);
        temp.add("No selection");
        int size = temp.size();
        for(int iGame = 0; iGame < size; iGame++){ games.add(temp.get(iGame)); }
    }

    public void setServersName(String pseudoChoice, String numberChoice, String game) throws ConflictDataException {
        ArrayList<String> temp = serverController.getAllServersName(pseudoChoice, numberChoice, game);
        temp.add("No selection");
        int size = temp.size();
        for(int iServer = 0; iServer < size; iServer++){ servers.add(temp.get(iServer)); }
    }



}
