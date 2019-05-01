package View.SearchPanel;

import Controller.CharacterClassController;
import Controller.GameController;
import Exception.AllCharacterClassException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Exception.AllGamesException;

public class SearchEffectPanel extends JPanel {
    private JComboBox gameCombo;
    private JComboBox characterClassCombo;
    private JLabel gameLabel;
    private JLabel characterClassLabel;
    private ArrayList<String> games;
    private ArrayList<String> gamesTemp;
    private ArrayList<String> characterClasses;
    private ArrayList<String> characterClassesTemp;
    private GameController gameController;
    private CharacterClassController characterClassController;
    private ComboBoxGame comboBoxGameListener;

    private String gameChoice;
    private ResultEffectPanel resultEffectPanel;

    public SearchEffectPanel(ResultEffectPanel resultEffectPanel) {
        this.resultEffectPanel = resultEffectPanel;
        gameController = new GameController();
        characterClassController = new CharacterClassController();

        setLayout(new GridLayout(2, 2, 5, 15));
        setBorder(new EmptyBorder(150, 0, 250, 250)); //top, left, bottom, right

        try{
            gameLabel = new JLabel("Game name");

            games = new ArrayList<>();
            games.add("No selection");
            gamesTemp = gameController.getAllGames();
            int size = gamesTemp.size();
            for(int iGame = 0; iGame < size; iGame++){ games.add(gamesTemp.get(iGame)); }

            gameCombo = new JComboBox(games.toArray());
            comboBoxGameListener = new ComboBoxGame();
            gameCombo.addActionListener(comboBoxGameListener);


            characterClassLabel = new JLabel("Character classes");

            characterClasses = new ArrayList<>();
            characterClasses.add("No Selection");
            characterClassCombo = new JComboBox(characterClasses.toArray());
            characterClassCombo.addActionListener(comboBoxGameListener);
            characterClassCombo.setEnabled(false);


            add(gameLabel);
            add(gameCombo);
            add(characterClassLabel);
            add(characterClassCombo);

        } catch (AllGamesException allGameException){
            JOptionPane.showMessageDialog(null, allGameException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class ComboBoxGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == gameCombo) {
                if (!gameCombo.getSelectedItem().equals(games.get(0))) {
                    setGameChoice(gameCombo.getSelectedItem().toString());
                    try {
                        characterClassesTemp = characterClassController.getClassesInAGame(gameChoice);
                        int size = characterClassesTemp.size();
                        for (int iClasses = 0; iClasses < size; iClasses++) {
                            characterClasses.add(characterClassesTemp.get(iClasses));
                        }
                        characterClassCombo.setModel(new DefaultComboBoxModel(characterClasses.toArray()));
                        repaint();
                        characterClassCombo.setEnabled(true);
                    } catch (AllCharacterClassException allCharacterClassException) {
                        JOptionPane.showMessageDialog(null, allCharacterClassException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else{
               // resultEffectPanel.setJTable(); //Prévooir la méthode dans le panel en question...
            }
        }
        //Add class
    }

    public void setGameChoice(String gameChoice){
        this.gameChoice = gameChoice;
    }

}