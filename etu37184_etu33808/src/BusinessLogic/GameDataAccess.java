package BusinessLogic;

import Model.SearchGameList;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface GameDataAccess {
    ArrayList<SearchGameList> getSearchAllGamesListCharacter(String pseudo, String number, String character, GregorianCalendar dateEnd) throws AllCommonException;

    ArrayList<String> getAllGamesName(String pseudoChoice, String numberChoice) throws AllCommonException;

    ArrayList<String> getAllGames() throws AllCommonException;
}
