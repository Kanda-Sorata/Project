package View;

import Model.TopOfClass;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TopOfClassModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<TopOfClass> contents;

    public TopOfClassModel(ArrayList<TopOfClass> topOfClasses){
        columnNames = new ArrayList<>();
        columnNames.add("Character Class");
        columnNames.add("Percent of utilisation on all character");
        columnNames.add("Description");
        setContents(topOfClasses);
    }
    public void setContents(ArrayList<TopOfClass> topOfClasses){ contents = topOfClasses; }
    public int getColumnCount( ) { return columnNames.size( ); }
    public int getRowCount( ) { return contents.size( ); }
    public String getColumnName(int column) {return columnNames.get(column); }
    public Object getValueAt(int row, int column){
        TopOfClass topOfClass = contents.get(row);
        switch (column){
            case 0: return topOfClass.getClassName();
            case 1: return topOfClass.getPercentFormatter();
            case 2: return topOfClass.getDescription();
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
