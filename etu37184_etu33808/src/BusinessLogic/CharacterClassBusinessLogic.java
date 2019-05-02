package BusinessLogic;

import DataAccess.CharacterClassDBAccess;
import Exception.DataAccessException;
import Exception.DataException;

import java.util.ArrayList;

public class CharacterClassBusinessLogic {
    CharacterClassDataAccess dao;

    public CharacterClassBusinessLogic(){setDAO(new CharacterClassDBAccess());}

    public void setDAO(CharacterClassDBAccess characterClassDBAccess){ dao = characterClassDBAccess;}

    public ArrayList<String> getClassesInAGame(String game) throws DataException, DataAccessException {
        return dao.getClassesInAGame(game);
    }
    public ArrayList<String> getAllClassesName(String pseudo, int number, String game, String server) throws
            DataException, DataAccessException{
        return dao.getAllCharactersName(pseudo, number, game, server);
    }
}