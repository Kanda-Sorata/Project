package Controller;

import BusinessLogic.AccountPlayerBusinessLogic;
import Exception.AllAccountException;
import Exception.NbAccountException;
import Model.AccountPlayer;

import java.util.ArrayList;

public class AccountPlayerController {
    private AccountPlayerBusinessLogic accountPlayerBusinessLogic;

    public AccountPlayerController(){
        accountPlayerBusinessLogic = new AccountPlayerBusinessLogic();
    }
    public int getNbAccountPlayers() throws NbAccountException{
            return accountPlayerBusinessLogic.getNbAccountPlayers();
    }

    public ArrayList<AccountPlayer> getAllAccountPlayer() throws AllAccountException{
        return accountPlayerBusinessLogic.getAllAccountPlayer();
    }

}
