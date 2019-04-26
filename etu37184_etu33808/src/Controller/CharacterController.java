package Controller;

import BusinessLogic.*;
import DataAccess.CharacterDBAccess;
import Model.Character;
import Exception.*;
import java.util.ArrayList;

public class CharacterController {
    private CharacterBusinessLogic characterBusinessLogic;

    public CharacterController(CharacterBusinessLogic characterBusinessLogic){
        this.characterBusinessLogic = characterBusinessLogic;
    }

    public ArrayList<Character> getAllCharacter(String pseudo, String number) throws AllCharacterException{
        return characterBusinessLogic.getAllCharacter(pseudo, number);
    }
}
