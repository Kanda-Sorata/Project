package View.SearchPanel;

import Model.SearchGameList;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllGamesFromCharacterModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<SearchGameList> contents;

    public AllGamesFromCharacterModel(ArrayList<SearchGameList> searchGameLists){
        columnNames = new ArrayList<>();
        columnNames.add("Name");
        columnNames.add("Release Date");
        columnNames.add("Server");
        setContents(searchGameLists);
    }

    public void setContents(ArrayList<SearchGameList> searchGameLists){ contents = searchGameLists; }
    public int getColumnCount( ) { return columnNames.size( ); }
    public int getRowCount( ) { return contents.size( ); }
    public String getColumnName(int column) { return columnNames.get(column); }
    public Object getValueAt(int row, int column){
        SearchGameList searchGameList = contents.get(row);
        switch (column){
            case 0: return searchGameList.getName();
            case 1: return searchGameList.getReleaseDateStringFormat();
            case 2: return searchGameList.getServer();
            default: return null;
        }
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public Class getColumnClass(int column){
        return String.class;
    }
}
