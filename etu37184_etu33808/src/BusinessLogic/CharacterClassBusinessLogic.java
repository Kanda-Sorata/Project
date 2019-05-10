package BusinessLogic;

import DataAccess.CharacterClassDBAccess;
import Exception.DataAccessException;
import Exception.DataException;
import Exception.DivideException;
import Model.TopOfClass;

import java.util.ArrayList;

public class CharacterClassBusinessLogic {
    CharacterClassDataAccess dao;

    public CharacterClassBusinessLogic(){setDAO(new CharacterClassDBAccess());}

    public void setDAO(CharacterClassDBAccess characterClassDBAccess){ dao = characterClassDBAccess;}

    public ArrayList<String> getClassesInAGame(String game) throws DataException, DataAccessException {
        return dao.getClassesInAGame(game);
    }
    public ArrayList<String> getAllCharactersClassName(String pseudo, int number, String game) throws
            DataException, DataAccessException{
        return dao.getAllCharactersClassName(pseudo, number, game);
    }

    public ArrayList<TopOfClass> getAllCharacterClassOrderClass() throws DataAccessException, DataException, DivideException {
        ArrayList<TopOfClass> topOfClasses = dao.getAllCharacterClassOrderClass();
        int nbCharactersTotal = 0;

        for (TopOfClass topOfClass : topOfClasses) {
            nbCharactersTotal += topOfClass.getNbCharacters();
        }

        for (TopOfClass topOfClass : topOfClasses) {
            topOfClass.setPercent(getPercent(topOfClass, nbCharactersTotal));
        }
        return topOfClasses;
    }

    public double getPercent(TopOfClass topOfClass, Integer nbCharactersTotal) throws DivideException {
        if (nbCharactersTotal <= 0) throw new DivideException(topOfClass.getNbCharacters(), nbCharactersTotal);
        return (topOfClass.getNbCharacters() / (double) nbCharactersTotal) * 100;
    }
}