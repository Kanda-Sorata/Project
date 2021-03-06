package BusinessLogic;

import DataAccess.CharacterDBAccess;
import Exception.DataAccessException;
import Exception.DataException;
import Exception.UniqueNameException;
import Model.Character;
import Model.DeleteCharacter;
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

    public ArrayList<DeleteCharacter> getAllCharactersInAGame(String pseudo, int number, String gameName) throws DataException, DataAccessException {
        return dao.getAllCharactersInAGame(pseudo, number, gameName);
    }

    public int deleteACharacter(String pseudo, int number, String gameName, String characterName) throws DataAccessException, DataException{
        if (!isDeleteParametersValid(pseudo, number, gameName, characterName)) {
            throw new DataException(7);
        } else {
            return dao.deleteACharacter(pseudo, number, gameName, characterName);
        }
    }

    public int insertACharacter(Character character, String pseudo, int number, String game, String server, String characterClass) throws DataAccessException, DataException, UniqueNameException {
        if (!isParametersValid(character, pseudo, number, game, server, characterClass)) {
            throw new DataException(6);
        } else {
            if (isSameName(pseudo, number, game, server, character.getName())) {
                throw new UniqueNameException(character.getName());
            } else {
                return dao.insertACharacter(character, pseudo, number, game, server, characterClass);
            }
        }
    }

    public int modifyACharacter(Character character, String pseudo, int number, String game, String server, String characterClass, String oldName) throws DataAccessException, DataException {
        if (!isParametersValid(character, pseudo, number, game, server, characterClass, oldName)) {
            throw new DataException(8);
        } else {
            return dao.modifyACharacter(character, pseudo, number, game, server, characterClass, oldName);
        }
    }

    public Character getOneCharacter(String pseudo, int number, String game, String server, String characterClass, String character) throws DataException, DataAccessException {
        return dao.getOneCharacter(pseudo, number, game, server, characterClass, character);
    }

    private boolean isParametersValid(Character character, String pseudo, int number, String game, String server, String characterClass, String oldName) {
        return isParametersValid(character, pseudo, number, game, server, characterClass) && Pattern.matches("^[a-zA-Z_-]{4,50}", oldName)
                && !Pattern.matches("(.)\\1{2,}", oldName);
    }

    private boolean isParametersValid(Character character, String pseudo, int number, String game, String server, String characterClass) {
        String noSelection = "No selection";
        return character != null && !character.getName().isEmpty() && Pattern.matches("^[a-zA-Z_-]{4,50}", character.getName())
                && !Pattern.matches("(.)\\1{2,}", character.getName())
                && character.getHealthPoints() >= Character.getMinHp()
                && character.getHealthPoints() <= Character.getMaxHp() && character.getCreationDate() != null
                && character.getStuffed() != null
                && (character.getDamagePerSecond() == null || (character.getDamagePerSecond() >= Character.getMinDmg()
                && character.getDamagePerSecond() <= Character.getMaxDmg())) && pseudo != null
                && !pseudo.equals(noSelection) && game != null
                && !game.equals(noSelection) && server != null && !server.equals(noSelection)
                && characterClass != null && !characterClass.equals(noSelection);
    }

    private boolean isSameName(String pseudo, int number, String game, String server, String characterName) throws DataException, DataAccessException {
        String character = dao.getOneCharacterToCompare(pseudo, number, game, server, characterName);
        return character != null;
    }

    private boolean isDeleteParametersValid(String pseudo, int number, String gameName, String characterName) {
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

    public int getNbCharacters() throws DataException, DataAccessException{
        return dao.getNbCharacters();
    }

    public ArrayList<DisplayCharacter> getAllInfosCharactersFromAllPlayers() throws DataException, DataAccessException {
        return dao.getAllInfosCharactersFromAllPlayers();
    }
}