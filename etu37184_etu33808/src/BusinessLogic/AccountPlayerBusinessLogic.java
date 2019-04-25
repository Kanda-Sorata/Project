package BusinessLogic;
import DataAccess.*;
import Exception.*;
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

    public int getNbAccountPlayers() throws NbAccountException{
        return dao.getNbAccountPlayers();
    }

    public ArrayList<AccountPlayer> getAllAccountPlayer() throws AllAccountException{
        return dao.getAllAccountPlayer();
    }

}
