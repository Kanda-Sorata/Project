package BusinessLogic;

import Exception.AllAccountException;
import Model.AccountPlayer;

import java.util.ArrayList;

public interface AccountPlayerDataAccess {
    Integer getNbAccountPlayers() throws AllCommonException;
    ArrayList<AccountPlayer> getAllAccountPlayer() throws AllAccountException;
}
