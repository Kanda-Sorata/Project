package View.CharacterPanel;

import Controller.CharacterClassController;
import Controller.CharacterController;
import Controller.GameController;
import Controller.ServerController;
import Exception.DataAccessException;
import Exception.DataException;
import View.UtilitiesPanelMethod;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
    private int healthPointMax;

    private UtilitiesPanelMethod utilitiesPanelMethod;
    private GameController gameController;
    private ServerController serverController;
    private CharacterClassController characterClassController;
    private CharacterController characterController;

    private FormPanelRight formPanelRight;
    private boolean isModifyPanel;


    public FormPanelLeftModify(FormPanelRight formPanelRight, boolean isModifyPanel) throws DataException, DataAccessException {
        //Add properties
        setLayout(new GridLayout(5, 2, 5, 15));
        setBorder(new EmptyBorder(150, 50, 120, 1)); //Top, left, bottom, right

        //Init
        utilitiesPanelMethod = new UtilitiesPanelMethod();
        gameController = new GameController();
        serverController = new ServerController();
        characterClassController = new CharacterClassController();
        characterController = new CharacterController();
        this.isModifyPanel = isModifyPanel;
        this.formPanelRight = formPanelRight;

        pseudoChoice = "No selection";
        gameChoice = "No selection";
        serverChoice = "No selection";
        characterClassChoice = "No selection";

        if (isModifyPanel) {
            characterChoice = "No selection";
        }


        pseudos = utilitiesPanelMethod.setPlayerAccountsPseudo();

        //Listener
        comboBoxListener = new ComboBoxListener();

        //Add components
        playerAccountLabel = new JLabel("<html>Player Account<font color = 'red'>*</font></html>");
        playerAccountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        playerAccountCombo = new JComboBox(pseudos.toArray());
        playerAccountCombo.addItemListener(comboBoxListener);

        gameLabel = new JLabel("<html>Game<font color = 'red'>*</font></html>");
        gameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        games = new ArrayList<>();
        games.add("No selection");
        gameCombo = new JComboBox(games.toArray());
        gameCombo.addItemListener(comboBoxListener);
        gameCombo.setEnabled(false);

        serverLabel = new JLabel("<html>Server<font color = 'red'>*</font></html>");
        serverLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        servers = new ArrayList<>();
        servers.add("No selection");
        serverCombo = new JComboBox(servers.toArray());
        serverCombo.addItemListener(comboBoxListener);
        serverCombo.setEnabled(false);

        characterClassLabel = new JLabel("<html>Character class<font color = 'red'>*</font></html>");
        characterClassLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        characterClasses = new ArrayList<>();
        characterClasses.add("No selection");
        characterClassCombo = new JComboBox(characterClasses.toArray());
        characterClassCombo.addItemListener(comboBoxListener);
        characterClassCombo.setEnabled(false);

        add(playerAccountLabel);
        add(playerAccountCombo);
        add(gameLabel);
        add(gameCombo);
        add(serverLabel);
        add(serverCombo);
        add(characterClassLabel);
        add(characterClassCombo);

        if (isModifyPanel) {
            characterLabel = new JLabel("Character");
            characterLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            characters = new ArrayList<>();
            characters.add("No selection");
            characterCombo = new JComboBox(characters.toArray());
            characterCombo.setEnabled(false);
            characterCombo.addItemListener(comboBoxListener);
            add(characterLabel);
            add(characterCombo);
        }
    }

    public void setGamesName(String pseudoChoice, int numberChoice) throws DataException, DataAccessException {
        ArrayList<String> gameTemp = gameController.getAllGamesName(pseudoChoice, numberChoice);
        for (String game : gameTemp) {
            games.add(game);
        }
    }

    public void setServersName(String pseudoChoice, int numberChoice, String game) throws DataException, DataAccessException {
        ArrayList<String> serverTemp = serverController.getAllServersName(pseudoChoice, numberChoice, game);
        for (String server : serverTemp) {
            servers.add(server);
        }
    }

    public void setCharacterClasses(String pseudoChoice, int numberChoice, String gameChoice) throws DataException, DataAccessException {
        ArrayList<String> characterClassesTemp = characterClassController.getAllCharactersClassName(pseudoChoice, numberChoice, gameChoice);
        for (String characterClass : characterClassesTemp) {
            characterClasses.add(characterClass);
        }
    }

    public void setCharactersName(String pseudoChoice, int numberChoice, String gameChoice, String serverChoice, String characterClassChoice) throws DataException, DataAccessException {
        ArrayList<String> charactersTemp = characterController.getAllCharactersInAGameInServerWithCharacterClass(pseudoChoice, numberChoice, gameChoice, serverChoice, characterClassChoice);
        for (String character : charactersTemp) {
            characters.add(character);
        }
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
        playerAccountLabel.setText("<html><font color = 'red'>Player Account*</font></html>");
    }

    public void setGameLabelError() {
        gameLabel.setText("<html><font color = 'red'>Game*</font></html>");
    }

    public void setServerLabelError() {
        serverLabel.setText("<html><font color = 'red'>Server*</font></html>");
    }

    public void setCharacterClassLabelError() {
        characterClassLabel.setText("<html><font color = 'red'>Character class*</font></html>");
    }

    public void setCharacterLabelError() {
        characterLabel.setText("<html><font color = 'red'>Character*</font></html>");
    }

    //Reset
    public void setPlayerAccountLabelReset() {
        playerAccountLabel.setText("<html>Player Account<font color = 'red'>*</font></html>");
    }

    public void setGameLabelReset() {
        gameLabel.setText("<html>Game<font color = 'red'>*</font></html>");
    }

    public void setServerLabelReset() {
        serverLabel.setText("<html>Server<font color = 'red'>*</font></html>");
    }

    public void setCharacterClassLabelReset() {
        characterClassLabel.setText("<html>Character class<font color = 'red'>*</font></html>");
    }

    public void setCharacterLabelReset() {
        characterLabel.setText("<html>Character<font color = 'red'>*</font></html>");
    }


    public boolean isModifyPanel() {
        return isModifyPanel;
    }

    public void setIsModifyPanel(boolean isModifyPanel) {
        this.isModifyPanel = isModifyPanel;
    }

    private class ComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if (itemEvent.getSource() == playerAccountCombo) {
                if (playerAccountCombo.getSelectedIndex() > 0) {
                    pseudoChoice = pseudos.get(playerAccountCombo.getSelectedIndex()).split("#")[0];
                    numberChoice = Integer.parseInt(pseudos.get(playerAccountCombo.getSelectedIndex()).split("#")[1]);
                    games = new ArrayList<>();
                    games.add("No selection");
                    try {
                        setGamesName(pseudoChoice, numberChoice);
                        gameCombo.setModel(new DefaultComboBoxModel(games.toArray()));
                        resetAfterPlayerCombo();
                        gameCombo.setEnabled(true);
                    } catch (DataException dataException) {
                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (DataAccessException dataAccessException) {
                        JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    resetAfterPlayerCombo();
                    pseudoChoice = "No selection";
                }
            } else {
                if (itemEvent.getSource() == gameCombo) {
                    if (gameCombo.getSelectedIndex() > 0) {
                        gameChoice = gameCombo.getSelectedItem().toString();
                        servers = new ArrayList<>();
                        servers.add("No selection");
                        try {
                            setServersName(pseudoChoice, numberChoice, gameChoice);
                            serverCombo.setModel(new DefaultComboBoxModel(servers.toArray()));
                            serverCombo.setSelectedIndex(0);
                            resetAfterGameCombo();
                            serverCombo.setEnabled(true);
                        } catch (DataException dataException) {
                            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (DataAccessException dataAccessException) {
                            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        resetAfterGameCombo();
                    }
                } else {
                    if (itemEvent.getSource() == serverCombo) {
                        if (serverCombo.getSelectedIndex() > 0) {
                            serverChoice = serverCombo.getSelectedItem().toString();
                            characterClasses = new ArrayList<>();
                            characterClasses.add("No selection");
                            try {
                                setCharacterClasses(pseudoChoice, numberChoice, gameChoice);
                                characterClassCombo.setModel(new DefaultComboBoxModel(characterClasses.toArray()));
                                characterClassCombo.setSelectedIndex(0);
                                resetAfterServerCombo();
                                characterClassCombo.setEnabled(true);
                            } catch (DataException dataException) {
                                JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            } catch (DataAccessException dataAccessException) {
                                JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            resetAfterServerCombo();
                        }
                    } else {
                        if (itemEvent.getSource() == characterClassCombo) {
                            if (characterClassCombo.getSelectedIndex() > 0) {
                                characterClassChoice = characterClassCombo.getSelectedItem().toString();
                                healthPointMax = getHealthPointMax();
                                formPanelRight.setHealthPointSlider(0, healthPointMax, healthPointMax);
                                formPanelRight.setHealthPointMax(healthPointMax);

                                if (isModifyPanel) {
                                    characters = new ArrayList<>();
                                    characters.add("No selection");
                                    try {
                                        setCharactersName(pseudoChoice, numberChoice, gameChoice, serverChoice, characterClassChoice);
                                        characterCombo.setModel(new DefaultComboBoxModel(characters.toArray()));
                                        characterCombo.setSelectedIndex(0);
                                        characterCombo.setEnabled(true);
                                        if (isModifyPanel) {
                                            resetComboModifyPanel();
                                        }
                                    } catch (DataAccessException dataAccessException) {
                                        JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    } catch (DataException dataException) {
                                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            } else {
                                characterClassChoice = "No selection";
                                if (isModifyPanel) {
                                    resetComboModifyPanel();
                                }
                            }
                        } else {
                            if (isModifyPanel) {
                                if (itemEvent.getSource() == characterCombo) {
                                    if (characterCombo.getSelectedIndex() > 0) {
                                        characterChoice = characterCombo.getSelectedItem().toString();
                                        formPanelRight.setFieldWithCharacterValues(pseudoChoice, numberChoice, gameChoice,
                                                serverChoice, characterClassChoice, characterChoice);
                                    } else {
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

    private int getHealthPointMax() {
        healthPointMax = 0;
        switch (characterClassCombo.getSelectedIndex()) {
            case 1:
                healthPointMax = 50000;
                break;
            case 2:
                healthPointMax = 40000;
                break;
            case 3:
            case 4:
                healthPointMax = 20000;
                break;

        }
        return healthPointMax;
    }

    private void resetComboModifyPanel() {
        characterCombo.setSelectedIndex(0);
        characterCombo.setEnabled(false);
        characterChoice = "No selection";
        formPanelRight.unsetFieldWithCharacterValues();
    }

    private void resetAfterPlayerCombo() {
        gameCombo.setSelectedIndex(0);
        serverCombo.setSelectedIndex(0);
        characterClassCombo.setSelectedIndex(0);
        serverChoice = "No selection";
        characterClassChoice = "No selection";
        gameCombo.setEnabled(false);
        serverCombo.setEnabled(false);
        characterClassCombo.setEnabled(false);
        if (isModifyPanel) {
            resetComboModifyPanel();
        }
    }

    private void resetAfterGameCombo() {
        serverCombo.setSelectedIndex(0);
        characterClassCombo.setSelectedIndex(0);
        serverChoice = "No selection";
        characterClassChoice = "No selection";
        serverCombo.setEnabled(false);
        characterClassCombo.setEnabled(false);
        if (isModifyPanel) {
            resetComboModifyPanel();
        }
    }

    private void resetAfterServerCombo() {
        characterClassCombo.setSelectedIndex(0);
        characterClassChoice = "No selection";
        characterClassCombo.setEnabled(false);
        if (isModifyPanel) {
            resetComboModifyPanel();
        }
    }

}
