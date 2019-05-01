package Controller;

import BusinessLogic.CharacterBusinessLogic;
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

    public ArrayList<Character> getAllCharacter(String pseudo, String number) throws AllCharacterException{
        return characterBusinessLogic.getAllCharacter(pseudo, number);
    }
}
