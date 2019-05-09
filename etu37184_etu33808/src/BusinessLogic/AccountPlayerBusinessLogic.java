package BusinessLogic;

import DataAccess.AccountPlayerDBAccess;
import Exception.DataAccessException;
import Exception.DataException;
import Model.AccountPlayer;

import java.util.ArrayList;

public class AccountPlayerBusinessLogic {

    AccountPlayerDataAccess dao;

    public AccountPlayerBusinessLogic() {
        setDao(new AccountPlayerDBAccess());
    }

    public void setDao(AccountPlayerDBAccess dao) {
        this.dao = dao;
    }

    public Integer getNbAccountPlayers() throws DataException, DataAccessException {
        return dao.getNbAccountPlayers();
    }

    public ArrayList<AccountPlayer> getAllAccountPlayer() throws DataException, DataAccessException {
        return dao.getAllAccountPlayer();
    }
}
