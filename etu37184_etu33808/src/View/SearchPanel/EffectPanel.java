package View.SearchPanel;

import javax.swing.*;
import java.awt.*;
import Exception.AllGamesException;

public class EffectPanel extends JPanel{
    private SearchEffectPanel searchEffectPanel;
    private ResultEffectPanel resultEffectPanel;

    public EffectPanel() {
        setLayout(new GridLayout(1, 2, 5, 15));
        searchEffectPanel = new SearchEffectPanel();
        //resultEffectPanel = new ResultEffectPanel();
        add(searchEffectPanel);
        //add(resultEffectPanel);
    }
}