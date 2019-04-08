package Model;

import java.util.ArrayList;
import Exception.NameException;

public class CharacterClass {
    private String name;
    private String description;
    private ArrayList<String> usableWeapons;
    private Game game;

    public CharacterClass(String name, String description, ArrayList<String> usableWeapons, Game game) throws NameException{
        this.name = name;
        this.description = description;
        this.usableWeapons = usableWeapons;
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NameException{
        if(name.matches("[^a-zA-Z]")){
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

    public String getUsableWeapons(){
        String output = "";
        for (String weapon: usableWeapons) {
            output += weapon + "\n";
        }
        return output;
    }

    public void addUsableWeapon(String weapon){
        usableWeapons.add(weapon);
    }

}
