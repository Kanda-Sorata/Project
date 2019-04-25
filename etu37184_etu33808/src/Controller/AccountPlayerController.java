package Controller;
import BusinessLogic.*;
import Exception.*;
import Model.AccountPlayer;

import java.util.ArrayList;

public class AccountPlayerController {
    private AccountPlayerBusinessLogic accountPlayerBusinessLogic;

    public AccountPlayerController(AccountPlayerBusinessLogic accountPlayerBusinessLogic){
        this.accountPlayerBusinessLogic = accountPlayerBusinessLogic;
    }
    public int getNbAccountPlayers() throws NbAccountException{
            return accountPlayerBusinessLogic.getNbAccountPlayers();
    }

    public ArrayList<AccountPlayer> getAllAccountPlayer() throws AllAccountException{
        return accountPlayerBusinessLogic.getAllAccountPlayer();
    }

}
