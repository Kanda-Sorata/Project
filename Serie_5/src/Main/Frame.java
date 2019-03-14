package Main;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame(JMenuBar menu){
        super("My beautiful menu!");

        // make the frame half the height and width
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        setSize(width/2+100, height/2+100);

        // here's the part where i center the jframe on screen
        setLocationRelativeTo(null);

        setJMenuBar(menu);

        setVisible(true);
    }
}
