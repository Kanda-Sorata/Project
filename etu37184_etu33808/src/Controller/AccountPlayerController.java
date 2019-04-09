package Controller;
import BusinessLogic.*;
import Exception.*;
import Model.AccountPlayer;

import java.util.ArrayList;

public class AccountPlayerController {
   public static int getNbAccountPlayers() throws StatementException, ConnectionException {
        return AccountPlayerBusinessLogic.getNbAccountPlayers();
    }

    public static ArrayList<AccountPlayer> getAllAccountPlayer() throws StatementException, NameException, SexException, ConnectionException{
        return BusinessLogic.AccountPlayerBusinessLogic.getAllAccountPlayer();
    }

}
