package View.SearchPanel;

import javax.swing.*;
import java.awt.*;

public class EffectPanel extends JPanel {
    private SearchEffectPanel searchEffectPanel;
    private ResultEffectPanel resultEffectPanel;

    public EffectPanel() {
        //Add properties
        setLayout(new GridLayout(1, 2, 5, 15));

        //Add components
        resultEffectPanel = new ResultEffectPanel();
        searchEffectPanel = new SearchEffectPanel(resultEffectPanel);

        add(searchEffectPanel);
        add(resultEffectPanel);
    }

    public ResultEffectPanel getResultEffectPanel(){
        return resultEffectPanel;
    }
}