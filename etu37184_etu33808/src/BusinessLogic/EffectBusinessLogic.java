package BusinessLogic;

import DataAccess.EffectDBAccess;
import Exception.DataAccessException;
import Exception.DataException;
import Model.SearchEffectList;

import java.util.ArrayList;

public class EffectBusinessLogic implements EffectDataAccess{

    private EffectDataAccess dao;

    public EffectBusinessLogic(){
        setDAO(new EffectDBAccess());
    }

    public void setDAO(EffectDataAccess dao){
        this.dao = dao;
    }

    public ArrayList<SearchEffectList> getSearchEffectList(String gameChoice, String classChoice) throws DataException, DataAccessException {
        return dao.getSearchEffectList(gameChoice, classChoice);
    }
}
