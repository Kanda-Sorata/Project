package BusinessLogic;
import DataAccess.*;
import Exception.*;
import Model.AccountPlayer;

import java.util.ArrayList;

public class AccountPlayerBusinessLogic {


    public static int getNbAccountPlayers() throws SelectException {
        return DataAccess.AccountPlayerDBAccess.getNbAccountPlayers();
    }

    public static ArrayList<AccountPlayer> getAllAccountPlayer() throws SelectException, NameException, SexException{
        return DataAccess.AccountPlayerDBAccess.getAllAccountPlayer();
    }

}
