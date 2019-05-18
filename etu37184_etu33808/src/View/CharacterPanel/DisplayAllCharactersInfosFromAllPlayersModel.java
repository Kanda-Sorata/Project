package View.CharacterPanel;

import Model.DisplayCharacter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DisplayAllCharactersInfosFromAllPlayersModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<DisplayCharacter> contents;
    private boolean isSpecifiedPlayer;

    public DisplayAllCharactersInfosFromAllPlayersModel(ArrayList<DisplayCharacter> characters, boolean isSpecifiedPlayer) {
        this.isSpecifiedPlayer = isSpecifiedPlayer;
        columnNames = new ArrayList<>();
        if (!isSpecifiedPlayer) {
            columnNames.add("Player account");
        }
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
        if (isSpecifiedPlayer) column += 1;
        switch (column) {
            case 0:
                return character.getPlayerAccount();
            case 1:
                return character.getGameName();
            case 2:
                return character.getServerName();
            case 3:
                return character.getCharacterName();
            case 4:
                return character.getCharacterClassName();
            case 5:
                return character.getHealthPoint();
            case 6:
                return character.getIsStuffed();
            case 7:
                return character.getCreationDateFormatter();
            case 8:
                return character.getPetName();
            case 9:
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
        if (isSpecifiedPlayer) column += 1;
        switch (column) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 7:
            case 8:
                c = String.class;
                break;
            case 5:
            case 9:
                c = Integer.class;
                break;
            case 6:
                c = Boolean.class;
                break;
            default:
                c = String.class;
        }
        return c;
    }
}
