package View;

import Controller.SingletonController;
import Exception.DataAccessException;
import Model.Character;
import View.CharacterPanel.DeletePanel;
import View.CharacterPanel.DisplayPanel;
import View.CharacterPanel.ModifyPanel;
import View.CharacterPanel.NewPanel;
import View.SearchPanel.EffectPanel;
import View.SearchPanel.GamePanel;
import View.SearchPanel.SpellPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Frame extends JFrame{
    private Container container;

    private JMenuBar menu;
    private JMenu application;
    private JMenuItem home;
    private JMenuItem topOfClass;
    private JMenuItem exit;
    private JMenu search;
    private JMenuItem listGamesFromCharacter;
    private JMenuItem listSpellsCharacterFromPlayer;
    private JMenuItem listEffectsCharacterClassFromGame;
    private JMenu character;
    private JMenuItem add;
    private JMenuItem modify;
    private JMenuItem delete;
    private JMenuItem list;

    private GamePanel gamePanel;
    private SpellPanel spellPanel;
    private EffectPanel effectPanel;
    private NewPanel newPanel;
    private DeletePanel deletePanel;
    private ModifyPanel modifyPanel;
    private DisplayPanel displayPanel;
    private HomePanel homePanel;
    private TopOfClassPanel topOfClassPanel;

    private Character characterForm;
    private Boolean haveSavedValue;
    private String pseudo;
    private int number;
    private String server;
    private String game;
    private String characterClass;
    private int indexPlayerAccount;
    private int indexGame;
    private int indexServer;
    private int indexCharacterClass;

    private SingletonController singletonController;

    public Frame(){
        //Generale
        super("Account management - Home");
        setFrame();

        //Contenu
        container = this.getContentPane();

        //Menu
        menu = getMenu();
        setJMenuBar(menu);

        //add panel home
        homePanel = new HomePanel(this);

        //init for newPanel
        haveSavedValue = false;

        //connection to close
        singletonController = new SingletonController();

        try
        {
            //Set the required look and feel
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //Update the component tree - associate the look and feel with the given frame.
            SwingUtilities.updateComponentTreeUI(this);
        }//end try
        catch(Exception ex)
        {
            ex.printStackTrace();
        }//end catch

        add(homePanel);
        setVisible(true);
    }

    public void setFrame(){
        // make the frame half the height and width
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        //pack();
        setSize(width/2+100, height/2+100);

        // here's the part where i center the jframe on screen
        setLocationRelativeTo(null);

        setResizable(false);

        addWindowListener(new WindowAdapter() { //Fermer la fenetre
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    singletonController.close();
                }catch(DataAccessException dataAccessException){
                    JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Close error", JOptionPane.ERROR_MESSAGE);
                }
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
        ApplicationListner applicationListener = new ApplicationListner();

        //Appplication
        application.setMnemonic('a');
        home = new JMenuItem("Home");
        home.addActionListener(applicationListener);
        topOfClass = new JMenuItem("Top of class");
        topOfClass.addActionListener(applicationListener);
        exit = new JMenuItem("Exit");
        exit.addActionListener(applicationListener);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        application.add(home);
        application.add(topOfClass);
        application.add(exit);

        //Search
        search.setMnemonic('s');
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
        character.setMnemonic('c');
        add = new JMenuItem("Add a new character");
        add.addActionListener(characterListener);
        character.add(add);
        modify = new JMenuItem("Modifiy a character");
        modify.addActionListener(characterListener);
        character.add(modify);
        delete = new JMenuItem("Delete a character");
        delete.addActionListener(characterListener);
        character.add(delete);
        list = new JMenuItem("Display all characters");
        list.addActionListener(characterListener);
        character.add(list);

        //Menu
        menu.add(application);
        menu.add(search);
        menu.add(character);
        return menu;
    }

    private class ApplicationListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            if(event.getSource() == exit) {
                try {
                    singletonController.close();
                }catch(DataAccessException dataAccessException){
                    JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Close error", JOptionPane.ERROR_MESSAGE);
                }
                System.exit(0);
            }
            else{
                container.removeAll();
                if(event.getSource() == topOfClass){
                    topOfClassPanel = new TopOfClassPanel(getFrame());
                    container.add(topOfClassPanel);
                }else {
                    homePanel = new HomePanel(getFrame());
                    container.add(homePanel);
                }
                setVisible(true);
            }
        }
    }

    private class SearchListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
          if(event.getSource() == listGamesFromCharacter){
              gamePanel = new GamePanel(getFrame());
              container.add(gamePanel);
          }
          else{
              if(event.getSource() == listSpellsCharacterFromPlayer){
                  spellPanel = new SpellPanel(getFrame());
                  container.add(spellPanel);
              }
              else{
                  effectPanel = new EffectPanel(getFrame());
                  container.add(effectPanel);
              }
          }

          setVisible(true); //Forced to repaint the panel
        }
    }

    public class CharacterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            container.removeAll();
            if(actionEvent.getSource() == add){
                newPanel = new NewPanel(getFrame());
                container.add(newPanel);
            }
            else{
                if(actionEvent.getSource() == modify){
                    modifyPanel = new ModifyPanel(getFrame());
                    container.add(modifyPanel);
                }
                else{
                    if(actionEvent.getSource() == delete){
                        deletePanel = new DeletePanel(getFrame());
                        container.add(deletePanel);
                    }
                    else{
                        displayPanel = new DisplayPanel(getFrame());
                        container.add(displayPanel);
                    }
                }
            }
            setVisible(true);
        }
    }

    public Frame getFrame(){
        return this;
    }

    public void setCharacterForm(Character characterForm){
        this.characterForm = characterForm;
    }

    public Character getCharacterForm() {
        return characterForm;
    }

    public Boolean getHaveSavedValue() {
        return haveSavedValue;
    }

    public void setHaveSavedValue(Boolean haveSavedValue) {
        this.haveSavedValue = haveSavedValue;
    }

    public Container getContainer(){
        return container;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public int getIndexPlayerAccount() {
        return indexPlayerAccount;
    }

    public int getIndexGame() {
        return indexGame;
    }

    public int getIndexServer() {
        return indexServer;
    }

    public int getIndexCharacterClass() {
        return indexCharacterClass;
    }

    public void setIndexPlayerAccount(int indexPlayerAccount) {
        this.indexPlayerAccount = indexPlayerAccount;
    }

    public void setIndexGame(int indexGame) {
        this.indexGame = indexGame;
    }

    public void setIndexServer(int indexServer) {
        this.indexServer = indexServer;
    }

    public void setIndexCharacterClass(int indexCharacterClass) {
        this.indexCharacterClass = indexCharacterClass;
    }

    public String getTitle(){
        return "Account management ";
    }


}
