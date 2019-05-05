package View.CharacterPanel;

import Controller.CharacterClassController;
import Controller.GameController;
import Controller.ServerController;
import Exception.DataAccessException;
import Exception.DataException;
import View.SearchPanel.UtilitiesPanelMethode;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    private CharacterClassController characterClassController;

    public FormPanelLeft(){
        utilitiesPanelMethode = new UtilitiesPanelMethode();
        gameController = new GameController();
        serverController = new ServerController();
        characterClassController = new CharacterClassController();
        //Add properties
        setLayout(new GridLayout(4,2, 5, 15));
        setBorder(new EmptyBorder(150, 50, 175, 125)); //Top, left, bottom, right
        //Init & add component
        try{
            comboBoxListener = new ComboBoxListener();

            playerAccountLabel = new JLabel("<html>Player account<font color = 'red'>*</font></html>");
            playerAccountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            pseudos = utilitiesPanelMethode.setPlayerAccountsPseudo();
            playerAccountCombo = new JComboBox(pseudos.toArray());
            playerAccountCombo.addActionListener(comboBoxListener);

            gameLabel = new JLabel("<html>Game name<font color = 'red'>*</font></html>");
            gameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            games = new ArrayList<>();
            games.add("No selection");
            gameCombo = new JComboBox(games.toArray());
            gameCombo.addActionListener(comboBoxListener);
            gameCombo.setEnabled(false);

            serverLabel = new JLabel("<html>Server name<font color = 'red'>*</font></html>");
            serverLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            servers = new ArrayList<>();
            servers.add("No selection");
            serverCombo = new JComboBox(servers.toArray());
            serverCombo.addActionListener(comboBoxListener);
            serverCombo.setEnabled(false);

            characterClassLabel = new JLabel("<html>Character class<font color = 'red'>*</font></html>");
            characterClassLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            characterClasses = new ArrayList<>();
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
        int size = temp.size();

        for(int iGame = 0; iGame < size; iGame++){ games.add(temp.get(iGame)); }
    }

    public void setServersName(String pseudoChoice, int numberChoice, String game) throws DataException, DataAccessException {
        ArrayList<String> temp = serverController.getAllServersName(pseudoChoice, numberChoice, game);
        int size = temp.size();
        for(int iServer = 0; iServer < size; iServer++){ servers.add(temp.get(iServer)); }
    }

    public void setCharacterClasses(String pseudoChoice, int numberChoice, String game) throws DataException, DataAccessException  {
        ArrayList<String> temp = characterClassController.getAllClassesName(pseudoChoice, numberChoice, game);
        int size = temp.size();
        for(int iClass = 0; iClass < size; iClass++){ characterClasses.add(temp.get(iClass)); }
    }


    public String getPseudoChoice() {
        return pseudoChoice;
    }

    public int getNumberChoice() {
        return numberChoice;
    }

    public String getGameChoice() {
        return gameChoice;
    }

    public String getServerChoice() {
        return serverChoice;
    }

    public String getCharacterClassChoice() {
        return characterClassChoice;
    }

    public void setPlayerAccountCombo(int index) {
        playerAccountCombo.setSelectedIndex(index);
    }

    public void setGameCombo(int index) {
        gameCombo.setSelectedIndex(index);
    }

    public void setServerCombo(int index) {
        serverCombo.setSelectedIndex(index);
    }

    public void setCharacterClassCombo(int index) {
        characterClassCombo.setSelectedIndex(index);
    }

    public int getIndexPlayerAccount() {
        return playerAccountCombo.getSelectedIndex();
    }

    public int getIndexGame() {
        return gameCombo.getSelectedIndex();
    }


    public int getIndexServer() {
        return serverCombo.getSelectedIndex();
    }


    public int getIndexCharacterClass() {
        return characterClassCombo.getSelectedIndex();
    }
    //Error
    public void setPlayerAccountLabelError() {
        playerAccountLabel.setText("<html><font color = 'red'>Player account*</font></html>");
    }

    public void setGameLabelError() {
        gameLabel.setText("<html><font color = 'red'>Game name*</font></html>");
    }

    public void setServerLabelError() {
        serverLabel.setText("<html><font color = 'red'>Server name*</font></html>");
    }

    public void setCharacterClassLabelError() {
        characterClassLabel.setText("<html><font color = 'red'>Character class*</font></html>");
    }
    //Reset
    public void setPlayerAccountLabelReset(){
        playerAccountLabel.setText("<html>Player account<font color = 'red'>*</font></html>");
    }

    public void setGameLabelReset() {
        gameLabel.setText("<html>Game name<font color = 'red'>*</font></html>");
    }

    public void setServerLabelReset() {
        serverLabel.setText("<html>Server name<font color = 'red'>*</font></html>");
    }

    public void setCharacterClassLabelReset() {
        characterClassLabel.setText("<html>Character class<font color = 'red'>*</font></html>");
    }


    private class ComboBoxListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent){
            if(actionEvent.getSource() == playerAccountCombo){
                if(playerAccountCombo.getSelectedIndex() != 0) {
                    pseudoChoice = pseudos.get(playerAccountCombo.getSelectedIndex()).split("#")[0];
                    numberChoice = Integer.parseInt(pseudos.get(playerAccountCombo.getSelectedIndex()).split("#")[1]);
                    games = new ArrayList<>();
                    games.add("No selection");
                    try {
                        setGamesName(pseudoChoice, numberChoice);
                        gameCombo.setModel(new DefaultComboBoxModel(games.toArray()));
                        gameCombo.setEnabled(true);
                        gameCombo.revalidate();
                        gameCombo.repaint();
                    } catch (DataException dataException) {
                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (DataAccessException dataAccessException) {
                        JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    gameCombo.setSelectedIndex(0);
                    serverCombo.setSelectedIndex(0);
                    characterClassCombo.setSelectedIndex(0);
                    pseudoChoice = "No selection";
                    serverChoice = "No selection";
                    characterClassChoice = "No selection";
                    gameCombo.setEnabled(false);
                    serverCombo.setEnabled(false);
                    characterClassCombo.setEnabled(false);
                }
            }else{
                if(actionEvent.getSource() == gameCombo){
                    if(gameCombo.getSelectedIndex() != 0) {
                        gameChoice = gameCombo.getSelectedItem().toString();
                        servers = new ArrayList<>();
                        servers.add("No selection");
                        try {
                            setServersName(pseudoChoice, numberChoice, gameChoice);
                            serverCombo.setModel(new DefaultComboBoxModel(servers.toArray()));
                            serverCombo.revalidate();
                            serverCombo.repaint();
                            serverCombo.setEnabled(true);
                        } catch (DataException dataException) {
                            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (DataAccessException dataAccessException) {
                            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        gameChoice = "No selection";
                        serverCombo.setSelectedIndex(0);
                        characterClassCombo.setSelectedIndex(0);
                        serverChoice = "No selection";
                        characterClassChoice = "No selection";
                        serverCombo.setEnabled(false);
                        characterClassCombo.setEnabled(false);
                    }
                }else{
                    if(actionEvent.getSource() == serverCombo){
                        if(serverCombo.getSelectedIndex() != 0){
                            serverChoice = serverCombo.getSelectedItem().toString();
                            characterClasses = new ArrayList<>();
                            characterClasses.add("No selection");
                            try {
                                setCharacterClasses(pseudoChoice, numberChoice, gameChoice);
                                characterClassCombo.setModel(new DefaultComboBoxModel(characterClasses.toArray()));
                                characterClassCombo.revalidate();
                                characterClassCombo.repaint();
                                characterClassCombo.setEnabled(true);
                            } catch (DataException dataException) {
                                JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            } catch (DataAccessException dataAccessException) {
                                JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            if(actionEvent.getSource() == characterClassCombo) {
                                characterClassChoice = characterClassCombo.getSelectedItem().toString();
                            }else{
                                serverChoice = "No selection";
                                characterClassCombo.setSelectedIndex(0);
                                characterClassChoice = "No selection";
                                characterClassCombo.setEnabled(false);
                            }
                        }
                    }else{
                        if(characterClassCombo.getSelectedIndex() != 0){
                            characterClassChoice = characterClassCombo.getSelectedItem().toString();
                        }else{
                            characterClassChoice = "No selection";
                        }
                    }
                }
            }
        }
    }



}
