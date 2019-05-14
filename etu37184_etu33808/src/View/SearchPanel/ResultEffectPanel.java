package View.SearchPanel;

import Controller.EffectController;
import Exception.DataAccessException;
import Exception.DataException;
import Model.SearchEffectList;
import View.UtilitiesPanelMethod;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class ResultEffectPanel extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private UtilitiesPanelMethod utilitiesPanelMethod;
    private ArrayList<SearchEffectList> searchEffectList;
    private EffectController effectController;

    public ResultEffectPanel() {
        //Add properties
        setLayout(new FlowLayout());

        //Init
        utilitiesPanelMethod = new UtilitiesPanelMethod();
        effectController = new EffectController();

        table = utilitiesPanelMethod.getJTableModelBlank();
        scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public void setJTable(String gameChoice, String classChoice) throws DataException, DataAccessException {
        searchEffectList = effectController.getSearchEffectList(gameChoice, classChoice);
        AllEffectsFromSpellModel model = new AllEffectsFromSpellModel(searchEffectList);
        remove(scrollPane);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        revalidate();
        repaint();
    }
}