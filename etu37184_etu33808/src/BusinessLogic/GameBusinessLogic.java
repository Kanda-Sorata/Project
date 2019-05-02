package BusinessLogic;

import DataAccess.GameDBAccess;
import Exception.DataAccessException;
import Exception.DataException;
import Model.SearchGameList;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class GameBusinessLogic implements GameDataAccess {

    private GameDataAccess dao;

    public GameBusinessLogic(){
        setDao(new GameDBAccess());
    }

    public void setDao(GameDataAccess dao) {
        this.dao = dao;
    }

    public ArrayList<SearchGameList> getSearchAllGamesListCharacter(String pseudo, int number, String character, GregorianCalendar dateEnd) throws DataException, DataAccessException {
        return dao.getSearchAllGamesListCharacter(pseudo, number, character, dateEnd);
    }

    public ArrayList<String> getAllGamesName(String pseudoChoice, int numberChoice) throws DataException, DataAccessException {
        return dao.getAllGamesName(pseudoChoice, numberChoice);
    }
    public ArrayList<String> getAllGames()throws DataException, DataAccessException {
        return dao.getAllGames();
    }
}
