package View;

import Controller.PlayerAccountController;
import Exception.DataAccessException;
import Exception.DataException;
import Model.PlayerAccount;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class UtilitiesPanelMethod {
    private PlayerAccountController playerAccountController;

    public UtilitiesPanelMethod() {
        playerAccountController = new PlayerAccountController();
    }

    public ArrayList<String> setPlayerAccountsPseudo() throws DataException, DataAccessException {
        ArrayList<String> playerAccounts = new ArrayList<>();
        ArrayList<PlayerAccount> players = playerAccountController.getAllAccountPlayer();
        playerAccounts.add("No selection");

        for (PlayerAccount player : players) {
            playerAccounts.add(player.getPseudo() + "#" + player.getNumber());
        }

        return playerAccounts;
    }

    public JTable getJTableModelBlank(){
        String[] columns = {"Column 1", "Column 2", "Column 3"};
        Object [][] data = {{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}};
        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        table.setModel(tableModel);
       return table;
    }
}
