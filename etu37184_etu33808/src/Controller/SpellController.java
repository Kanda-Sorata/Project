package Controller;

import BusinessLogic.SpellBusinessLogic;
import Exception.ConflictDataException;
import Model.SearchSpellList;

import java.util.ArrayList;

public class SpellController {
    private SpellBusinessLogic spellBusinessLogic;

    public SpellController(){
        spellBusinessLogic = new SpellBusinessLogic();
    }

    public ArrayList<SearchSpellList> getSearchSpellList(String pseudoChoice, String numberChoice)throws ConflictDataException {
        return spellBusinessLogic.getSearchSpellList(pseudoChoice, numberChoice);
    }
}
