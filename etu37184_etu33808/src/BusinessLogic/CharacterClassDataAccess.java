package BusinessLogic;

import Exception.DataAccessException;
import Exception.DataException;
import Model.TopOfClass;

import java.util.ArrayList;

public interface CharacterClassDataAccess {
    ArrayList<String> getClassesInAGame(String game) throws DataException, DataAccessException;
    ArrayList<String> getAllCharactersClassName(String pseudo, int number, String game) throws
                                                                                    DataException, DataAccessException;
    ArrayList<TopOfClass> getAllCharacterClassOrderClass() throws DataAccessException, DataException;
}
