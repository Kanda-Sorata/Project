package BusinessLogic;

import Exception.DataAccessException;
import Exception.DataException;

import java.util.ArrayList;

public interface CharacterClassDataAccess {
    ArrayList<String> getClassesInAGame(String game) throws DataException, DataAccessException;
    ArrayList<String> getAllClassesName(String pseudo, int number, String game, String server) throws
            DataException, DataAccessException;
}
