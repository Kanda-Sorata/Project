package View.SearchPanel;

import Controller.GameController;
import Exception.ConflictDataException;
import Model.SearchGameList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class GamePanel extends JPanel {
    private SearchPanelGame searchPanelGame;
    private JTable table;
    private  JScrollPane scrollPane;
    private GameController gameController;
    private  ArrayList<SearchGameList> searchAllGamesListCharacter;
    private UtilitiesPanelMethode utilitiesPanelMethode;

    public GamePanel() {
        utilitiesPanelMethode = new UtilitiesPanelMethode();
        gameController = new GameController();
        setLayout(new FlowLayout());
        try {
            searchPanelGame = new SearchPanelGame(this);
            add(searchPanelGame);

            table = utilitiesPanelMethode.getJTableModelBlank();
            scrollPane = new JScrollPane(table);
            add(table);
        } catch (ConflictDataException conflictDataException) {
            JOptionPane.showMessageDialog(null, conflictDataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<SearchGameList> getSearchAllGamesListCharacter(String pseudo, String number, String character, GregorianCalendar dateEnd) throws ConflictDataException {
        return gameController.getSearchAllGamesListCharacter(pseudo, number, character, dateEnd);
    }

    public void setJtable() throws ConflictDataException {
        searchAllGamesListCharacter = getSearchAllGamesListCharacter(searchPanelGame.getPseudoChoice(), searchPanelGame.getNumberChoice(), searchPanelGame.getCharacterNameChoice(), searchPanelGame.getDateEnd());
        AllGamesFromCharacterModel model = new AllGamesFromCharacterModel(searchAllGamesListCharacter);
        remove(table);
        table = new JTable(model);
        add(table);
        revalidate();
        repaint();
    }
}
