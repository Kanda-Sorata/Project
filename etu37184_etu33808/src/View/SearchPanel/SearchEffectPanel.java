package View.SearchPanel;

import Controller.GameController;

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
    private GameController gameController;
    private ComboBoxClass comboBoxClassListener;

    //private ArrayList<String> gamesName;
    //private ArrayList<String> characterClassesName;

    public SearchEffectPanel() {
        gameController = new GameController();

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(new EmptyBorder(150, 0, 250, 250)); //top, left, bottom, right

        try{
            games = new ArrayList<>();
            games.add("No selection");
            gamesTemp = gameController.getAllGames();

            for(int iGame = 0; iGame < gamesTemp.size(); iGame++){ games.add(gamesTemp.get(iGame)); }

            gameController.getAllGames();
            gameLabel = new JLabel("Game name");
            gameCombo = new JComboBox(games.toArray());
            //eventlistener quand il choisit un truc dans la combo box
            characterClasses = new ArrayList<>();
            characterClasses.add("No selection");
            characterClassLabel = new JLabel("Character classes");
            characterClassCombo = new JComboBox();
            comboBoxClassListener = new ComboBoxClass();
            characterClassCombo.addActionListener(comboBoxClassListener);

            add(gameLabel);
            add(gameCombo);
            add(characterClassLabel);
            add(characterClassCombo);

        } catch (AllGamesException allGameException){
            JOptionPane.showMessageDialog(null, allGameException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            //je la gère ici donc pas besoin de la throws ailleurs
        }
    }

    private class ComboBoxClass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (characterClassCombo.getSelectedIndex() != 0){
                //je dois faire une querry avec les character pour les ajouter dans l'arraylist -> combobox

            }
        }
    }
}