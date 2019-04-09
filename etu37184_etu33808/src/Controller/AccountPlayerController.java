package Controller;
import BusinessLogic.*;
import Exception.*;
import Model.AccountPlayer;

import java.util.ArrayList;

public class AccountPlayerController {
    public static int getNbAccountPlayers() throws SelectException {
        return AccountPlayerBusinessLogic.getNbAccountPlayers();
    }

    public static ArrayList<AccountPlayer> getAllAccountPlayer() throws SelectException, NameException, SexException{
        return BusinessLogic.AccountPlayerBusinessLogic.getAllAccountPlayer();
    }

}
