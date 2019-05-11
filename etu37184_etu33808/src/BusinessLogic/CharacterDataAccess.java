package BusinessLogic;

import Exception.DataAccessException;
import Exception.DataException;
import Model.Character;
import Model.DisplayCharacter;

import java.util.ArrayList;

public interface CharacterDataAccess {
    ArrayList<Character> getAllCharacter(String pseudo, int number) throws DataException, DataAccessException;
    ArrayList<String> getAllCharactersInAGame(String pseudo, int number, String gameName) throws DataException,
                                                                                                    DataAccessException;
    int deleteACharacter(String pseudo, int number, String gameName, String characterName) throws DataAccessException,
                                                                                                            DataException;
    int insertACharacter(Character character, String pseudo, int number, String game, String server, String characterClass)
                                                                            throws DataException, DataAccessException;
    Character getOneCharacter(String pseudo, int number, String game, String server, String characterClass, String character)
            throws DataException, DataAccessException;
    ArrayList<String> getAllCharactersInAGameInServerWithCharacterClass(String pseudo, int number, String game, String server, String characterClass)
                                                                            throws DataException, DataAccessException;

    int modifyACharacter(Character character, String pseudo, int number, String game, String server, String characterClass)
            throws DataException, DataAccessException;

    ArrayList<DisplayCharacter> getAllInfosCharacters(String pseudoChoice, int numberChoice) throws DataException, DataAccessException;

    int getNbCharacters() throws DataException, DataAccessException;

    boolean notTheSameName(String pseudo, int number, String game, String server, String characterClass, String characterName) throws DataException, DataAccessException;
}