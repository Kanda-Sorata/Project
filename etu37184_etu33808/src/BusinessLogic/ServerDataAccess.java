package BusinessLogic;

import Exception.DataAccessException;
import Exception.DataException;

import java.util.ArrayList;

public interface ServerDataAccess {
    ArrayList<String> getAllServersName(String pseudoChoice, int numberChoice, String game) throws DataException, DataAccessException;
}
