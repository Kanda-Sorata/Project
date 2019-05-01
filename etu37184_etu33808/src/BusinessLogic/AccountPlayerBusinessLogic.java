package BusinessLogic;

import DataAccess.AccountPlayerDBAccess;
import Exception.ConflictDataException;
import Exception.DataAccessException;
import Model.AccountPlayer;

import java.util.ArrayList;

public class AccountPlayerBusinessLogic {

    AccountPlayerDataAccess dao;

    public AccountPlayerBusinessLogic() {
        setDao(new  AccountPlayerDBAccess());
    }

    public void setDao(AccountPlayerDBAccess dao) {
        this.dao = dao;
    }

    public Integer getNbAccountPlayers() throws ConflictDataException, DataAccessException {
        return dao.getNbAccountPlayers();
    }

    public ArrayList<AccountPlayer> getAllAccountPlayer() throws ConflictDataException, DataAccessException {
        return dao.getAllAccountPlayer();
    }
}
