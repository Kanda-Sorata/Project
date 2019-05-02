package Controller;

import BusinessLogic.SpellBusinessLogic;
import Exception.DataAccessException;
import Exception.DataException;
import Model.SearchSpellList;

import java.util.ArrayList;

public class SpellController {
    private SpellBusinessLogic spellBusinessLogic;

    public SpellController(){
        spellBusinessLogic = new SpellBusinessLogic();
    }

    public ArrayList<SearchSpellList> getSearchSpellList(String pseudoChoice, int numberChoice)throws DataException, DataAccessException {
        return spellBusinessLogic.getSearchSpellList(pseudoChoice, numberChoice);
    }
}
