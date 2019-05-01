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
        playerAccounts = new String[nbMaxPlayer+1];
        playerAccounts[0] = "No selection";
        for (int iPseudo = 0; iPseudo < nbMaxPlayer; iPseudo++) {
            playerAccounts[iPseudo+1] = players.get(iPseudo).getPseudo() + "#" + players.get(iPseudo).getNumber();
        }
        return playerAccounts;
    }

    public JTable getJTableModelBlank(){
        String [] columns = {"Colonne 1", "Colonne 2", "Colonne 3"};
        Object [][] data = {{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}};
       return new JTable(data, columns);
    }
}
