package BusinessLogic;

import Exception.ConflictDataException;
import Exception.DataAccessException;
import Model.SearchSpellList;

import java.util.ArrayList;

public interface SpellDataAccess {
    ArrayList<SearchSpellList> getSearchSpellList(String pseudoChoice, String numberChoice) throws ConflictDataException, DataAccessException;
}