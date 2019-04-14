package View;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;


public class SearchPanelGeneral extends JPanel {
    private SearchPanelGameList searchPanelGameList;
    private SearchPanelEffectsList searchPanelEffectsList;
    private SearchPanelSpellList searchPanelSpellList;
    private JTable table;
    private  JScrollPane scrollPane;

    public SearchPanelGeneral(int numberPanel){
        setLayout(new BorderLayout());
        switch(numberPanel) {
            case 1:
                searchPanelGameList = new SearchPanelGameList();
                add(searchPanelGameList, BorderLayout.WEST);
               /* AllGameFromCharacterModel model = new AllGameFromCharacterModel(); SQL request
                table = new JTable(model);*/
                break;
            case 2:
                searchPanelSpellList = new SearchPanelSpellList();
                add(searchPanelSpellList, BorderLayout.WEST);
                /*add model
                table = new JTable(model);*/
                break;
            case 3:
                searchPanelEffectsList = new SearchPanelEffectsList();
                add(searchPanelEffectsList, BorderLayout.WEST);
                /*add model
                table = new JTable(model);*/
                break;
        }

        scrollPane = new JScrollPane(table);
        add(table, BorderLayout.CENTER);
        setVisible(true);
    }
}
