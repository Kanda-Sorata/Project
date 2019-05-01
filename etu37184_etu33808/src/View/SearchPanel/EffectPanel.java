package View.SearchPanel;

import javax.swing.*;
import java.awt.*;
import Exception.AllGamesException;

public class EffectPanel extends JPanel{
    private SearchEffectPanel searchEffectPanel;
    private ResultEffectPanel resultEffectPanel;

    public EffectPanel() {
        setLayout(new GridLayout(1, 2, 5, 15));
        resultEffectPanel = new ResultEffectPanel();
        searchEffectPanel = new SearchEffectPanel(resultEffectPanel);

        add(searchEffectPanel);
        add(resultEffectPanel);
    }
}