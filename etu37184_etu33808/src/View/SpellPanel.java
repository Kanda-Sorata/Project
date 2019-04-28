package View;

import Controller.GameController;

import javax.swing.*;
import java.awt.*;

public class SpellPanel extends JPanel {
    private SearchPanelSpellList searchPanelSpellList;
    private JTable table;
    private JScrollPane scrollPane;
    private GameController gameController;

    public SpellPanel(){
        gameController = new GameController();
        setLayout(new GridLayout(1, 2));
        searchPanelSpellList = new SearchPanelSpellList();
        add(searchPanelSpellList, BorderLayout.WEST);
                /*add model
                table = new JTable(model);*/
        scrollPane = new JScrollPane(table);
        add(table, BorderLayout.CENTER);
    }

}
