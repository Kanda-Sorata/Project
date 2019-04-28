package BusinessLogic;

import Exception.AllCharacterException;
import Model.Character;

import java.util.ArrayList;

public interface CharacterDataAccess {
    ArrayList<Character> getAllCharacter(String pseudo, String number) throws AllCharacterException;
}
