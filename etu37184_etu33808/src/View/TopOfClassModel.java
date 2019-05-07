package View;

import Model.TopOfClass;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TopOfClassModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<TopOfClass> contents;

    public TopOfClassModel(ArrayList<TopOfClass> topOfClasses){
        columnNames = new ArrayList<>();
        columnNames.add("Server");
        columnNames.add("Purcent");
        columnNames.add("CharacterClass");
        setContents(topOfClasses);
    }
    public void setContents(ArrayList<TopOfClass> topOfClasses){ contents = topOfClasses; }
    public int getColumnCount( ) { return columnNames.size( ); }
    public int getRowCount( ) { return contents.size( ); }
    @Override
    public String getColumnName(int column) {return columnNames.get(column); }
    public Object getValueAt(int row, int column){
        TopOfClass topOfClass = contents.get(row);
        switch (column){
            case 0: return topOfClass.getServerName();
            case 1: return topOfClass.getPurcent();
            case 2: return topOfClass.getClassName();
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
            case 1: c = Double.class;
                break;
            case 2: c = String.class;
                break;
            default: c = String.class;
        }
        return c;
    }
}
