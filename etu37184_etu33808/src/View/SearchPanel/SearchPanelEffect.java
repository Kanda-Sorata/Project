package View.SearchPanel;

import Controller.CharacterClassController;
import Controller.GameController;
import Exception.DataAccessException;
import Exception.DataException;
import View.UtilitiesPanelMethod;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class SearchPanelEffect extends JPanel {
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
    private ComboBoxListener comboBoxListener;

    private String classChoice;
    private String gameChoice;
    private ResultEffectPanel resultEffectPanel;

    private UtilitiesPanelMethod utilitiesPanelMethod;

    public SearchPanelEffect(ResultEffectPanel resultEffectPanel) throws DataAccessException, DataException {
        //Add properties
        setLayout(new GridLayout(2, 2, 5, 15));
        setBorder(new EmptyBorder(200, 0, 300, 250)); //top, left, bottom, right

        //Init
        this.resultEffectPanel = resultEffectPanel;
        gameController = new GameController();
        characterClassController = new CharacterClassController();
        utilitiesPanelMethod = new UtilitiesPanelMethod();


        //Add component
        gameLabel = new JLabel("Game");
        gameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        games = new ArrayList<>();
        games.add("No selection");
        gamesTemp = gameController.getAllGames();
        int size = gamesTemp.size();
        games.addAll(gamesTemp);

        gameCombo = new JComboBox(games.toArray());
        comboBoxListener = new ComboBoxListener();
        gameCombo.addItemListener(comboBoxListener);


        characterClassLabel = new JLabel("Character classes");
        characterClassLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        characterClasses = new ArrayList<>();
        characterClasses.add("No Selection");
        characterClassCombo = new JComboBox(characterClasses.toArray());
        characterClassCombo.addItemListener(comboBoxListener);
        characterClassCombo.setEnabled(false);


        add(gameLabel);
        add(gameCombo);
        add(characterClassLabel);
        add(characterClassCombo);
    }

    private class ComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged (ItemEvent itemEvent) {
            if (itemEvent.getSource() == gameCombo) {
                if (gameCombo.getSelectedIndex() > 0) {
                    gameChoice = gameCombo.getSelectedItem().toString();
                    try {
                        characterClassesTemp = characterClassController.getClassesInAGame(gameChoice);
                        characterClasses = new ArrayList<>();
                        characterClasses.add("No selection");
                        for (String characterClass : characterClassesTemp) {
                            characterClasses.add(characterClass);
                        }
                        characterClassCombo.setModel(new DefaultComboBoxModel(characterClasses.toArray()));
                    } catch (DataException dataException) {
                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (DataAccessException dataAccessException){
                        JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    characterClassCombo.setEnabled(true);
                }
            } else{
                classChoice = characterClassCombo.getSelectedItem().toString();
                try{
                    resultEffectPanel.setJTable(gameChoice, classChoice);
                } catch(DataException dataException){
                    JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }catch(DataAccessException dataAccessException){
                    JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}