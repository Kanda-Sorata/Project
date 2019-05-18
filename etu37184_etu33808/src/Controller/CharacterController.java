package Controller;

import BusinessLogic.CharacterBusinessLogic;
import Exception.DataAccessException;
import Exception.DataException;
import Exception.UniqueNameException;
import Model.Character;
import Model.DeleteCharacter;
import Model.DisplayCharacter;

import java.util.ArrayList;


public class CharacterController {
    private CharacterBusinessLogic characterBusinessLogic;

    public CharacterController(){
        characterBusinessLogic = new CharacterBusinessLogic();
    }

    public ArrayList<Character> getAllCharacter(String pseudo, int number) throws DataException, DataAccessException {
        return characterBusinessLogic.getAllCharacter(pseudo, number);
    }

    public ArrayList<DeleteCharacter> getAllCharactersInAGame(String pseudo, int number, String gameName) throws DataException, DataAccessException {
        return characterBusinessLogic.getAllCharactersInAGame(pseudo, number, gameName);
    }

    public int deleteACharacter(String pseudo, int number, String gameName, String characterName) throws DataAccessException, DataException{
        return characterBusinessLogic.deleteACharacter(pseudo, number, gameName, characterName);
    }

    public int insertACharacter(Character character, String pseudo, int number, String game, String server, String characterClass) throws DataAccessException, DataException, UniqueNameException {
        return characterBusinessLogic.insertACharacter(character, pseudo, number, game, server, characterClass);
    }

    public int modifyACharacter(Character character, String pseudo, int number, String game, String server, String characterClass, String oldName) throws DataAccessException, DataException {
        return characterBusinessLogic.modifyACharacter(character, pseudo, number, game, server, characterClass, oldName);
    }

    public Character getOneCharacter(String pseudo, int number, String game, String server, String characterClass, String character) throws DataException, DataAccessException {
        return characterBusinessLogic.getOneCharacter(pseudo, number, game, server, characterClass, character);
    }

    public ArrayList<String> getAllCharactersInAGameInServerWithCharacterClass(String pseudo, int number, String game, String server, String characterClass) throws DataException, DataAccessException{
        return characterBusinessLogic.getAllCharactersInAGameInServerWithCharacterClass(pseudo, number, game, server, characterClass);
    }

    public ArrayList<DisplayCharacter> getAllInfosCharacters(String pseudoChoice, int numberChoice) throws DataAccessException, DataException {
        return characterBusinessLogic.getAllInfosCharacters(pseudoChoice, numberChoice);
    }

    public int getNbCharacters() throws DataAccessException, DataException{
        return characterBusinessLogic.getNbCharacters();
    }

    public ArrayList<DisplayCharacter> getAllInfosCharactersFromAllPlayers() throws DataException, DataAccessException {
        return characterBusinessLogic.getAllInfosCharactersFromAllPlayers();
    }
}
