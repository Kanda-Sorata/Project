package View.SearchPanel;

import Controller.AccountPlayerController;
import Exception.ConflictDataException;
import Model.AccountPlayer;

import javax.swing.*;
import java.util.ArrayList;

public class UtilitiesPanelMethode {
    private AccountPlayerController accountPlayerController;

    public UtilitiesPanelMethode(){
        accountPlayerController = new AccountPlayerController();
    }

    public ArrayList<String> setPlayerAccountsPseudo() throws ConflictDataException {
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
       return new JTable(data, columns);
    }
}
