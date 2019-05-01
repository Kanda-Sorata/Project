package Controller;

import BusinessLogic.CharacterBusinessLogic;
import Exception.DataAccessException;
import Exception.DataException;
import Model.Character;

import java.util.ArrayList;

public class CharacterController {
    private CharacterBusinessLogic characterBusinessLogic;

    public CharacterController(){
        characterBusinessLogic = new CharacterBusinessLogic();
    }

    public CharacterController(CharacterBusinessLogic characterBusinessLogic){
        this.characterBusinessLogic = characterBusinessLogic;
    }

    public ArrayList<Character> getAllCharacter(String pseudo, String number) throws DataException, DataAccessException {
        return characterBusinessLogic.getAllCharacter(pseudo, number);
    }
}
