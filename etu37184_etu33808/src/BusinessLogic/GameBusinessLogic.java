package BusinessLogic;

import DataAccess.GameDBAccess;
import Model.Game;
import Model.Server;
import Exception.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameBusinessLogic implements GameDataAccess {

    private GameDataAccess dao;

    public GameBusinessLogic(){
        setDao(new GameDBAccess());
    }

    public void setDao(GameDataAccess dao) {
        this.dao = dao;
    }

    public HashMap<Game, ArrayList<Server>> getAllGames() throws AllGamesException {
        return dao.getAllGames();
    }
}
