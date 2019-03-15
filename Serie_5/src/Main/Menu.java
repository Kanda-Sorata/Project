package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Menu extends JPanel{
    private JMenuBar menu;

    private JMenu applicationMenu;
    private JMenuItem exit;

    private JMenu etuMenu;
    private JMenuItem inscriptionItem;

    private JMenu infoMenu;
    private JMenuItem iesnItem;
    private JMenuItem helpItem;

    private InfoIESN infoIesn;

    private Frame parentWindow;

    public Menu(InfoIESN infoIesn){
        menu = new JMenuBar();

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

        this.infoIesn = infoIesn;
    }

    private class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }

    private class IesnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            parentWindow.getContainer().removeAll();
            parentWindow.getContainer().add(infoIesn);
            parentWindow.getContainer().repaint();
        }
    }

    private class Aide implements ActionListener{
        public void actionPerformed(ActionEvent e){
            
        }
    }

    public JMenuBar getMenuBar(){
        return menu;
    }

    public void setParentWindow(Frame parentWindow){
        this.parentWindow = parentWindow;
    }
}
