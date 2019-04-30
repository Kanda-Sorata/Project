package Controller;
import BusinessLogic.*;
import Model.SearchSpellList;

import java.util.ArrayList;

public class SpellController {
    private SpellBusinessLogic spellBusinessLogic;

    public SpellController(){
        spellBusinessLogic = new SpellBusinessLogic();
    }

    public ArrayList<SearchSpellList> getSearchSpellList(String pseudoChoice, String numberchoice){
        return spellBusinessLogic.getSearchSpellList(pseudoChoice, numberchoice);
    }
}
