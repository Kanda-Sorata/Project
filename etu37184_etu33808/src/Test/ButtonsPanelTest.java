package Test;

import View.CharacterPanel.ButtonsPanel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ButtonsPanelTest {
    private ArrayList<String> values;
    private ButtonsPanel buttonsPanel;

    @Before
    public void setUp() throws Exception {
        values = new ArrayList<>();
        values.add("azertyuiopqsdfghjklmwxcvbnazertyuiopqsdfghjklmwxcv");
        values.add("Test_");
        values.add("Okay_");
        values.add("Test_deux-");
        values.add("---");
        values.add("   ");
        values.add("___");

        buttonsPanel = new ButtonsPanel();
    }

    @Test
    public void isNameValidPattern() {
        for (int i = 0; i < 3; i++) {
            Assert.assertTrue("OK! : " + i, Pattern.matches("^[-_a-zA-Z]{4,50}", values.get(i)));
        }

        for (int i = 4; i < values.size(); i++) {
            Assert.assertTrue("KO! : " + i, Pattern.matches("(.)\\1{2,}", values.get(i)));

        }
    }
}