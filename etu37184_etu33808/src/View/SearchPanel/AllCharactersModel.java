package View.SearchPanel;

import Model.DeleteCharacter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllCharactersModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<DeleteCharacter> contents;

    public AllCharactersModel(ArrayList<DeleteCharacter> characters) {
        columnNames = new ArrayList<>();
        columnNames.add("Server name");
        columnNames.add("Character name");
        columnNames.add("Character creation date");
        setContents(characters);
    }

    public void setContents(ArrayList<DeleteCharacter> characters) {
        contents = characters;
    }
    public int getColumnCount( ) { return columnNames.size( ); }
    public int getRowCount( ) { return contents.size( ); }
    public String getColumnName(int column) {return columnNames.get(column); }
    public Object getValueAt(int row, int column){
        DeleteCharacter character = contents.get(row);
        switch (column) {
            case 0:
                return character.getServer();
            case 1:
                return character.getName();
            case 2:
                return character.getCreationDateFormatter();
            default:
                return null;
        }
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public Class getColumnClass(int column){
        return String.class;
    }
}
