package BusinessLogic;

import Exception.AllGamesException;
import Model.SearchGameList;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface GameDataAccess {
    ArrayList<SearchGameList>  getSearchAllGamesListCharacter(String pseudo, String number, String character, GregorianCalendar dateEnd) throws AllGamesException;
    ArrayList<String> getAllGamesName(String pseudoChoice, String numberChoice)throws AllGamesException;
}
