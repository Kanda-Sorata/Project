package View.SearchPanel;

import Controller.EffectController;
import Model.SearchEffectList;
import Exception.DataAccessException;
import Exception.DataException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ResultEffectPanel extends JPanel {

    private SearchEffectPanel searchEffectPanel;
    private EffectController effectController;
    private JTable table;
    private  JScrollPane scrollPane;
    private ArrayList<SearchEffectList> searchEffectList;
    private UtilitiesPanelMethode utilitiesPanelMethode;

    public ResultEffectPanel() {
        utilitiesPanelMethode = new UtilitiesPanelMethode();
        effectController = new EffectController();
        setLayout(new FlowLayout());

        searchEffectPanel = new SearchEffectPanel(this);
        table = utilitiesPanelMethode.getJTableModelBlank();
        scrollPane = new JScrollPane(table);
        add(searchEffectPanel);
        add(table);
    }

    public void setJTable(String gameChoice, String classChoice) throws DataException, DataAccessException {
        searchEffectList = effectController.getSearchEffectList(gameChoice, classChoice);
        AllEffectsFromSpellModel model = new AllEffectsFromSpellModel(searchEffectList);
        remove(table);
        table = new JTable(model);
        add(table);
        revalidate();
        repaint();
    }
}