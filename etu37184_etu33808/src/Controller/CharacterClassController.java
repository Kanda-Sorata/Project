package Controller;

import BusinessLogic.CharacterClassBusinessLogic;
import Exception.DataAccessException;
import Exception.DataException;

import java.util.ArrayList;

public class CharacterClassController {
    CharacterClassBusinessLogic characterClassBusinessLogic;

    public CharacterClassController(){characterClassBusinessLogic = new CharacterClassBusinessLogic();}

    public ArrayList<String> getClassesInAGame(String game) throws DataException, DataAccessException {
        return characterClassBusinessLogic.getClassesInAGame(game);
    }

    public ArrayList<String> getAllClassesName(String pseudo, int number, String game) throws
                                                                                    DataException, DataAccessException{
        return characterClassBusinessLogic.getAllClassesName(pseudo, number, game);
    }
}
