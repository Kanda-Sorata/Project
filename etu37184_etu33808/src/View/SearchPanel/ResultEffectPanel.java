package View.SearchPanel;

import Controller.GameController;
import javax.swing.*;
import java.awt.*;
import Exception.AllGamesException;

public class ResultEffectPanel extends JPanel {

    private SearchEffectPanel searchEffectPanel;

    private JTable table;
    private  JScrollPane scrollPane;
    private GameController gameController;

    public ResultEffectPanel() {
        gameController = new GameController();
        setLayout(new FlowLayout(FlowLayout.RIGHT));

        /*add model
        table = new JTable(model);*/
        scrollPane = new JScrollPane(table);
        //add(table);
    }
}