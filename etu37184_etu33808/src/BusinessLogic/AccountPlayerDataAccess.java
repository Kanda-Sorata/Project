package BusinessLogic;

import Exception.ConflictDataException;
import Exception.DataAccessException;
import Model.AccountPlayer;

import java.util.ArrayList;

public interface AccountPlayerDataAccess {
    Integer getNbAccountPlayers() throws ConflictDataException, DataAccessException;
    ArrayList<AccountPlayer> getAllAccountPlayer() throws ConflictDataException, DataAccessException;
}
