package View.SearchPanel;

import Model.SearchEffectList;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllEffectsFromSpellModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<SearchEffectList> contents;

    public AllEffectsFromSpellModel(ArrayList<SearchEffectList> searchEffectList){
        columnNames = new ArrayList<>();
        columnNames.add("Label effect");
        columnNames.add("Spell name");
        columnNames.add("Cooldown");
        setContents(searchEffectList);
    }
    public void setContents(ArrayList<SearchEffectList> searchEffectList){ contents = searchEffectList; }
    public int getColumnCount( ) { return columnNames.size( ); }
    public int getRowCount( ) { return contents.size( ); }
    public String getColumnName(int column) {return columnNames.get(column); }
    public Object getValueAt(int row, int column){
        SearchEffectList searchEffectList = contents.get(row);
        switch (column){
            case 0: return searchEffectList.getLabelEffect();
            case 1: return searchEffectList.getSpellName();
            case 2: return searchEffectList.getCooldown();
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
            case 1:
                c = String.class;
                break;
            case 2: c = Integer.class;
                break;
            default: c = String.class;
        }
        return c;
    }
}
