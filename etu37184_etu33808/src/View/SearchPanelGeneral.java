package View;

import Controller.GameController;
import Exception.AllCharacterException;
import Exception.AllGamesException;
import Exception.AllAccountException;
import Exception.NbAccountException;
import Model.Game;
import Model.SearchGameList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class SearchPanelGeneral extends JPanel {
    private SearchPanelGameList searchPanelGameList;
    private SearchPanelEffectsList searchPanelEffectsList;
    private SearchPanelSpellList searchPanelSpellList;
    private JTable table;
    private  JScrollPane scrollPane;
    private ArrayList<Game> games;
    private GameController gameController;

    public SearchPanelGeneral(int numberPanel){
        gameController = new GameController();
        setLayout(new BorderLayout());
        switch(numberPanel) {
            case 1:
                try {
                    searchPanelGameList = new SearchPanelGameList();
                    add(searchPanelGameList, BorderLayout.WEST);
                    ArrayList<SearchGameList> searchAllGamesListCharacter = getSearchAllGamesListCharacter(searchPanelGameList.getPseudoChoice(), searchPanelGameList.getNumberChoice(), searchPanelGameList.getCharacterNameChoice(), searchPanelGameList.getDateEnd());
                    AllGameFromCharacterModel model = new AllGameFromCharacterModel(searchAllGamesListCharacter);
                    table = new JTable(model);
                    scrollPane = new JScrollPane(table);
                    add(table, BorderLayout.CENTER);
                }catch (AllCharacterException allCharacterException){
                    JOptionPane.showMessageDialog(null, allCharacterException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }catch(AllGamesException allGameException){
                    JOptionPane.showMessageDialog(null, allGameException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NbAccountException nbAccountException){
                    JOptionPane.showMessageDialog(null, nbAccountException.getMessage(),  "Error", JOptionPane.ERROR_MESSAGE);;
                }catch(AllAccountException allAccountException){
                    JOptionPane.showMessageDialog(null, allAccountException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 2:
                searchPanelSpellList = new SearchPanelSpellList();
                add(searchPanelSpellList, BorderLayout.WEST);
                /*add model
                table = new JTable(model);*/
                scrollPane = new JScrollPane(table);
                add(table, BorderLayout.CENTER);
                break;
            case 3:
                searchPanelEffectsList = new SearchPanelEffectsList();
                add(searchPanelEffectsList, BorderLayout.WEST);
                /*add model
                table = new JTable(model);*/
                scrollPane = new JScrollPane(table);
                add(table, BorderLayout.CENTER);
                break;
        }
    }

    public ArrayList<SearchGameList> getSearchAllGamesListCharacter(String pseudo, String number, String character, GregorianCalendar dateEnd) throws AllGamesException {
        return gameController.getSearchAllGamesListCharacter(pseudo, number, character, dateEnd);
    }



}
