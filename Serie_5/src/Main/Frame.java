package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Frame extends JFrame {

    private Container container;

    public Frame(Menu menu){
        super("My beautiful menu!");

        // make the frame half the height and width
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        setSize(width/2+100, height/2+100);

        // here's the part where i center the jframe on screen
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() { //Fermer la fenetre
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        container = this.getContentPane();

        menu.setParentWindow(this);

        setJMenuBar(menu.getMenuBar());

        setVisible(true);
    }

    public Container getContainer(){
        return  container;
    }



}
