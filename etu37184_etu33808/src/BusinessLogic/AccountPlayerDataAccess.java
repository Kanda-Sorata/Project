package BusinessLogic;

import Model.AccountPlayer;
import Exception.*;

import java.rmi.NotBoundException;
import java.util.ArrayList;

public interface AccountPlayerDataAccess {
    int getNbAccountPlayers() throws NbAccountException;
    ArrayList<AccountPlayer> getAllAccountPlayer() throws AllAccountException;
}
