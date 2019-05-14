package Model;

public class CharacterClass {
    private String name;
    private String description;
    private Game game;

    public CharacterClass(String name, String description, Game game){
        this.name = name;
        this.description = description;
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
            this.name = name;
}

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
