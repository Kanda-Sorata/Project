package View.SearchPanel;

import Exception.DataAccessException;
import Exception.DataException;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private  SearchPanelGame searchPanelGame;
    private  ResultGamePanel resultGamePanel;

    public GamePanel() throws DataAccessException, DataException {
        //Add properties
        setLayout(new GridLayout(1, 2, 5, 15));


        //Add components
        resultGamePanel = new ResultGamePanel();
        searchPanelGame = new SearchPanelGame(resultGamePanel);

        add(searchPanelGame);
        add(resultGamePanel);
    }
}
