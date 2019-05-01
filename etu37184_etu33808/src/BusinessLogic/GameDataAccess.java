package BusinessLogic;

import Exception.DataAccessException;
import Exception.DataException;
import Model.SearchGameList;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface GameDataAccess {
    ArrayList<SearchGameList> getSearchAllGamesListCharacter(String pseudo, String number, String character, GregorianCalendar dateEnd) throws DataException, DataAccessException;

    ArrayList<String> getAllGamesName(String pseudoChoice, String numberChoice) throws DataException, DataAccessException;

    ArrayList<String> getAllGames() throws DataException, DataAccessException;
}
