package View;

import Controller.GameController;

import Exception.*;
import Model.SearchGameList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class GamePanel extends JPanel {
    private SearchPanelGameList searchPanelGameList;
    private JTable table;
    private  JScrollPane scrollPane;
    private GameController gameController;
    private JButton validation;
    private ButtonListener buttonListener;
    private  ArrayList<SearchGameList> searchAllGamesListCharacter;

    public GamePanel() {
        gameController = new GameController();
        setLayout(new FlowLayout());
        try {
            searchPanelGameList = new SearchPanelGameList();
            add(searchPanelGameList);

            table = new JTable();
            scrollPane = new JScrollPane(table);
            add(table);

            validation = new JButton("Validation");
            buttonListener = new ButtonListener();
            validation.addActionListener(buttonListener);
            add(validation);
        } catch (NbAccountException nbAccountException) {
            JOptionPane.showMessageDialog(null, nbAccountException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (AllAccountException allAccountException) {
            JOptionPane.showMessageDialog(null, allAccountException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (AllCharacterException allCharacterException) {
            JOptionPane.showMessageDialog(null, allCharacterException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<SearchGameList> getSearchAllGamesListCharacter(String pseudo, String number, String character, GregorianCalendar dateEnd) throws AllGamesException {
        return gameController.getSearchAllGamesListCharacter(pseudo, number, character, dateEnd);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if(searchPanelGameList.getCharacterNameChoice() != null) {
                    setJtable();
                }
            }catch(AllGamesException allGameException){
                JOptionPane.showMessageDialog(null, allGameException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void setJtable() throws AllGamesException{
        searchAllGamesListCharacter = getSearchAllGamesListCharacter(searchPanelGameList.getPseudoChoice(), searchPanelGameList.getNumberChoice(), searchPanelGameList.getCharacterNameChoice(), searchPanelGameList.getDateEnd());
        AllGameFromCharacterModel model = new AllGameFromCharacterModel(searchAllGamesListCharacter);
        table.setModel(model);
    }
}
