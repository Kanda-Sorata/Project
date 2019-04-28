package BusinessLogic;

import Exception.AllAccountException;
import Exception.NbAccountException;
import Model.AccountPlayer;

import java.util.ArrayList;

public interface AccountPlayerDataAccess {
    Integer getNbAccountPlayers() throws NbAccountException;
    ArrayList<AccountPlayer> getAllAccountPlayer() throws AllAccountException;
}
