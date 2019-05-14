package BusinessLogic;

import Exception.DataAccessException;
import Exception.DataException;
import Model.PlayerAccount;

import java.util.ArrayList;

public interface PlayerAccountDataAccess {
    int getNbAccountPlayers() throws DataException, DataAccessException;
    ArrayList<PlayerAccount> getAllAccountPlayer() throws DataException, DataAccessException;
}
