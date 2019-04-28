package View;

import Controller.GameController;
import javax.swing.*;
import java.awt.*;

public class EffectPanel extends JPanel {

    private SearchPanelEffectsList searchPanelEffectsList;

    private JTable table;
    private  JScrollPane scrollPane;
    private GameController gameController;

    public EffectPanel(){
        gameController = new GameController();
        setLayout(new GridLayout(1, 2));

        searchPanelEffectsList = new SearchPanelEffectsList();
        add(searchPanelEffectsList);
        /*add model
        table = new JTable(model);*/
        scrollPane = new JScrollPane(table);
        add(table);
    }
}

