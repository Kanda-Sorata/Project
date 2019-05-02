package View.SearchPanel;

import Controller.GameController;
import Exception.DataAccessException;
import Exception.DataException;
import Model.SearchGameList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class GamePanel extends JPanel {
    private SearchPanelGame searchPanelGame;
    private JTable table;
    private JScrollPane scrollPane;
    private GameController gameController;
    private ArrayList<SearchGameList> searchAllGamesListCharacter;
    private UtilitiesPanelMethode utilitiesPanelMethode;

    public GamePanel() {
        utilitiesPanelMethode = new UtilitiesPanelMethode();
        gameController = new GameController();
        setLayout(new FlowLayout());
        searchPanelGame = new SearchPanelGame(this);
        add(searchPanelGame);

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
