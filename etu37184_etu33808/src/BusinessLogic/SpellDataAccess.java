package BusinessLogic;

import Model.SearchSpellList;

import java.util.ArrayList;

public interface SpellDataAccess {
    ArrayList<SearchSpellList> getSearchSpellList(String pseudoChoice, String numberchoice);
}
