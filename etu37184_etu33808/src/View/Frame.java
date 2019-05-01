package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame{
    private Container container;

    private JMenuBar menu;
    JMenu application;
    JMenuItem exit;
    JMenu search;
    JMenuItem listGamesFromCharacter;
    JMenuItem listSpellsCharacterFromPlayer;
    JMenuItem listEffectsCharacterClassFromGame;
    JMenu character;
    JMenuItem add;
    JMenuItem modify;
    JMenuItem delete;
    JMenuItem list;

    GamePanel gamePanel;
    SpellPanel spellPanel;
    ResultEffectPanel resultEffectPanel;
    FormPanel formPanel;
    DeletePanel deletePanel;
    ModifyPanel modifyPanel;
    DisplayPanel displayPanel;

    public Frame(){
        //Generale
        super("Account management");
        setFrame();

        //Contenu
        container = this.getContentPane();

        //Menu
        menu = getMenu();
        setJMenuBar(menu);

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

        setResizable(false);

        addWindowListener(new WindowAdapter() { //Fermer la fenetre
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    public JMenuBar getMenu(){
        JMenuBar menu = new JMenuBar();

        application = new JMenu("Application");
        search = new JMenu("Search");
        character = new JMenu("Character");
        //JMenu Help if we have the time

        SearchListener searchListener = new SearchListener();
        CharacterListener characterListener = new CharacterListener();

        //Appplication
        application.setMnemonic('a');
        exit = new JMenuItem("Exit");
        exit.addActionListener(new ExitListener());
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        application.add(exit);

        //Search
        listGamesFromCharacter = new JMenuItem("List of games from character");
        listGamesFromCharacter.addActionListener(searchListener);
        search.add(listGamesFromCharacter);

        listSpellsCharacterFromPlayer = new JMenuItem("List spells of character from player");
        listSpellsCharacterFromPlayer.addActionListener(searchListener);
        search.add(listSpellsCharacterFromPlayer);

        listEffectsCharacterClassFromGame = new JMenuItem("List effects of character class from a game");
        listEffectsCharacterClassFromGame.addActionListener(searchListener);
        search.add(listEffectsCharacterClassFromGame);

        //Character
        add = new JMenuItem("New");
        add.addActionListener(characterListener);
        character.add(add);
        modify = new JMenuItem("Modifiy");
        modify.addActionListener(characterListener);
        character.add(modify);
        delete = new JMenuItem("Delete");
        delete.addActionListener(characterListener);
        character.add(delete);
        list = new JMenuItem("Display");
        list.addActionListener(characterListener);
        character.add(list);

        //Menu
        menu.add(application);
        menu.add(search);
        menu.add(character);
        return menu;
    }

    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            System.exit(0);
        } //Add !connection.isClosed() => close the connection
    }

    private class SearchListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
          if(event.getSource() == listGamesFromCharacter){
              gamePanel = new GamePanel();
              container.add(gamePanel);
          }
          else{
              if(event.getSource() == listSpellsCharacterFromPlayer){
                  spellPanel = new SpellPanel();
                  container.add(spellPanel);
              }
              else{
                  resultEffectPanel = new ResultEffectPanel();
                  container.add(resultEffectPanel);
              }
          }

          setVisible(true); //Forced to repaint the panel
        }
    }

    public class CharacterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //Add JOPTINPANEdialogue JBomboBox pseudo & number if(non null utiliser pseudo et nuber
            container.removeAll();
            if(actionEvent.getSource() == add){
                formPanel = new FormPanel();
                //formPanel.setPlayer();
                container.add(formPanel);
            }
            else{
                if(actionEvent.getSource() == modify){
                    modifyPanel = new ModifyPanel();
                    //modifyPanel.setPlayer();
                    container.add(modifyPanel);
                }
                else{
                    if(actionEvent.getSource() == delete){
                        deletePanel = new DeletePanel();
                        //deletePanel.setPlayer();
                        container.add(deletePanel);
                    }
                    else{
                        displayPanel = new DisplayPanel();
                        //displayPanel.setPlayer();
                        container.add(displayPanel);
                    }
                }
            }
            setVisible(true);
        }
    }
}
