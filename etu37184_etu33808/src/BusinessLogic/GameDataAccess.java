package BusinessLogic;

import Model.Game;
import Model.Server;

import java.util.ArrayList;
import java.util.HashMap;

public interface GameDataAccess {
    HashMap<Game, ArrayList<Server>> getAllGames();

}
