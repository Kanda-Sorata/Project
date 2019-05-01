package BusinessLogic;

import Model.SearchSpellList;

import java.util.ArrayList;
import Exception.*;

public interface SpellDataAccess {
    ArrayList<SearchSpellList> getSearchSpellList(String pseudoChoice, String numberChoice) throws AllSpellsException;
}
