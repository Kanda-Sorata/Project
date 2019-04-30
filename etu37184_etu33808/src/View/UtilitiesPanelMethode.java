package View;

import Controller.AccountPlayerController;
import Exception.AllAccountException;
import Exception.NbAccountException;
import Model.AccountPlayer;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.rmi.NotBoundException;
import java.util.ArrayList;

public class UtilitiesPanelMethode {
    private AccountPlayerController accountPlayerController;

    public UtilitiesPanelMethode(){
        accountPlayerController = new AccountPlayerController();
    }

    public String [] setPseudos() throws NbAccountException, AllAccountException{
        String [] playerAccounts;
            Integer nbMaxPlayer = accountPlayerController.getNbAccountPlayers();
            ArrayList<AccountPlayer> players = accountPlayerController.getAllAccountPlayer();
            playerAccounts = new String[nbMaxPlayer];
            for (int iPseudo = 0; iPseudo < nbMaxPlayer; iPseudo++) {
                playerAccounts[iPseudo] = players.get(iPseudo).getPseudo() + "#" + players.get(iPseudo).getNumber();
            }
            return playerAccounts;
    }

    public JTable getJTableModelBlank(){
        return new JTable(new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return 20;
            }

            @Override
            public int getColumnCount() {
                return 5;
            }

            @Override
            public Object getValueAt(int i, int i1) {
                return null;
            }
        });
    }
}
