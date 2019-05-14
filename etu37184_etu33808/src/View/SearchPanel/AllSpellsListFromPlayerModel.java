package View.SearchPanel;

import Model.SearchSpellList;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllSpellsListFromPlayerModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<SearchSpellList> contents;

    public AllSpellsListFromPlayerModel(ArrayList<SearchSpellList> searchSpellLists){
        columnNames = new ArrayList<>();
        columnNames.add("Name");
        columnNames.add("Cooldown");
        columnNames.add("CharacterName");
        setContents(searchSpellLists);
    }

    public void setContents(ArrayList<SearchSpellList> searchSpellLists){ contents = searchSpellLists; }
    public int getColumnCount() { return columnNames.size( ); }
    public int getRowCount( ) { return contents.size( ); }
    public String getColumnName(int column) { return columnNames.get(column); }
    public Object getValueAt(int row, int column){
        SearchSpellList searchSpellLists = contents.get(row);
        switch (column){
            case 0: return searchSpellLists.getSpellName();
            case 1: return searchSpellLists.getSpellCooldown();
            case 2: return searchSpellLists.getCharacterName();
            default: return null;
        }
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public Class getColumnClass(int column){
        Class c;
        switch(column){
            case 0:
            case 2:
                c = String.class;
                break;
            case 1: c = Integer.class;
                break;
            default: c = String.class;
        }
        return c;
    }
}
