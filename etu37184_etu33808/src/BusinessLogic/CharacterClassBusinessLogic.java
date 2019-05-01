package BusinessLogic;

import DataAccess.CharacterClassDBAccess;

import java.util.ArrayList;

public class CharacterClassBusinessLogic {
    CharacterClassDataAccess dao;

    public CharacterClassBusinessLogic(){setDAO(new CharacterClassDBAccess());}

    public void setDAO(CharacterClassDBAccess characterClassDBAccess){ dao = characterClassDBAccess;}

    public ArrayList<String> getClassesInAGame(String game) throws AllCommonException {
        return dao.getClassesInAGame(game);
    }
}