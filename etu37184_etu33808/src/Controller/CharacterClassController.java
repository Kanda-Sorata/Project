package Controller;

import BusinessLogic.CharacterClassBusinessLogic;
import java.util.ArrayList;
import Exception.AllCharacterClassException;

public class CharacterClassController {
    CharacterClassBusinessLogic characterClassBusinessLogic;

    public CharacterClassController(){characterClassBusinessLogic = new CharacterClassBusinessLogic();}

    public ArrayList<String> getClassesInAGame(String game) throws AllCharacterClassException {
        return characterClassBusinessLogic.getClassesInAGame(game);
    }
}
