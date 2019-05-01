package Controller;
import BusinessLogic.*;
import Model.SearchSpellList;
import Exception.AllSpellsException;
import java.util.ArrayList;

public class SpellController {
    private SpellBusinessLogic spellBusinessLogic;

    public SpellController(){
        spellBusinessLogic = new SpellBusinessLogic();
    }

    public ArrayList<SearchSpellList> getSearchSpellList(String pseudoChoice, String numberChoice)throws AllSpellsException {
        return spellBusinessLogic.getSearchSpellList(pseudoChoice, numberChoice);
    }
}
