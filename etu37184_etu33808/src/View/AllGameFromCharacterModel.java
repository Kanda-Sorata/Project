package View;

import Model.Game;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class AllGameFromCharacterModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Game> contents;

    public AllGameFromCharacterModel(ArrayList<Game> games){
        columnNames = new ArrayList<>();
        columnNames.add("Name");
        columnNames.add("Release Date");
        columnNames.add("Server");
        setContents(games);
    }

    public void setContents(ArrayList<Game> games){ contents = games; }
    public int getColumnCount( ) { return columnNames.size( ); }
    public int getRowCount( ) { return contents.size( ); }
    public String getColumnName(int column) { return columnNames.get(column); }
    public Object getValueAt(int row, int column){
        Game game = contents.get(row);
        switch (column){
            case 0: return game.getName();
            case 1: return game.getReleaseDaze();
            case 2: {if(game.getPrice() != null) return game.getPrice(); else return null;}
            default: return null;
        }
    }

    public Class getColumClass(int column){
        Class c;
        switch(column){
            case 0: c = String.class;
                break;
            case 1: c = Date.class;
                break;
            case 2: c = String.class;
                break;
            default: c = String.class;
        }

        return c;
    }
}
