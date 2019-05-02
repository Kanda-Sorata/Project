package View.SearchPanel;

import javax.swing.*;
import java.awt.*;

public class SpellPanel extends JPanel {

    private SearchPanelSpell searchPanelSpell;
    private ResultSpellPanel resultSpellPanel;

    public SpellPanel() {
        //Add properties
        setLayout(new GridLayout(1, 2, 5, 15));

        //Add components
        resultSpellPanel = new ResultSpellPanel();
        searchPanelSpell = new SearchPanelSpell(resultSpellPanel);

        add(searchPanelSpell);
        add(resultSpellPanel);
    }
}
