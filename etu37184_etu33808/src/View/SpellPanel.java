package View;

import Controller.SpellController;
import Exception.*;
import Model.SearchSpellList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SpellPanel extends JPanel {
    private SearchPanelSpellList searchPanelSpellList;
    private JTable table;
    private JScrollPane scrollPane;
    private SpellController spellController;
    private ArrayList<SearchSpellList> searchSpellLists;

    public SpellPanel(){
        spellController = new SpellController();
        //Add properties
        setLayout(new GridLayout(1, 2, 5, 15));

        //Add components
        try {
            searchPanelSpellList = new SearchPanelSpellList();
            add(searchPanelSpellList, BorderLayout.WEST);
                /*add model
                table = new JTable(model);*/
           // scrollPane = new JScrollPane(table);
            add(table, BorderLayout.CENTER);
        }catch(NbAccountException nbAccountException){
            JOptionPane.showConfirmDialog(null, nbAccountException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch (AllAccountException allAccountException){
            JOptionPane.showConfirmDialog(null, allAccountException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<SearchSpellList> getSearchSpellList(String pseudoChoice, String numberchoice){
        return spellController.getSearchSpellList(pseudoChoice, numberchoice);
    }

    public void setJtable() throws {
        searchSpellLists = getSearchSpellList(searchPanelSpellList.getPseudoChoice(), searchPanelSpellList.getNumberChoice());
        AllSpellsFromPlayersModel model = new AllSpellsFromPlayersModel(searchSpellLists);
        table.setModel(model);
    }



}
