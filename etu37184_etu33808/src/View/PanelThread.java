package View;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class PanelThread extends JPanel {
    private JLabel resultPlayers;
    private JLabel resultCharacters;
    private JLabel resultAverage;
    DecimalFormat decimalFormat;

    public PanelThread(){
        //Add properties
        setLayout(new GridLayout(3, 1));

        decimalFormat = new DecimalFormat("#.##");

        //Add & init components
        resultPlayers = new JLabel("Total of players : ");
        resultCharacters = new JLabel("Total of characters : ");
        resultAverage = new JLabel("Average number of characters for a player account : ");

        add(resultPlayers);
        add(resultCharacters);
        add(resultAverage);
    }

    public void setValueOfAllLabels(int nbPlayers, int nbCharacters, double average) {
        resultPlayers.setText("Total of players : " + nbPlayers);
        resultCharacters.setText("Total of characters : " + nbCharacters);
        resultAverage.setText("Average number of characters for a player account : " + decimalFormat.format(average));
    }
}
