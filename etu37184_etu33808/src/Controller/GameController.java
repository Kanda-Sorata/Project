package Controller;

import BusinessLogic.GameBusinessLogic;
import Exception.AllGamesException;
import Model.SearchGameList;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class GameController {
    private GameBusinessLogic gameBusinessLogic;

    public GameController(){
        gameBusinessLogic = new GameBusinessLogic();
    }

    public ArrayList<SearchGameList> getSearchAllGamesListCharacter(String pseudo, String number, String character, GregorianCalendar dateEnd) throws AllGamesException {
        return gameBusinessLogic.getSearchAllGamesListCharacter(pseudo, number, character, dateEnd);
    }
}
