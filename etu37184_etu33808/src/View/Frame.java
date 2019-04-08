package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame{
    private Container container;
    private JMenuBar menu;

    public Frame(){
        //Generale
        super("Account management");
        setFrame();

        //Contenu
        container = this.getContentPane();

        //Menu
        menu = getMenu();
        setJMenuBar(menu);

        //Panel


        setVisible(true);
    }

    public void setFrame(){
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
    }

    public JMenuBar getMenu(){
        JMenuBar menu = new JMenuBar();

        JMenu application = new JMenu("Applciation");
        JMenu search = new JMenu("Search");
        JMenu character = new JMenu("Character");
        //JMenu Helph; if we have the time

        //Appplication
        application.setMnemonic('a');
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ExitListener());
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        application.add(exit);

        //Search
        //TODO
        //Character

        menu.add(application);
        menu.add(search);
        menu.add(character);
        return menu;
    }

    private class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
}
