package Model;

import Exception.NameException;

public class CharacterClass {
    private String name;
    private String description;
    private Game game;

    public CharacterClass(String name, String description, Game game) throws NameException{
        this.name = name;
        this.description = description;
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NameException{
        if(name.matches("^[a-zA-Z]")){
            this.name = name;
        }
        else {
            throw new NameException(name);
        }
}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
