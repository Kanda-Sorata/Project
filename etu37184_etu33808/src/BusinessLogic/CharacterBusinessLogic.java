package BusinessLogic;

import DataAccess.CharacterDBAccess;
import Model.Character;
import Exception.*;
import java.util.ArrayList;

public class CharacterBusinessLogic {
    CharacterDataAccess dao;

    public CharacterBusinessLogic() {
        setDao(new CharacterDBAccess());
    }

    public void setDao(CharacterDBAccess dao) {
        this.dao = dao;
    }

    public ArrayList<Character> getAllCharacter(String pseudo, String number) throws AllCharacterException{
        return dao.getAllCharacter(pseudo, number);
    }
}
