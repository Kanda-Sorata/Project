package View.CharacterPanel;

import Model.DisplayCharacter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DisplayAllCharactersInfosModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<DisplayCharacter> contents;

    public DisplayAllCharactersInfosModel(ArrayList<DisplayCharacter> characters) {
        columnNames = new ArrayList<>();
        columnNames.add("Game");
        columnNames.add("Server");
        columnNames.add("Character");
        columnNames.add("Character class");
        columnNames.add("Health point(s)");
        columnNames.add("Stuffed");
        columnNames.add("Creational date");
        columnNames.add("Pet");
        columnNames.add("Damage per second");
        setContents(characters);
    }

    public void setContents(ArrayList<DisplayCharacter> characters) {
        contents = characters;
    }

    public int getColumnCount() {
        return columnNames.size();
    }

    public int getRowCount() {
        return contents.size();
    }

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public Object getValueAt(int row, int column) {
        DisplayCharacter character = contents.get(row);
        switch (column) {
            case 0:
                return character.getGameName();
            case 1:
                return character.getServerName();
            case 2:
                return character.getCharacterName();
            case 3:
                return character.getCharacterClassName();
            case 4:
                return character.getHealthPoint();
            case 5:
                return character.getIsStuffed();
            case 6:
                return character.getCreationDateFormatter();
            case 7:
                return character.getPetName();
            case 8:
                return character.getDamagePerSecond();
            default:
                return null;
        }
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public Class getColumnClass(int column) {
        Class c;
        switch (column) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 7:
                c = String.class;
                break;
            case 4:
            case 8:
                c = Integer.class;
                break;
            case 5:
                c = Boolean.class;
                break;
            case 6:
                c = GregorianCalendar.class;
                break;
            default:
                c = String.class;
        }
        return c;
    }
}
