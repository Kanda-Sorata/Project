package Controller;

import BusinessLogic.CharacterClassBusinessLogic;
import Exception.DataAccessException;
import Exception.DataException;
import Model.TopOfClass;

import java.util.ArrayList;

public class CharacterClassController {
    CharacterClassBusinessLogic characterClassBusinessLogic;

    public CharacterClassController(){characterClassBusinessLogic = new CharacterClassBusinessLogic();}

    public ArrayList<String> getClassesInAGame(String game) throws DataException, DataAccessException {
        return characterClassBusinessLogic.getClassesInAGame(game);
    }

   public ArrayList<String> getAllCharactersClassName(String pseudo, int number, String game) throws
                                                                                    DataException, DataAccessException{
        return characterClassBusinessLogic.getAllCharactersClassName(pseudo, number, game);
    }

    public ArrayList<TopOfClass> getAllCharacterClassOrderClass() throws DataAccessException, DataException{
        return  characterClassBusinessLogic.getAllCharacterClassOrderClass();
    }
}
