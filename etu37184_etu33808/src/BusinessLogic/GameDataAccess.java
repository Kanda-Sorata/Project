package BusinessLogic;

import Exception.ConflictDataException;
import Exception.DataAccessException;
import Model.SearchGameList;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface GameDataAccess {
    ArrayList<SearchGameList> getSearchAllGamesListCharacter(String pseudo, String number, String character, GregorianCalendar dateEnd) throws ConflictDataException, DataAccessException;

    ArrayList<String> getAllGamesName(String pseudoChoice, String numberChoice) throws ConflictDataException, DataAccessException;

    ArrayList<String> getAllGames() throws ConflictDataException, DataAccessException;
}
