package BusinessLogic;

import DataAccess.CharacterClassDBAccess;
import Exception.DataAccessException;
import Exception.DataException;
import Model.TopOfClass;

import java.util.ArrayList;

public class CharacterClassBusinessLogic {
    CharacterClassDataAccess dao;

    public CharacterClassBusinessLogic(){setDAO(new CharacterClassDBAccess());}

    public void setDAO(CharacterClassDBAccess characterClassDBAccess){ dao = characterClassDBAccess;}

    public ArrayList<String> getClassesInAGame(String game) throws DataException, DataAccessException {
        return dao.getClassesInAGame(game);
    }
    public ArrayList<String> getAllCharactersClassName(String pseudo, int number, String game) throws
            DataException, DataAccessException{
        return dao.getAllCharactersClassName(pseudo, number, game);
    }

    public ArrayList<TopOfClass> getAllCharacterClassOrderClass() throws DataAccessException, DataException {
        try {
            return  dao.getAllCharacterClassOrderClass();
        } catch (DataAccessException dataAccessException) {
            throw new DataAccessException(0);
        } catch (DataException dataException) {
            throw new DataException(1);
        }
    }
}