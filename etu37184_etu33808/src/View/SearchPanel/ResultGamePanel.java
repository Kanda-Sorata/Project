package View.SearchPanel;

import Controller.GameController;
import Exception.DataAccessException;
import Exception.DataException;
import Model.SearchGameList;
import View.UtilitiesPanelMethod;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ResultGamePanel extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private UtilitiesPanelMethod utilitiesPanelMethod;
    private ArrayList<SearchGameList> searchAllGamesListCharacter;
    private GameController gameController;

    public ResultGamePanel() {
        //Add properties
        setLayout(new FlowLayout());

        //Init
        utilitiesPanelMethod = new UtilitiesPanelMethod();
        gameController = new GameController();

        //Add components
        table = utilitiesPanelMethod.getJTableModelBlank();
        scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public ArrayList<SearchGameList> getSearchAllGamesListCharacter(String pseudo, int number, String character, GregorianCalendar dateEnd) throws DataException, DataAccessException {
        return gameController.getSearchAllGamesListCharacter(pseudo, number, character, dateEnd);
    }

    public void setJTable(String pseudoChoice, int numberChoice, String character, GregorianCalendar dateEnd) throws DataException, DataAccessException {
        searchAllGamesListCharacter = getSearchAllGamesListCharacter(pseudoChoice, numberChoice, character, dateEnd);
        AllGamesFromCharacterModel model = new AllGamesFromCharacterModel(searchAllGamesListCharacter);
        remove(scrollPane);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        revalidate();
        repaint();
    }
}
