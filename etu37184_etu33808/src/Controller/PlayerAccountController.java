package Controller;

import BusinessLogic.PlayerAccountBusinessLogic;
import Exception.DataAccessException;
import Exception.DataException;
import Model.PlayerAccount;

import java.util.ArrayList;

public class PlayerAccountController {
    private PlayerAccountBusinessLogic playerAccountBusinessLogic;

    public PlayerAccountController() {
        playerAccountBusinessLogic = new PlayerAccountBusinessLogic();
    }

    public int getNbAccountPlayers() throws DataException, DataAccessException {
        return playerAccountBusinessLogic.getNbAccountPlayers();
    }

    public ArrayList<PlayerAccount> getAllAccountPlayer() throws DataException, DataAccessException {
        return playerAccountBusinessLogic.getAllAccountPlayer();
    }

}
