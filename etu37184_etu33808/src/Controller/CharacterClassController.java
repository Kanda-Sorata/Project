package Controller;

import BusinessLogic.CharacterClassBusinessLogic;
import Exception.AllCommonException;

import java.util.ArrayList;

public class CharacterClassController {
    CharacterClassBusinessLogic characterClassBusinessLogic;

    public CharacterClassController(){characterClassBusinessLogic = new CharacterClassBusinessLogic();}

    public ArrayList<String> getClassesInAGame(String game) throws AllCommonException {
        return characterClassBusinessLogic.getClassesInAGame(game);
    }
}
