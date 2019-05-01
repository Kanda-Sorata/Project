package BusinessLogic;

import Exception.DataAccessException;
import Exception.DataException;

import java.util.ArrayList;

public interface CharacterClassDataAccess {
    ArrayList<String> getClassesInAGame(String game) throws DataException, DataAccessException;
}
