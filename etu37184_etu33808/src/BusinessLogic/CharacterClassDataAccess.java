package BusinessLogic;

import Model.AccountPlayer;
import Exception.AllCharacterClassException;
import java.util.ArrayList;

public interface CharacterClassDataAccess {
    ArrayList<String> getClassesInAGame(String game) throws AllCharacterClassException;
}
