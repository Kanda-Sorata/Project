package Controller;

import BusinessLogic.GameBusinessLogic;
import Exception.ConflictDataException;
import Model.SearchGameList;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class GameController {
    private GameBusinessLogic gameBusinessLogic;

    public GameController(){
        gameBusinessLogic = new GameBusinessLogic();
    }

    public ArrayList<SearchGameList> getSearchAllGamesListCharacter(String pseudo, String number, String character, GregorianCalendar dateEnd) throws ConflictDataException {
        return gameBusinessLogic.getSearchAllGamesListCharacter(pseudo, number, character, dateEnd);
    }

    public ArrayList<String> getAllGamesName(String pseudoChoice, String numberChoice)throws ConflictDataException {
        return gameBusinessLogic.getAllGamesName(pseudoChoice, numberChoice);
    }

    public ArrayList<String> getAllGames()throws ConflictDataException {
        return gameBusinessLogic.getAllGames();
    }
}
