package BusinessLogic;

import Exception.AllCommonException;

import java.util.ArrayList;

public interface CharacterClassDataAccess {
    ArrayList<String> getClassesInAGame(String game) throws AllCommonException;
}
