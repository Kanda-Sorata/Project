package BusinessLogic;

import Exception.DataAccessException;
import Exception.DataException;
import Model.AccountPlayer;

import java.util.ArrayList;

public interface AccountPlayerDataAccess {
    Integer getNbAccountPlayers() throws DataException, DataAccessException;
    ArrayList<AccountPlayer> getAllAccountPlayer() throws DataException, DataAccessException;
}
