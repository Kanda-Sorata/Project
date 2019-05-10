package View.SearchPanel;

import Exception.DataAccessException;
import Exception.DataException;

import javax.swing.*;
import java.awt.*;

public class EffectPanel extends JPanel {
    private SearchPanelEffect searchPanelEffect;
    private ResultEffectPanel resultEffectPanel;

    public EffectPanel() throws DataAccessException, DataException {
        //Add properties
        setLayout(new GridLayout(1, 2, 5, 15));

        //Add components
        resultEffectPanel = new ResultEffectPanel();
        searchPanelEffect = new SearchPanelEffect(resultEffectPanel);

        add(searchPanelEffect);
        add(resultEffectPanel);
    }
}