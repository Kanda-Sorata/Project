package BusinessLogic;

import DataAccess.CharacterDBAccess;
import Exception.DataAccessException;
import Exception.DataException;
import Model.Character;

import java.util.ArrayList;

public class CharacterBusinessLogic {
    CharacterDataAccess dao;

    public CharacterBusinessLogic() {
        setDao(new CharacterDBAccess());
    }

    public void setDao(CharacterDBAccess dao) {
        this.dao = dao;
    }

    public ArrayList<Character> getAllCharacter(String pseudo, int number) throws DataException, DataAccessException {
        return dao.getAllCharacter(pseudo, number);
    }

    public ArrayList<String> getAllCharactersInAGame(String pseudo, int number, String gameName) throws DataException, DataAccessException{
        return dao.getAllCharactersInAGame(pseudo, number, gameName);
    }

    public int deleteACharacter(String pseudo, int number, String gameName, String characterName) throws DataAccessException, DataException{
        return dao.deleteACharacter(pseudo, number, gameName, characterName);
    }
    public int insertACharacter(Character character, String pseudo, int number, String game, String server, String characterClass) throws DataAccessException, DataException {
        return dao.insertACharacter(character, pseudo, number, game, server, characterClass);
    }
}