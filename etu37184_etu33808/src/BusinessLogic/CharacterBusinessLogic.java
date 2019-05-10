package BusinessLogic;

import DataAccess.CharacterDBAccess;
import Exception.DataAccessException;
import Exception.DataException;
import Model.Character;
import Model.DisplayCharacter;

import java.util.ArrayList;
import java.util.regex.Pattern;

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
        if(isDeleteParametersValide(pseudo, number, gameName, characterName)) {
            return dao.deleteACharacter(pseudo, number, gameName, characterName);
        }else{
            throw new DataException(7);
        }
    }
    public int insertACharacter(Character character, String pseudo, int number, String game, String server, String characterClass) throws DataAccessException, DataException {
        if(isInsertParametersValide(character, pseudo, number, game, server, characterClass)) {
            return dao.insertACharacter(character, pseudo, number, game, server, characterClass);
        }else{
           throw new DataException(6);
        }
    }

    public int modifyACharacter(Character character, String pseudo, int number, String game, String server, String characterClass) throws DataAccessException, DataException {
        if(isInsertParametersValide(character, pseudo, number, game, server, characterClass)) {
            return dao.modifyACharacter(character, pseudo, number, game, server, characterClass);
        }else{
            throw new DataException(8);
        }
    }

    public Character getOneCharacter(String pseudo, int number, String game, String server, String characterClass, String character) throws DataException, DataAccessException {
        return dao.getOneCharacter(pseudo, number, game, server, characterClass, character);
    }

    private boolean isInsertParametersValide(Character character, String pseudo, int number, String game, String server, String characterClass){
        String noSelection = "No selection";
        return character != null && !character.getName().isEmpty() && Pattern.matches("^[a-zA-Z_-]{4,50}", character.getName())
                && character.getHealthPoints() >= Character.getMinHp()
                && character.getHealthPoints() <= Character.getMaxHp() && character.getCreationDate() != null
                && character.getStuffed() != null
                && (character.getDamagePerSecond() == null || (character.getDamagePerSecond() >= Character.getMinDmg()
                && character.getDamagePerSecond() <= Character.getMaxDmg())) && pseudo != null
                && !pseudo.equals(noSelection) && game != null
                && !game.equals(noSelection) && server != null && !server.equals(noSelection)
                && characterClass != null && !characterClass.equals(noSelection);
    }

    private boolean isDeleteParametersValide(String pseudo, int number, String gameName, String characterName){
        String noSelection = "No selection";
        return pseudo != null && !pseudo.equals(noSelection) && gameName != null && !gameName.equals(noSelection)
                && characterName != null && !characterName.equals(noSelection);
    }

    public ArrayList<String> getAllCharactersInAGameInServerWithCharacterClass(String pseudo, int number, String game, String server, String characterClass) throws DataException, DataAccessException{
        return dao.getAllCharactersInAGameInServerWithCharacterClass(pseudo, number, game, server, characterClass);
    }

    public ArrayList<DisplayCharacter> getAllInfosCharacters(String pseudoChoice, int numberChoice) throws DataException, DataAccessException {
        return dao.getAllInfosCharacters(pseudoChoice, numberChoice);
    }
}