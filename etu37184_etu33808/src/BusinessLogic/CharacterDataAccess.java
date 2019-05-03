package BusinessLogic;

import Exception.DataAccessException;
import Exception.DataException;
import Model.Character;

import java.util.ArrayList;

public interface CharacterDataAccess {
    ArrayList<Character> getAllCharacter(String pseudo, int number) throws DataException, DataAccessException;
    ArrayList<String> getAllCharactersInAGame(String pseudo, int number, String gameName) throws DataException, DataAccessException;
    int deleteACharacter(String pseudo, int number, String gameName, String characterName) throws DataAccessException, DataException;
}