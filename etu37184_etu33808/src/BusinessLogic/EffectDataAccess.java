package BusinessLogic;

import Model.SearchEffectList;
import Exception.DataException;
import Exception.DataAccessException;

import java.util.ArrayList;

public interface EffectDataAccess {
    ArrayList<SearchEffectList> getSearchEffectList(String gameChoice, String classChoice) throws DataException, DataAccessException;
}
