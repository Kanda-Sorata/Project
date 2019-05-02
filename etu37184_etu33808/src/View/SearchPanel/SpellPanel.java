package View.SearchPanel;

import Controller.SpellController;
import Exception.DataAccessException;
import Exception.DataException;
import Model.SearchSpellList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SpellPanel extends JPanel {
    private SearchPanelSpell searchPanelSpell;
    private JTable table;
    private JScrollPane scrollPane;
    private SpellController spellController;
    private ArrayList<SearchSpellList> searchSpellLists;
    private UtilitiesPanelMethode utilitiesPanelMethode;

    public SpellPanel(){
        utilitiesPanelMethode = new UtilitiesPanelMethode();
        spellController = new SpellController();
        setLayout(new FlowLayout());
        //Add components
        searchPanelSpell = new SearchPanelSpell(this);
        table = utilitiesPanelMethode.getJTableModelBlank();
        scrollPane = new JScrollPane(table);
        add(searchPanelSpell);
        add(scrollPane);
    }

    public ArrayList<SearchSpellList> getSearchSpellList(String pseudoChoice, int numberChoice)throws DataException, DataAccessException {
        return spellController.getSearchSpellList(pseudoChoice, numberChoice);
    }

    public void setJtable(String pseudoChoice, int numberChoice) throws DataException, DataAccessException {
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
