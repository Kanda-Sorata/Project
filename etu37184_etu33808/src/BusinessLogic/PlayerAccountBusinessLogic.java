package BusinessLogic;

import DataAccess.PlayerAccountDBAccess;
import Exception.DataAccessException;
import Exception.DataException;
import Model.PlayerAccount;

import java.util.ArrayList;

public class PlayerAccountBusinessLogic {

    PlayerAccountDataAccess dao;

    public PlayerAccountBusinessLogic() {
        setDao(new PlayerAccountDBAccess());
    }

    public void setDao(PlayerAccountDBAccess dao) {
        this.dao = dao;
    }

    public int getNbAccountPlayers() throws DataException, DataAccessException {
        return dao.getNbAccountPlayers();
    }

    public ArrayList<PlayerAccount> getAllAccountPlayer() throws DataException, DataAccessException {
        return dao.getAllAccountPlayer();
    }
}
