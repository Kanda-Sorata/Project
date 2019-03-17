package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame {

    private Container container;
    private JMenuBar menu;

    public Frame(){
        //Generale
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

        //Contenu

        container = this.getContentPane();


        //Création menu

        menu = new JMenuBar();

        JMenu applicationMenu;
        JMenuItem exit;

        JMenu etuMenu;
        JMenuItem registerItem;

        JMenu infoMenu;
        JMenuItem iesnItem;
        JMenuItem helpItem;

        applicationMenu = new JMenu("Applications");
        applicationMenu.setMnemonic('a');
        exit = new JMenuItem("Exit");
        exit.addActionListener(new ExitListener());
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        applicationMenu.add(exit);


        etuMenu = new JMenu("Etudiant");
        etuMenu.setMnemonic('e');
        registerItem = new JMenuItem("Inscription");
        registerItem.addActionListener(new RegisterListener());

        etuMenu.add(registerItem);

        infoMenu = new JMenu("Infos");
        infoMenu.setMnemonic('i');

        iesnItem = new JMenuItem("IESN");
        iesnItem.addActionListener(new IesnListener());
        helpItem = new JMenuItem("Aide");
        helpItem.addActionListener(new HelpListener());
        infoMenu.add(iesnItem);
        infoMenu.add(helpItem);

        menu.add(applicationMenu);
        menu.add(etuMenu);
        menu.add(infoMenu);

        setJMenuBar(menu);

        setVisible(true);
    }

    private class RegisterListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            container.removeAll();
            container.add(new RegisterForm());
            container.repaint();
            setVisible(true);
        }
    }

    private class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }

    private class IesnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            container.removeAll();
            container.add(new InfoIESN());
            container.repaint();
            setVisible(true);
        }
    }

    private class HelpListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new FrameHelp();
        }
    }

     private class InfoIESN extends JPanel {
         private JLabel label;

         public InfoIESN() {
             setLayout(new FlowLayout());

             label = new JLabel("<html><p>Rue Joseph Calozet 19<p>5000 Namur</p>" +
                     "<p>Tél : +32 (0)81 46 86 10</p><p>Fax: +32 (0)81 73 32 17</p>" +
                     "<p><a href='info.iesn@henallux.be'>Henallux IESN</a></p></html>");
             this.add(label);
         }

     }

}
