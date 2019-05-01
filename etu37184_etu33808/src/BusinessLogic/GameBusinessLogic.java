package BusinessLogic;

import DataAccess.GameDBAccess;
import Exception.AllGamesException;
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

    public ArrayList<SearchGameList> getSearchAllGamesListCharacter(String pseudo, String number, String character, GregorianCalendar dateEnd) throws AllGamesException {
        return dao.getSearchAllGamesListCharacter(pseudo, number, character, dateEnd);
    }

    public ArrayList<String> getAllGamesName(String pseudoChoice, String numberChoice) throws AllGamesException{
        return dao.getAllGamesName(pseudoChoice, numberChoice);
    }
}
