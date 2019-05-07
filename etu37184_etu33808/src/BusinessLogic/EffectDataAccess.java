package BusinessLogic;

import Exception.DataAccessException;
import Exception.DataException;
import Model.SearchEffectList;

import java.util.ArrayList;

public interface EffectDataAccess {
    ArrayList<SearchEffectList> getSearchEffectList(String gameChoice, String classChoice) throws DataException, DataAccessException;
}
