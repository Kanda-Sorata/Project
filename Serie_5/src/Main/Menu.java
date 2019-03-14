package Main;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Menu  {
    private JMenuBar menu;

    private JMenu applicationMenu;
    private JMenuItem exit;

    private JMenu etuMenu;
    private JMenuItem inscriptionItem;

    private JMenu infoMenu;
    private JMenuItem iesnItem;
    private JMenuItem helpItem;

    public Menu(){
        menu = new JMenuBar();

        applicationMenu = new JMenu("Applications");
        applicationMenu.setMnemonic('a');
        exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(InputEvent.CTRL_MASK, KeyEvent.VK_Q));
        applicationMenu.add(exit);


        etuMenu = new JMenu("Etudiant");
        etuMenu.setMnemonic('e');
        inscriptionItem = new JMenuItem("Inscription");


        infoMenu = new JMenu("Infos");

        iesnItem = new JMenuItem("IESN");
        helpItem = new JMenuItem("Aide");
        infoMenu.add(iesnItem);
        infoMenu.add(helpItem);
    }

    private class ExitListener implements  Action

    public JMenuBar getMenuBar(){
        return menu;
    }
}
