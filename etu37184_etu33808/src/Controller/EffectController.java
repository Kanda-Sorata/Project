package Controller;

import Model.SearchEffectList;
import Exception.DataAccessException;
import Exception.DataException;
import BusinessLogic.EffectBusinessLogic;

import java.util.ArrayList;

public class EffectController {
    private EffectBusinessLogic effectBusinessLogic;

    public EffectController(){
        this.effectBusinessLogic = new EffectBusinessLogic();
    }

    public ArrayList<SearchEffectList> getSearchEffectList(String gameChoice, String classChoice) throws DataException, DataAccessException {
        return effectBusinessLogic.getSearchEffectList(gameChoice, classChoice);
    }
}
