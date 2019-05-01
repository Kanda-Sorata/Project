package View.SearchPanel;

import Controller.SpellController;
import Exception.*;
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
        try {
            searchPanelSpell = new SearchPanelSpell(this);
            table = utilitiesPanelMethode.getJTableModelBlank();
            scrollPane = new JScrollPane(table);
            add(searchPanelSpell);
            add(table);
        }catch(NbAccountException nbAccountException){
            JOptionPane.showMessageDialog(null, nbAccountException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch (AllAccountException allAccountException){
            JOptionPane.showMessageDialog(null, allAccountException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<SearchSpellList> getSearchSpellList(String pseudoChoice, String numberChoice)throws AllSpellsException{
        return spellController.getSearchSpellList(pseudoChoice, numberChoice);
    }

    public void setJtable() throws AllSpellsException{
        searchSpellLists = getSearchSpellList(searchPanelSpell.getPseudoChoice(), searchPanelSpell.getNumberChoice());
        AllSpellsListFromPlayerModel model = new AllSpellsListFromPlayerModel(searchSpellLists);
        remove(table);
        table = new JTable(model);
        add(table);
        revalidate();
        repaint();
    }
}