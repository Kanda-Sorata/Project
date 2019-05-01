package BusinessLogic;

import Model.SearchSpellList;
import DataAccess.SpellDBAccess;
import java.util.ArrayList;
import Exception.AllSpellsException;

public class SpellBusinessLogic {
    private SpellDataAccess dao;

    public SpellBusinessLogic(){
        setDAO(new SpellDBAccess());
    }

    public void setDAO(SpellDataAccess dao){
        this.dao = dao;
    }
    public ArrayList<SearchSpellList> getSearchSpellList(String pseudoChoice, String numberChoice) throws AllSpellsException {
        return  dao.getSearchSpellList(pseudoChoice, numberChoice);
    }
}
