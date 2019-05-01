package BusinessLogic;

import DataAccess.CharacterDBAccess;
import Exception.ConflictDataException;
import Exception.DataAccessException;
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

    public ArrayList<Character> getAllCharacter(String pseudo, String number) throws ConflictDataException, DataAccessException {
        return dao.getAllCharacter(pseudo, number);
    }
}
