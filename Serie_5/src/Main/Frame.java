package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame {

    private Container container;
    private JMenuBar menu;

    public Frame(){
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


        //Création menu

        menu = new JMenuBar();

        JMenu applicationMenu;
        JMenuItem exit;

        JMenu etuMenu;
        JMenuItem inscriptionItem;

        JMenu infoMenu;
        JMenuItem iesnItem;
        JMenuItem helpItem;

        applicationMenu = new JMenu("Applications");
        applicationMenu.setMnemonic('a');
        exit = new JMenuItem("Exit");
        exit.addActionListener(new ExitListener());
        exit.setAccelerator(KeyStroke.getKeyStroke(InputEvent.CTRL_MASK, KeyEvent.VK_Q));
        applicationMenu.add(exit);


        etuMenu = new JMenu("Etudiant");
        etuMenu.setMnemonic('e');
        inscriptionItem = new JMenuItem("Inscription");

        etuMenu.add(inscriptionItem);

        infoMenu = new JMenu("Infos");
        infoMenu.setMnemonic('i');

        iesnItem = new JMenuItem("IESN");
        iesnItem.addActionListener(new IesnListener());
        helpItem = new JMenuItem("Aide");
        infoMenu.add(iesnItem);
        infoMenu.add(helpItem);

        menu.add(applicationMenu);
        menu.add(etuMenu);
        menu.add(infoMenu);

        setJMenuBar(menu);

        setVisible(true);




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

        private class Aide implements ActionListener{
            public void actionPerformed(ActionEvent e){

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
