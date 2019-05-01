package BusinessLogic;

import Exception.DataAccessException;
import Exception.DataException;
import Model.Character;

import java.util.ArrayList;

public interface CharacterDataAccess {
    ArrayList<Character> getAllCharacter(String pseudo, String number) throws DataException, DataAccessException;
}
