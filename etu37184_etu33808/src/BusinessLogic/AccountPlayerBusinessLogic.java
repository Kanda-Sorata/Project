package BusinessLogic;
import DataAccess.*;
import Exception.*;
import Model.AccountPlayer;

import java.util.ArrayList;

public class AccountPlayerBusinessLogic {


    public static int getNbAccountPlayers() throws StatementException, ConnectionException {
        return DataAccess.AccountPlayerDBAccess.getNbAccountPlayers();
    }

    public static ArrayList<AccountPlayer> getAllAccountPlayer() throws StatementException, NameException, SexException, ConnectionException{
        return DataAccess.AccountPlayerDBAccess.getAllAccountPlayer();
    }




}
