package BusinessLogic;

import DataAccess.GameDBAccess;
import Exception.ConflictDataException;
import Exception.DataAccessException;
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

    public ArrayList<SearchGameList> getSearchAllGamesListCharacter(String pseudo, String number, String character, GregorianCalendar dateEnd) throws ConflictDataException, DataAccessException {
        return dao.getSearchAllGamesListCharacter(pseudo, number, character, dateEnd);
    }

    public ArrayList<String> getAllGamesName(String pseudoChoice, String numberChoice) throws ConflictDataException, DataAccessException {
        return dao.getAllGamesName(pseudoChoice, numberChoice);
    }
    public ArrayList<String> getAllGames()throws ConflictDataException, DataAccessException {
        return dao.getAllGames();
    }
}
