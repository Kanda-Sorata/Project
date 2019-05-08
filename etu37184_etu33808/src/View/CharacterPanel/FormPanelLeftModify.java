package View.CharacterPanel;

import Controller.CharacterClassController;
import Controller.CharacterController;
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


public class FormPanelLeftModify extends JPanel {
    private JComboBox playerAccountCombo;
    private JComboBox gameCombo;
    private JComboBox serverCombo;
    private JComboBox characterClassCombo;
    private JComboBox characterCombo;

    private ArrayList<String> pseudos;
    private ArrayList<String> games;
    private ArrayList<String> servers;
    private ArrayList<String> characterClasses;
    private ArrayList<String> characters;


    private JLabel playerAccountLabel;
    private JLabel gameLabel;
    private JLabel serverLabel;
    private JLabel characterClassLabel;
    private JLabel characterLabel;
    private ComboBoxListener comboBoxListener;

    private String pseudoChoice;
    private int numberChoice;
    private String gameChoice;
    private String serverChoice;
    private String characterClassChoice;
    private String characterChoice;

    private UtilitiesPanelMethode utilitiesPanelMethode;
    private GameController gameController;
    private ServerController serverController;
    private CharacterClassController characterClassController;
    private CharacterController characterController;

    private FormPanelRight formPanelRight;
    private  boolean isModifyPanel;

    private enum Classes{
        Tank(1),
        Cleric(2),
        Archer(3),
        Wizard(4);
        private final int id;
        Classes(int id){ this.id = id; }
        public int getClassesId(){ return id; }
    }

    public FormPanelLeftModify(FormPanelRight formPanelRight, boolean isModifyPanel ){
        //Add properties
        setLayout(new GridLayout(5, 2, 5, 15));
        setBorder(new EmptyBorder(150, 50, 120, 1)); //Top, left, bottom, right

        utilitiesPanelMethode = new UtilitiesPanelMethode();
        gameController = new GameController();
        serverController = new ServerController();
        characterClassController = new CharacterClassController();
        characterController = new CharacterController();
        this.isModifyPanel  = isModifyPanel;
        this.formPanelRight = formPanelRight;

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

            if(isModifyPanel) {
                characterLabel = new JLabel("Character name");
                characterLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                characters = new ArrayList<>();
                characters.add("No selection");
                characterCombo = new JComboBox(characters.toArray());
                characterCombo.setEnabled(false);
                characterCombo.addActionListener(comboBoxListener);
                add(characterLabel);
                add(characterCombo);
            }

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

    public void setCharacterClasses(String pseudoChoice, int numberChoice, String gameChoice) throws DataException, DataAccessException {
        ArrayList<String> temp = characterClassController.getAllCharactersClassName(pseudoChoice, numberChoice, gameChoice);
        int size = temp.size();
        for(int iClass = 0; iClass < size; iClass++){ characterClasses.add(temp.get(iClass)); }
    }

    public void setCharactersName(String pseudoChoice, int numberChoice, String gameChoice, String serverChoice, String characterClassChoice) throws DataException, DataAccessException{
        ArrayList<String> temp = characterController.getAllCharactersInAGameInServerWithCharacterClass(pseudoChoice, numberChoice, gameChoice, serverChoice, characterClassChoice);
        int size = temp.size();
        for(int iCharacter = 0; iCharacter < size; iCharacter++){ characters.add(temp.get(iCharacter)); }
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

    public String getCharacterChoice() {
        return characterChoice;
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

    public void setCharacterLabelError(){
        characterLabel.setText("<html><font color = 'red'>Character name*</font></html>\"");
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

    public void setCharacterLabelReset(){
        characterLabel.setText("<html>Character name<font color = 'red'>*</font></html>\"");
    }



    public boolean isModifyPanel() {
        return isModifyPanel;
    }

    public void setIsModifyPanel(boolean isModifyPanel) {
        this.isModifyPanel = isModifyPanel;
    }

    private class ComboBoxListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent){
            if(actionEvent.getSource() == playerAccountCombo){
                if(playerAccountCombo.getSelectedIndex() > 0) {
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
                    if(gameCombo.getSelectedIndex() > 0) {
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
                        if(serverCombo.getSelectedIndex() > 0){
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
                                if(isModifyPanel){
                                    characterChoice = null;
                                }
                            }else{
                                serverChoice = "No selection";
                                characterClassCombo.setSelectedIndex(0);
                                characterClassChoice = "No selection";
                                characterClassCombo.setEnabled(false);
                            }
                        }
                    }else{
                        if(actionEvent.getSource() == characterClassCombo){
                            if(characterClassCombo.getSelectedIndex() > 0){
                                characterClassChoice = characterClassCombo.getSelectedItem().toString();
                             /*   int max = 0; //TODO

                                switch(){
                                    case "Tank":
                                        max = 50000;
                                        break;
                                    case "Cleric":
                                        max = 40000;
                                        break;
                                    case "Archer":
                                        max = 20000;
                                        break;
                                    case "Wizard":
                                        max = 20000;
                                        break;

                                }
                                formPanelRight.setHealthPointSlider(0, max, max/2);*/
                                if(isModifyPanel) {
                                    characters = new ArrayList<>();
                                    characters.add("No selection");
                                    try {
                                        setCharactersName(pseudoChoice, numberChoice, gameChoice, serverChoice, characterClassChoice);
                                        characterCombo.setModel(new DefaultComboBoxModel(characters.toArray()));
                                        characterCombo.revalidate();
                                        characterCombo.repaint();
                                        characterCombo.setEnabled(true);
                                        characterChoice = null;
                                    } catch (DataAccessException dataAccessException) {
                                        JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    } catch (DataException dataException) {
                                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }else{
                                characterClassChoice = "No selection";
                                if(isModifyPanel) {
                                    characterCombo.setSelectedIndex(0);
                                    characterCombo.setEnabled(false);
                                }
                            }
                        }else{
                            if (actionEvent.getSource() == characterCombo) {
                                if (characterCombo.getSelectedIndex() > 0) {
                                    characterChoice = characterCombo.getSelectedItem().toString();
                                    formPanelRight.setFieldWithCharacterValues(pseudoChoice, numberChoice, gameChoice,
                                            serverChoice, characterClassChoice, characterChoice);
                                }else {
                                    characterChoice = "No selection";
                                    formPanelRight.unsetFieldWithCharacterValues();
                                }
                            }
                        }
                    }
                }
            }
        }
    }



}
