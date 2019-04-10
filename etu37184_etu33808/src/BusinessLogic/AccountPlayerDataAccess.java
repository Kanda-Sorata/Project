package BusinessLogic;

import Model.AccountPlayer;
import Exception.*;

import java.util.ArrayList;

public interface AccountPlayerDataAccess {
    int getNbAccountPlayers() throws ConnectionException, StatementException;
    ArrayList<AccountPlayer> getAllAccountPlayer() throws NameException, SexException, ConnectionException, StatementException;
}
