package View.SearchPanel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllCharactersModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<String> contents;

    public AllCharactersModel(ArrayList<String> characters){
        columnNames = new ArrayList<>();
        columnNames.add("Name");
        setContents(characters);
    }
    public void setContents(ArrayList<String> characters){ contents = characters; }
    public int getColumnCount( ) { return columnNames.size( ); }
    public int getRowCount( ) { return contents.size( ); }
    @Override
    public String getColumnName(int column) {return columnNames.get(column); }
    public Object getValueAt(int row, int column){
        String character = contents.get(row);
        switch (column){
            case 0: return character;
            default: return null;
        }
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public Class getColumnClass(int column){
        Class c;
        switch(column){
            case 0: c = String.class;
                break;
            case 1: c = String.class;
                break;
            case 2: c = Integer.class;
                break;
            default: c = String.class;
        }
        return c;
    }
}
