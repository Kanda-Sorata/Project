package BusinessLogic;

import Exception.ConflictDataException;
import Exception.DataAccessException;

import java.util.ArrayList;

public interface CharacterClassDataAccess {
    ArrayList<String> getClassesInAGame(String game) throws ConflictDataException, DataAccessException;
}
