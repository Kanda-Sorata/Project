package Controller;

import BusinessLogic.AccountPlayerBusinessLogic;
import Exception.ConflictDataException;
import Model.AccountPlayer;

import java.util.ArrayList;

public class AccountPlayerController {
    private AccountPlayerBusinessLogic accountPlayerBusinessLogic;

    public AccountPlayerController(){
        accountPlayerBusinessLogic = new AccountPlayerBusinessLogic();
    }
    public Integer getNbAccountPlayers() throws ConflictDataException {
            return accountPlayerBusinessLogic.getNbAccountPlayers();
    }

    public ArrayList<AccountPlayer> getAllAccountPlayer() throws ConflictDataException {
        return accountPlayerBusinessLogic.getAllAccountPlayer();
    }

}
