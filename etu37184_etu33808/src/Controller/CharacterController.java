package Controller;

import BusinessLogic.CharacterBusinessLogic;
import Exception.DataAccessException;
import Exception.DataException;
import Model.Character;

import javax.xml.crypto.Data;
import java.util.ArrayList;


public class CharacterController {
    private CharacterBusinessLogic characterBusinessLogic;

    public CharacterController(){
        characterBusinessLogic = new CharacterBusinessLogic();
    }

    public CharacterController(CharacterBusinessLogic characterBusinessLogic){
        this.characterBusinessLogic = characterBusinessLogic;
    }

    public ArrayList<Character> getAllCharacter(String pseudo, int number) throws DataException, DataAccessException {
        return characterBusinessLogic.getAllCharacter(pseudo, number);
    }

    public ArrayList<String> getAllCharactersInAGame(String pseudo, int number, String gameName) throws DataException, DataAccessException{
        return characterBusinessLogic.getAllCharactersInAGame(pseudo, number, gameName);
    }

    public int deleteACharacter(String pseudo, int number, String gameName, String characterName) throws DataAccessException, DataException{
        return characterBusinessLogic.deleteACharacter(pseudo, number, gameName, characterName);
    }

    public int insertACharacter(Character character, String pseudo, int number, String game, String server, String characterClass) throws DataAccessException, DataException {
        return characterBusinessLogic.insertACharacter(character, pseudo, number, game, server, characterClass);
    }

    public Character getOneCharacter(String pseudo, int number, String game, String server, String characterClass) throws DataException, DataAccessException {
        return characterBusinessLogic.getOneCharacter(pseudo, number, game, server, characterClass);
    }
}
