package View;

import javax.swing.*;
import java.awt.*;

public class EffectPanel extends JPanel{
    private SearchEffectPanel searchEffectPanel;
    private ResultEffectPanel resultEffectPanel;

    public EffectPanel(){
        setLayout(new GridLayout(1, 2, 5, 15));

        searchEffectPanel = new SearchEffectPanel();
        resultEffectPanel = new ResultEffectPanel();
    }
}