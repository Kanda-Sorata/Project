package View.SearchPanel;

import Controller.CharacterClassController;
import Controller.GameController;
import Exception.DataAccessException;
import Exception.DataException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public SearchPanelEffect(ResultEffectPanel resultEffectPanel) {
        this.resultEffectPanel = resultEffectPanel;
        gameController = new GameController();
        characterClassController = new CharacterClassController();

        setLayout(new GridLayout(2, 2, 5, 15));
        setBorder(new EmptyBorder(200, 0, 300, 250)); //top, left, bottom, right

        try{
            gameLabel = new JLabel("Game name");
            gameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            games = new ArrayList<>();
            games.add("No selection");
            gamesTemp = gameController.getAllGames();
            int size = gamesTemp.size();
            for(int iGame = 0; iGame < size; iGame++){ games.add(gamesTemp.get(iGame)); }

            gameCombo = new JComboBox(games.toArray());
            comboBoxListener = new ComboBoxListener();
            gameCombo.addActionListener(comboBoxListener);


            characterClassLabel = new JLabel("Character classes");
            characterClassLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            characterClasses = new ArrayList<>();
            characterClasses.add("No Selection");
            characterClassCombo = new JComboBox(characterClasses.toArray());
            characterClassCombo.addActionListener(comboBoxListener);
            characterClassCombo.setEnabled(false);


            add(gameLabel);
            add(gameCombo);
            add(characterClassLabel);
            add(characterClassCombo);

        } catch (DataException dataException){
            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DataAccessException dataAccessException){
            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class ComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == gameCombo) {
                if (!gameCombo.getSelectedItem().toString().equals(games.get(0))) {
                    setGameChoice(gameCombo.getSelectedItem().toString());
                    try {
                        characterClassesTemp = characterClassController.getClassesInAGame(gameChoice);
                        int size = characterClassesTemp.size();
                        characterClasses = new ArrayList<>();
                        characterClasses.add("No selection");
                        //if(size > 0) { //todo inutile, la condition de boucle fera d'office ça
                            for (int iClasses = 0; iClasses < size; iClasses++) {
                                characterClasses.add(characterClassesTemp.get(iClasses));
                            }
                            characterClassCombo.setModel(new DefaultComboBoxModel(characterClasses.toArray()));
                            characterClassCombo.repaint();
                        //}
                    } catch (DataException dataException) {
                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (DataAccessException dataAccessException){
                        JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    characterClassCombo.setEnabled(true);
                }
            } else{
                setClassChoice(characterClassCombo.getSelectedItem().toString());
                try{
                    resultEffectPanel.setJTable(gameChoice, classChoice);
                } catch(DataException dataException){
                    JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }catch(DataAccessException dataAccessException){
                    JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        //Add class
    }

    public void setGameChoice(String gameChoice){
        this.gameChoice = gameChoice;
    }

    public void setClassChoice(String classChoice){
        this.classChoice = classChoice;
    }

}