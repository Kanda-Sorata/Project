package View.CharacterPanel;

import Controller.CharacterController;
import Controller.GameController;
import Controller.ServerController;
import Exception.DataAccessException;
import Exception.DataException;
import View.SearchPanel.UtilitiesPanelMethode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JLabel playerAccountLabel;


    private ComboBoxListener comboBoxListener;



    private String pseudoChoice;
    private String numberChoice;
    private String gameChoice;

    private UtilitiesPanelMethode utilitiesPanelMethode;
    private GameController gameController;
    private ServerController serverController;
    private CharacterController characterController;

    public FormPanel(){
        utilitiesPanelMethode = new UtilitiesPanelMethode();
        gameController = new GameController();
        serverController = new ServerController();
        characterController = new CharacterController();

        //Add properties
        setLayout(new GridLayout(2,2, 5, 15));

        //Add component
        try{
            comboBoxListener = new ComboBoxListener();

            playerAccountLabel = new JLabel("Plauyer account");
            pseudos = utilitiesPanelMethode.setPlayerAccountsPseudo();
            playerAccountCombo = new JComboBox(pseudos.toArray());

            playerAccountCombo.addActionListener(comboBoxListener);


            games.add("No selection");
            gameCombo = new JComboBox();
            gameCombo.addActionListener(comboBoxListener);
            gameCombo.setEnabled(false);


            add(playerAccountLabel);
            add(playerAccountCombo);

        }catch(DataException dataException){
            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch(DataAccessException dataAccessException){
            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setPseudoChoice(String pseudoChoice) {
        this.pseudoChoice = pseudoChoice;
    }

    public void setNumberChoice(String numberChoice) {
        this.numberChoice = numberChoice;
    }

    public void setGamesName(String pseudoChoice, String numberChoice) throws DataException, DataAccessException {
        ArrayList<String> temp = gameController.getAllGamesName(pseudoChoice, numberChoice);
        temp.add("No selection");
        int size = temp.size();
        for(int iGame = 0; iGame < size; iGame++){ games.add(temp.get(iGame)); }
    }

    public void setServersName(String pseudoChoice, String numberChoice, String game) throws DataException, DataAccessException {
        ArrayList<String> temp = serverController.getAllServersName(pseudoChoice, numberChoice, game);
        temp.add("No selection");
        int size = temp.size();
        for(int iServer = 0; iServer < size; iServer++){ servers.add(temp.get(iServer)); }
    }

    private class ComboBoxListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent){
            if(actionEvent.getSource() == playerAccountCombo){

            }
        }
    }



}
