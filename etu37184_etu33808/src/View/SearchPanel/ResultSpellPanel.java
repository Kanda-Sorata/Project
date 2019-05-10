package View.SearchPanel;

import Controller.SpellController;
import Exception.DataAccessException;
import Exception.DataException;
import Model.SearchSpellList;
import View.UtilitiesPanelMethod;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ResultSpellPanel extends JPanel {
    private SearchPanelSpell searchPanelSpell;
    private JTable table;
    private JScrollPane scrollPane;
    private SpellController spellController;
    private ArrayList<SearchSpellList> searchSpellLists;
    private UtilitiesPanelMethod utilitiesPanelMethod;

    public ResultSpellPanel(){
        //Add properties
        setLayout(new FlowLayout());

        //Init
        utilitiesPanelMethod = new UtilitiesPanelMethod();
        spellController = new SpellController();

        //Add components
        table = utilitiesPanelMethod.getJTableModelBlank();
        scrollPane = new JScrollPane(table);
        add(scrollPane);
    }


    public ArrayList<SearchSpellList> getSearchSpellList(String pseudoChoice, int numberChoice)throws DataException, DataAccessException {
        return spellController.getSearchSpellList(pseudoChoice, numberChoice);
    }

    public void setJTable(String pseudoChoice, int numberChoice) throws DataException, DataAccessException {
        searchSpellLists = getSearchSpellList(pseudoChoice, numberChoice);
        AllSpellsListFromPlayerModel model = new AllSpellsListFromPlayerModel(searchSpellLists);
        remove(scrollPane);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        revalidate();
        repaint();
    }

}
