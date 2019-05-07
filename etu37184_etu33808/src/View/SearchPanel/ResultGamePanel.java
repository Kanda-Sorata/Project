package View.SearchPanel;

import Controller.GameController;
import Exception.DataAccessException;
import Exception.DataException;
import Model.SearchGameList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ResultGamePanel extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private UtilitiesPanelMethode utilitiesPanelMethode;
    private ArrayList<SearchGameList> searchAllGamesListCharacter;
    private GameController gameController;

    public ResultGamePanel() {
        utilitiesPanelMethode = new UtilitiesPanelMethode();
        setLayout(new FlowLayout());
        gameController = new GameController();

        table = utilitiesPanelMethode.getJTableModelBlank();
        scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public ArrayList<SearchGameList> getSearchAllGamesListCharacter(String pseudo, int number, String character, GregorianCalendar dateEnd) throws DataException, DataAccessException {
        return gameController.getSearchAllGamesListCharacter(pseudo, number, character, dateEnd);
    }

    public void setJtable(String pseudoChoice, int numberchoice, String character, GregorianCalendar dateEnd) throws DataException, DataAccessException {
        searchAllGamesListCharacter = getSearchAllGamesListCharacter(pseudoChoice, numberchoice, character, dateEnd);
        AllGamesFromCharacterModel model = new AllGamesFromCharacterModel(searchAllGamesListCharacter);
        remove(scrollPane);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        revalidate();
        repaint();
    }
}
