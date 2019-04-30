package BusinessLogic;

import Model.SearchSpellList;
import DataAccess.SpellDBAccess;
import java.util.ArrayList;

public class SpellBusinessLogic {
    private SpellDataAccess dao;

    public SpellBusinessLogic(){
        setDAO(new SpellDBAccess());
    }

    public void setDAO(SpellDataAccess dao){
        this.dao = dao;
    }
    public ArrayList<SearchSpellList> getSearchSpellList(String pseudoChoice, String numberchoice){
        return  dao.getSearchSpellList(pseudoChoice, numberchoice);
    }
}
