package BusinessLogic;

import Exception.AllAccountException;
import Exception.NbAccountException;
import Model.AccountPlayer;

import java.util.ArrayList;

public interface AccountPlayerDataAccess {
    int getNbAccountPlayers() throws NbAccountException;
    ArrayList<AccountPlayer> getAllAccountPlayer() throws AllAccountException;
}
