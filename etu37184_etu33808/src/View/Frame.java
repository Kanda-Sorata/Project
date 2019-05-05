package View;

import View.CharacterPanel.*;
import View.SearchPanel.EffectPanel;
import View.SearchPanel.GamePanel;
import View.SearchPanel.SpellPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Model.Character;
public class Frame extends JFrame{
    private Container container;

    private JMenuBar menu;
    JMenu application;
    JMenuItem home;
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
    EffectPanel effectPanel;
    NewPanel newPanel;
    DeletePanel deletePanel;
    ModifyPanel modifyPanel;
    DisplayPanel displayPanel;
    HomePanel homePanel;

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

    public Frame(){
        //Generale
        super("Account management");
        setFrame();

        //Contenu
        container = this.getContentPane();

        //Menu
        menu = getMenu();
        setJMenuBar(menu);

        //add panel home
        homePanel = new HomePanel();

        //init for newPanel
        haveSavedValue = false;

        add(homePanel);
        setVisible(true);
    }

    public void setFrame(){
        // make the frame half the height and width
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        pack();
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
        ApplicationListner applicationListener = new ApplicationListner();

        //Appplication
        application.setMnemonic('a');
        home = new JMenuItem("Home");
        home.addActionListener(applicationListener);
        exit = new JMenuItem("Exit");
        exit.addActionListener(applicationListener);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        application.add(home);
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

    private class ApplicationListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            if(event.getSource() == exit) {
                System.exit(0);
                //todo close Connection
            }
            else{
                container.removeAll();
                homePanel = new HomePanel();
                container.add(homePanel);
                setVisible(true);
            }
        }
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
                  effectPanel = new EffectPanel();
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
                    modifyPanel = new ModifyPanel();
                    container.add(modifyPanel);
                }
                else{
                    if(actionEvent.getSource() == delete){
                        deletePanel = new DeletePanel();
                        container.add(deletePanel);
                    }
                    else{
                        displayPanel = new DisplayPanel();
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




}
