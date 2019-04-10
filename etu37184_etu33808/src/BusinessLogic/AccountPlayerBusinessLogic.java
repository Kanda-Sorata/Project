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

    public int getNbAccountPlayers() throws StatementException, ConnectionException {
        return dao.getNbAccountPlayers();
    }

    public ArrayList<AccountPlayer> getAllAccountPlayer() throws StatementException, NameException, SexException, ConnectionException{
        return dao.getAllAccountPlayer();
    }




}
