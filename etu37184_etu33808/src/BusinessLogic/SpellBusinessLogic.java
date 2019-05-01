package BusinessLogic;

import DataAccess.SpellDBAccess;
import Model.SearchSpellList;

import java.util.ArrayList;

public class SpellBusinessLogic {
    private SpellDataAccess dao;

    public SpellBusinessLogic(){
        setDAO(new SpellDBAccess());
    }

    public void setDAO(SpellDataAccess dao){
        this.dao = dao;
    }
    public ArrayList<SearchSpellList> getSearchSpellList(String pseudoChoice, String numberChoice) throws AllCommonException {
        return  dao.getSearchSpellList(pseudoChoice, numberChoice);
    }
}
