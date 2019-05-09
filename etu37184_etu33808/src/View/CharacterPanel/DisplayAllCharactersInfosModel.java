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
        columnNames.add("GameName");
        columnNames.add("ServerName");
        columnNames.add("CharacterName");
        columnNames.add("CharacterClassName");
        columnNames.add("HealthPoint");
        columnNames.add("Stuffed");
        columnNames.add("CreationDate");
        columnNames.add("PetName");
        columnNames.add("DamagePerSecond");

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

    @Override
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
                return character.getCreationDateFormater();
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
                c = String.class;
                break;
            case 1:
                c = String.class;
                break;
            case 2:
                c = String.class;
                break;
            case 3:
                c = String.class;
                break;
            case 4:
                c = int.class;
                break;
            case 5:
                c = boolean.class;
                break;
            case 6:
                c = GregorianCalendar.class;
                break;
            case 7:
                c = String.class;
                break;
            case 8:
                c = Integer.class;
                break;
            default:
                c = String.class;
        }
        return c;
    }
}
