package View.CharacterPanel;

import Controller.GameController;
import Controller.CharacterController;
import Exception.DataAccessException;
import Exception.DataException;
import View.SearchPanel.UtilitiesPanelMethode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeletePanel extends JPanel {
    private ArrayList<String> playerAccounts;
    private ArrayList<String> games;
    private ArrayList<String> gamesTemp;
    private ArrayList<String> characters;
    private ArrayList<String> charactersTemp;

    private JComboBox playerAccountCombo;
    private JComboBox gameCombo;
    private JComboBox characterCombo;
    private ComboBoxListener comboBoxListener;
    private JLabel playerAccountLabel;
    private JLabel gameLabel;
    private JLabel characterLabel;
    private JButton validationButton;
    private ButtonListener buttonListener;

    private GameController gameController;
    private CharacterController characterController;

    private UtilitiesPanelMethode utilitiesPanelMethode;
    private String pseudoChoice;
    private int numberChoice;
    private String gameChoice;
    private String characterChoice;

    public DeletePanel(){
        setLayout(new GridLayout(4, 2, 5, 15));
        try{
            utilitiesPanelMethode = new UtilitiesPanelMethode();
            comboBoxListener = new ComboBoxListener();
            gameController = new GameController();
            characterController = new CharacterController();

            playerAccountLabel = new JLabel("Player account");
            playerAccounts = new ArrayList<>();
            playerAccounts = utilitiesPanelMethode.setPlayerAccountsPseudo();
            playerAccountCombo = new JComboBox(playerAccounts.toArray());
            playerAccountCombo.addActionListener(comboBoxListener);

            gameLabel = new JLabel("Game name");
            games = new ArrayList<>();
            games.add("No selection");
            gameCombo = new JComboBox(games.toArray());
            gameCombo.addActionListener(comboBoxListener);
            gameCombo.setEnabled(false);

            characterLabel = new JLabel("Character name");
            characters = new ArrayList<>();
            characters.add("No selection");
            characterCombo = new JComboBox(characters.toArray());
            characterCombo.addActionListener(comboBoxListener);
            characterCombo.setEnabled(false);

            validationButton = new JButton("Validation");
            buttonListener = new ButtonListener();
            validationButton.addActionListener(buttonListener);

            add(playerAccountLabel);
            add(playerAccountCombo);
            add(gameLabel);
            add(gameCombo);
            add(characterLabel);
            add(characterCombo);
            add(validationButton);

        } catch (DataException dataException) {
            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DataAccessException dataAccessException) {
            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class ComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == playerAccountCombo){
                if (playerAccountCombo.getSelectedIndex() == 0) {
                    gameCombo.setSelectedIndex(0);
                    gameCombo.setEnabled(false);
                } else {
                    setPseudoChoice(playerAccounts.get(playerAccountCombo.getSelectedIndex()).split("#")[0]);
                    setNumberChoice(Integer.parseInt(playerAccounts.get(playerAccountCombo.getSelectedIndex()).split("#")[1]));
                    try {
                        gamesTemp = gameController.getAllGamesName(pseudoChoice, numberChoice);
                        games = new ArrayList();
                        games.add("No selection");
                        int gameSize = gamesTemp.size();
                        for(int iGame = 0; iGame < gameSize; iGame++){ games.add(gamesTemp.get(iGame)); }
                        gameCombo.setModel(new DefaultComboBoxModel(games.toArray()));
                        gameCombo.repaint();
                    } catch (DataException dataException) {
                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (DataAccessException dataAccessException) {
                        JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    gameCombo.setEnabled(true); //Tester si le choix est différent de no selection
                }
            } else if(actionEvent.getSource() == gameCombo){
                if(gameCombo.getSelectedIndex() == 0){
                    characterCombo.setSelectedIndex(0);
                    characterCombo.setEnabled(false);
                } else {
                    setGameChoice(games.get(gameCombo.getSelectedIndex()));
                    try {
                        charactersTemp = characterController.getAllCharactersInAGame(pseudoChoice, numberChoice, gameChoice);
                        characters = new ArrayList();
                        characters.add("No selection");
                        int charactersSize = charactersTemp.size();
                        for(int iCharacters = 0; iCharacters < charactersSize; iCharacters++){ characters.add(charactersTemp.get(iCharacters)); }
                        characterCombo.setModel(new DefaultComboBoxModel(characters.toArray()));
                        characterCombo.repaint();
                    } catch (DataException dataException) {
                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (DataAccessException dataAccessException) {
                        JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    characterCombo.setEnabled(true);
                }
            } else {
                setCharacterChoice(characters.get(characterCombo.getSelectedIndex()));
            }
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(playerAccountCombo.getSelectedIndex() != 0 && gameCombo.getSelectedIndex() != 0 && characterCombo.getSelectedIndex() != 0) {
                try {
                    int state = characterController.deleteACharacter(pseudoChoice, numberChoice, gameChoice, characterChoice);
                } catch (DataException dataException) {
                    JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (DataAccessException dataAccessException) {
                    JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void setPseudoChoice(String pseudoChoice) {
        this.pseudoChoice = pseudoChoice;
    }

    public void setNumberChoice(int numberChoice) {
        this.numberChoice = numberChoice;
    }

    public void setGameChoice(String gameChoice){
        this.gameChoice = gameChoice;
    }

    public void setCharacterChoice(String characterChoice){
        this.characterChoice = characterChoice;
    }
}