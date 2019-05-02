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


public class FormPanelLeft extends JPanel {
    private JComboBox playerAccountCombo;
    private JComboBox gameCombo;
    private JComboBox serverCombo;
    private JComboBox characterClassCombo;

    private ArrayList<String> pseudos;
    private ArrayList<String> games;
    private ArrayList<String> servers;
    private ArrayList<String> characterClasses;


    private JLabel playerAccountLabel;
    private JLabel gameLabel;
    private JLabel serverLabel;
    private JLabel characterClassLabel;

    private ComboBoxListener comboBoxListener;

    private String pseudoChoice;
    private int numberChoice;
    private String gameChoice;
    private String serverChoice;
    private String characterClassChoice;

    private UtilitiesPanelMethode utilitiesPanelMethode;
    private GameController gameController;
    private ServerController serverController;
    private CharacterController characterController;

    public FormPanelLeft(){
        utilitiesPanelMethode = new UtilitiesPanelMethode();
        gameController = new GameController();
        serverController = new ServerController();
        characterController = new CharacterController();
        //Add properties
        setLayout(new GridLayout(2,2, 5, 15));

        //Add component
        try{
            comboBoxListener = new ComboBoxListener();

            playerAccountLabel = new JLabel("Player account");
            pseudos = utilitiesPanelMethode.setPlayerAccountsPseudo();
            playerAccountCombo = new JComboBox(pseudos.toArray());
            playerAccountCombo.addActionListener(comboBoxListener);

            gameLabel = new JLabel("Game");
            games.add("No selection");
            gameCombo = new JComboBox(games.toArray());
            gameCombo.addActionListener(comboBoxListener);
            gameCombo.addActionListener(comboBoxListener);
            gameCombo.setEnabled(false);

            serverLabel = new JLabel("Server");
            servers.add("No selection");
            serverCombo = new JComboBox(servers.toArray());
            serverCombo.addActionListener(comboBoxListener);
            serverCombo.setEnabled(false);

            characterClassLabel = new JLabel("Character class");
            characterClasses.add("No selection");
            characterClassCombo = new JComboBox(characterClasses.toArray());
            characterClassCombo.addActionListener(comboBoxListener);
            characterClassCombo.setEnabled(false);

            add(playerAccountLabel);
            add(playerAccountCombo);
            add(gameLabel);
            add(gameCombo);
            add(serverLabel);
            add(serverCombo);
            add(characterClassLabel);
            add(characterClassCombo);

        }catch(DataException dataException){
            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch(DataAccessException dataAccessException){
            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setGamesName(String pseudoChoice, int numberChoice) throws DataException, DataAccessException {
        ArrayList<String> temp = gameController.getAllGamesName(pseudoChoice, numberChoice);
        temp.add("No selection");
        int size = temp.size();
        for(int iGame = 0; iGame < size; iGame++){ games.add(temp.get(iGame)); }
    }

    public void setServersName(String pseudoChoice, int numberChoice, String game) throws DataException, DataAccessException {
        ArrayList<String> temp = serverController.getAllServersName(pseudoChoice, numberChoice, game);
        temp.add("No selection");
        int size = temp.size();
        for(int iServer = 0; iServer < size; iServer++){ servers.add(temp.get(iServer)); }
    }

    public void setCharacterClasses() {
        /*ArrayList<String> temp = characterController.getAllCharactersName();
        temp.add("No selection");
        int size = temp.size();
        for(int iServer = 0; iServer < size; iServer++){ servers.add(temp.get(iServer)); }*/
    }

    private class ComboBoxListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent){
            if(actionEvent.getSource() == playerAccountCombo){
                pseudoChoice = pseudos.get(playerAccountCombo.getSelectedIndex()).split("#")[0];
                numberChoice = Integer.parseInt(pseudos.get(playerAccountCombo.getSelectedIndex()).split("#")[1]);
                try {
                    setGamesName(pseudoChoice, numberChoice);
                    gameCombo.setModel(new DefaultComboBoxModel(games.toArray()));
                    gameCombo.setEnabled(true);
                    gameCombo.revalidate();
                    gameCombo.repaint();
                }catch(DataException dataException){
                    JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }catch (DataAccessException dataAccessException){
                    JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                if(actionEvent.getSource() == gameCombo){
                    gameChoice = gameCombo.getSelectedItem().toString();
                    try {
                        setServersName(pseudoChoice, numberChoice, gameChoice);
                        serverCombo.setModel(new DefaultComboBoxModel(servers.toArray()));
                        serverCombo.revalidate();
                        serverCombo.repaint();;
                        serverCombo.setEnabled(true);
                    }catch(DataException dataException){
                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }catch (DataAccessException dataAccessException){
                        JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                  /*  if(actionEvent.getSource() == serverCombo){
                        try {
                            set(pseudoChoice, numberChoice, gameChoice);
                            characterClassCombo.setModel(new DefaultComboBoxModel(characterClasses.toArray()));
                            characterClassCombo.revalidate();
                            characterClassCombo.repaint();;
                            characterClassCombo.setEnabled(true);
                        }catch(DataException dataException){
                            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }catch (DataAccessException dataAccessException){
                            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{

                    }*/
                }
            }
        }
    }



}
