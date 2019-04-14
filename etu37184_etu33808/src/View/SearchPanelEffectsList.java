package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchPanelEffectsList extends JPanel {
    private JComboBox gameNameCombo;
    private ArrayList<JCheckBox> characterClass;
    private JLabel gameName;
    private String [] games;

    public SearchPanelEffectsList(){
        //Fill arrayList & array
        setLayout(new GridLayout(characterClass.size(), 2, 5, 15));

        gameName = new JLabel("Game name");
        gameNameCombo = new JComboBox(games);


        add(gameName);
        add(gameNameCombo);
    }
}
