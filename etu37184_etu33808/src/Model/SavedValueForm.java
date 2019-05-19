package Model;


public class SavedValueForm {
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
    private Integer indexCharacter;

    public SavedValueForm(Character characterForm, Boolean haveSavedValue, String pseudo, int number, String server,
                          String game, String characterClass, int indexPlayerAccount, int indexGame, int indexServer,
                          int indexCharacterClass, Integer indexCharacter) {
        this.characterForm = characterForm;
        this.haveSavedValue = haveSavedValue;
        this.pseudo = pseudo;
        this.number = number;
        this.server = server;
        this.game = game;
        this.characterClass = characterClass;
        this.indexPlayerAccount = indexPlayerAccount;
        this.indexGame = indexGame;
        this.indexServer = indexServer;
        this.indexCharacterClass = indexCharacterClass;
        this.indexCharacter = indexCharacter;
    }

    public SavedValueForm() {
        this(null, null, null, 0, null, null,
                null, 0, 0, 0, 0, null);
    }

    public void setCharacterForm(Character characterForm) {
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

    public int getIndexPlayerAccount() {
        return indexPlayerAccount;
    }

    public int getIndexGame() { return indexGame; }

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

    public Integer getIndexCharacter() {
        return indexCharacter;
    }

    public void setIndexCharacter(Integer indexCharacter) {
        this.indexCharacter = indexCharacter;
    }
}
