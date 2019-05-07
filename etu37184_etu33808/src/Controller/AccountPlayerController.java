package Controller;

import BusinessLogic.AccountPlayerBusinessLogic;
import Exception.DataAccessException;
import Exception.DataException;
import Model.AccountPlayer;

import java.util.ArrayList;

public class AccountPlayerController {
    private AccountPlayerBusinessLogic accountPlayerBusinessLogic;

    public AccountPlayerController(){
        accountPlayerBusinessLogic = new AccountPlayerBusinessLogic();
    }
    public Integer getNbAccountPlayers() throws DataException, DataAccessException {
            return accountPlayerBusinessLogic.getNbAccountPlayers();
    }

    public ArrayList<AccountPlayer> getAllAccountPlayer() throws DataException, DataAccessException {
        return accountPlayerBusinessLogic.getAllAccountPlayer();
    }

}
