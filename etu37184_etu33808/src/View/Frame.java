package View;

import Controller.SingletonController;
import Exception.DataAccessException;
import Exception.DataException;
import Exception.DivideException;
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
    private JMenuItem dataAccount;
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
    private JMenu help;
    private JMenuItem showHelp;

    private GamePanel gamePanel;
    private SpellPanel spellPanel;
    private EffectPanel effectPanel;
    private NewPanel newPanel;
    private DeletePanel deletePanel;
    private ModifyPanel modifyPanel;
    private DisplayPanel displayPanel;
    private HomePanel homePanel;
    private TopOfClassPanel topOfClassPanel;
    private HelpPanel helpPanel;

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

    private InformationPanel informationPanel;

    public Frame(){
        //General
        super("Account management - Home");
        setFrame();

        //Container
        container = this.getContentPane();

        //Menu
        menu = getMenu();
        setJMenuBar(menu);

        //add panel home
        homePanel = new HomePanel();

        //init for newPanel
        haveSavedValue = false;

        //connection to close
        singletonController = new SingletonController();

        //Add look and feel
        try
        {
            //Set the required look and feel
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //Update the component tree - associate the look and feel with the given frame.
            SwingUtilities.updateComponentTreeUI(this);
        }//end try
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "An error has occurred with the appearance of the software, we apologize about this.\n" +
                    "This will not impact your work.", "Warning - Appearance", JOptionPane.WARNING_MESSAGE);
        }//end catch


        //Thread
        SingletonThread.getSingletonThread(this);


        //Add component
        add(homePanel);

        setVisible(true);
    }

    public int getLocationThreadX() {
        return getLocation().x + getSize().width + 5;
    }

    public int getLocationThreadY() {
        return getLocation().y;
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
        help = new JMenu("?");

        SearchListener searchListener = new SearchListener();
        CharacterListener characterListener = new CharacterListener();
        ApplicationListener applicationListener = new ApplicationListener();
        HelpListener helpListener = new HelpListener();

        //Application
        application.setMnemonic('a');
        home = new JMenuItem("Home");
        home.addActionListener(applicationListener);
        topOfClass = new JMenuItem("Top of class");
        topOfClass.addActionListener(applicationListener);
        dataAccount = new JMenuItem("Data about Account");
        dataAccount.addActionListener(applicationListener);
        exit = new JMenuItem("Exit");
        exit.addActionListener(applicationListener);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        application.add(home);
        application.add(topOfClass);
        application.add(dataAccount);
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

        //Help
        help.setMnemonic('?');
        showHelp = new JMenuItem("Show the help");
        showHelp.addActionListener(helpListener);
        help.add(showHelp);

        //Menu
        menu.add(application);
        menu.add(search);
        menu.add(character);
        menu.add(help);
        return menu;
    }

    private class ApplicationListener implements ActionListener {
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
                if(event.getSource() == topOfClass){
                    try {
                        topOfClassPanel = new TopOfClassPanel();
                        setTitleFrame("Top of Classes");
                        container.removeAll();
                        container.add(topOfClassPanel);
                    } catch (DataAccessException dataAccessException) {
                        updatePanelToGoHome();
                        JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (DataException dataException) {
                        updatePanelToGoHome();
                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (DivideException divideException) {
                        updatePanelToGoHome();
                        JOptionPane.showMessageDialog(null, divideException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    if (event.getSource() == home) {
                        homePanel = new HomePanel();
                        setTitleFrame("Home");
                        container.removeAll();
                        container.add(homePanel);
                    } else {
                        SingletonThread.getSingletonThread(getFrame());
                    }
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
              try {
                  gamePanel = new GamePanel();
                  container.add(gamePanel);
                  setTitleFrame("Search for game(s)");
              } catch (DataAccessException dataAccessException) {
                  updatePanelToGoHome();
                  JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
              } catch (DataException dataException) {
                  updatePanelToGoHome();
                  JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
              }
          }
          else{
              if(event.getSource() == listSpellsCharacterFromPlayer){
                  try {
                      spellPanel = new SpellPanel();
                      container.add(spellPanel);
                      setTitleFrame("Search for spell(s)");
                  } catch (DataAccessException dataAccessException) {
                      updatePanelToGoHome();
                      JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                  } catch (DataException dataException) {
                      updatePanelToGoHome();
                      JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                  }
              }
              else{
                  try {
                      effectPanel = new EffectPanel();
                      container.add(effectPanel);
                      setTitleFrame("Search for spell's effect(s)");
                  } catch (DataAccessException dataAccessException) {
                      updatePanelToGoHome();
                      JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                  } catch (DataException dataException) {
                      updatePanelToGoHome();
                      JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                  }
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
                try {
                    newPanel = new NewPanel(getFrame());
                    container.add(newPanel);
                    setTitleFrame("Add a new character");
                } catch (DataAccessException dataAccessException) {
                    updatePanelToGoHome();
                    JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (DataException dataException) {
                    updatePanelToGoHome();
                    JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                if(actionEvent.getSource() == modify){
                    try {
                        modifyPanel = new ModifyPanel(getFrame());
                        container.add(modifyPanel);
                        setTitleFrame("Modify a character");
                    } catch (DataAccessException dataAccessException) {
                        updatePanelToGoHome();
                        JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (DataException dataException) {
                        updatePanelToGoHome();
                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    if(actionEvent.getSource() == delete){
                        try {
                            deletePanel = new DeletePanel();
                            container.add(deletePanel);
                            setTitleFrame("Delete a character");
                        } catch (DataAccessException dataAccessException) {
                            updatePanelToGoHome();
                            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (DataException dataException) {
                            updatePanelToGoHome();
                            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        try {
                            displayPanel = new DisplayPanel();
                            container.add(displayPanel);
                            setTitleFrame("Display all character(s)");
                        } catch (DataAccessException dataAccessException) {
                            updatePanelToGoHome();
                            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (DataException dataException) {
                            updatePanelToGoHome();
                            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
            setVisible(true);
        }
    }

    public class HelpListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent){
            container.removeAll();
            helpPanel = new HelpPanel();
            container.add(helpPanel);
            setTitleFrame("Help");
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

    public void setTitleFrame(String extraTitle) {
        setTitle("");
        setTitle("Account management - " + extraTitle);
    }

    public String getTitle(){
        return "Account management ";
    }


    public void updatePanelToGoHome() {
        informationPanel = new InformationPanel(this);
        container.removeAll();
        container.add(informationPanel);
        container.revalidate();
        container.repaint();
    }

}
