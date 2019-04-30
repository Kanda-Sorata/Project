package View;

import Controller.GameController;

import Exception.*;
import Model.SearchGameList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class GamePanel extends JPanel {
    private SearchPanelGameList searchPanelGameList;
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
            searchPanelGameList = new SearchPanelGameList(this);
            add(searchPanelGameList);

            table = utilitiesPanelMethode.getJTableModelBlank();
            scrollPane = new JScrollPane(table);
            add(table);
        } catch (NbAccountException nbAccountException) {
            JOptionPane.showMessageDialog(null, nbAccountException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (AllAccountException allAccountException) {
            JOptionPane.showMessageDialog(null, allAccountException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<SearchGameList> getSearchAllGamesListCharacter(String pseudo, String number, String character, GregorianCalendar dateEnd) throws AllGamesException {
        return gameController.getSearchAllGamesListCharacter(pseudo, number, character, dateEnd);
    }

    public void setJtable() throws AllGamesException{
        searchAllGamesListCharacter = getSearchAllGamesListCharacter(searchPanelGameList.getPseudoChoice(), searchPanelGameList.getNumberChoice(), searchPanelGameList.getCharacterNameChoice(), searchPanelGameList.getDateEnd());
        AllGamesFromCharacterModel model = new AllGamesFromCharacterModel(searchAllGamesListCharacter);
        table.setModel(model);
    }
}
