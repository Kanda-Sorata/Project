package BusinessLogic;

import DataAccess.CharacterDBAccess;
import Exception.DataAccessException;
import Exception.DataException;
import Model.Character;

import java.util.ArrayList;

public class CharacterBusinessLogic {
    CharacterDataAccess dao;

    public CharacterBusinessLogic() {
        setDao(new CharacterDBAccess());
    }

    public void setDao(CharacterDBAccess dao) {
        this.dao = dao;
    }

    public ArrayList<Character> getAllCharacter(String pseudo, String number) throws DataException, DataAccessException {
        return dao.getAllCharacter(pseudo, number);
    }
}
