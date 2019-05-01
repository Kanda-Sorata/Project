package Controller;

import BusinessLogic.CharacterClassBusinessLogic;
import Exception.ConflictDataException;

import java.util.ArrayList;

public class CharacterClassController {
    CharacterClassBusinessLogic characterClassBusinessLogic;

    public CharacterClassController(){characterClassBusinessLogic = new CharacterClassBusinessLogic();}

    public ArrayList<String> getClassesInAGame(String game) throws ConflictDataException {
        return characterClassBusinessLogic.getClassesInAGame(game);
    }
}
