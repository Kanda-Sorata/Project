package BusinessLogic;

import Exception.DataAccessException;
import Exception.DataException;
import Model.SearchSpellList;

import java.util.ArrayList;

public interface SpellDataAccess {
    ArrayList<SearchSpellList> getSearchSpellList(String pseudoChoice, int numberChoice) throws DataException, DataAccessException;
}