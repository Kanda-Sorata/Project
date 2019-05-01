package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class SearchEffectPanel extends JPanel {
    private JComboBox gameCombo;
    private JComboBox characterClassCombo;
    private JLabel gameLabel;
    private JLabel characterClassLabel;
    private String [] games;
    private String [] characterClasses;
    //private ArrayList<String> gamesName;
    //private ArrayList<String> characterClassesName;

    public SearchEffectPanel(){
        //Fill arrayList & array
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(new EmptyBorder(150, 0, 250, 250)); //top, left, bottom, right

        //games = retour de la querry
        gameLabel = new JLabel("Game name");
        gameCombo = new JComboBox(games);
        //eventlistener quand il choisit un truc dans la combo box
        characterClassLabel = new JLabel("Character classes");
        characterClassCombo = new JComboBox();

        add(gameLabel);
        add(gameCombo);
        add(characterClassLabel);
        add(characterClassCombo);
    }
}