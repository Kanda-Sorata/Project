package View.SearchPanel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private  SearchPanelGame searchPanelGame;
    private  ResultGamePanel resultGamePanel;

    public GamePanel(Frame frame) {
        //Add properties
        setLayout(new GridLayout(1, 2, 5, 15));

        frame.setTitle("");
        frame.setTitle(frame.getTitle() + " - Search for game(s)");

        //Add components
        resultGamePanel = new ResultGamePanel();
        searchPanelGame = new SearchPanelGame(resultGamePanel);

        add(searchPanelGame);
        add(resultGamePanel);
    }
}
