package View;

import Controller.AccountPlayerController;
import Exception.DataAccessException;
import Exception.DataException;
import Model.AccountPlayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class UtilitiesPanelMethod {
    private AccountPlayerController accountPlayerController;

    public UtilitiesPanelMethod() {
        accountPlayerController = new AccountPlayerController();
    }

    public ArrayList<String> setPlayerAccountsPseudo() throws DataException, DataAccessException {
        ArrayList<String> playerAccounts = new ArrayList<>();
        Integer nbMaxPlayer = accountPlayerController.getNbAccountPlayers();
        ArrayList<AccountPlayer> players = accountPlayerController.getAllAccountPlayer();
        playerAccounts.add("No selection");

        for (int iPseudo = 0; iPseudo < nbMaxPlayer; iPseudo++) {
           playerAccounts.add(players.get(iPseudo).getPseudo() + "#" + players.get(iPseudo).getNumber());
        }
        return playerAccounts;
    }

    public JTable getJTableModelBlank(){
        String [] columns = {"Colonne 1", "Colonne 2", "Colonne 3"};
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

    public void updatePanelToGoHome(JPanel panel, Frame frame) {
        frame.getContainer().removeAll();
        frame.getContainer().add(new InformationPanel(frame));
        frame.revalidate();
        frame.repaint();
    }
}
