package BusinessLogic;

import Exception.DivideException;
import Model.TopOfClass;
import org.junit.Assert;

public class CharacterClassBusinessLogicTest {

    private CharacterClassBusinessLogic characterClassBusinessLogic;
    private TopOfClass topOfClass;
    private int nbCharactersTotal;

    @org.junit.Before
    public void setUp() throws Exception {
        characterClassBusinessLogic = new CharacterClassBusinessLogic();
        topOfClass = new TopOfClass(4, null, null);
        nbCharactersTotal = 18;
    }

    @org.junit.Test
    public void getPercent() throws DivideException {
        if (topOfClass == null) {
            throw new IllegalArgumentException("This method need a TopOfClass model differ from null!");
        } else {
            if (nbCharactersTotal <= 0) {
                throw new IllegalArgumentException("This method need a number of characters more than 0!");
            } else {
                double expected = (topOfClass.getNbCharacters() / (double) nbCharactersTotal) * 100;
                Assert.assertEquals(expected, characterClassBusinessLogic.getPercent(topOfClass, nbCharactersTotal), 0.001);
            }
        }
    }

    @org.junit.Test(expected = DivideException.class)
    public void divideException() throws DivideException {
        nbCharactersTotal = 0;
        characterClassBusinessLogic.getPercent(topOfClass, nbCharactersTotal);
    }
}