package Exo_1;

public class Editor {
    private String name;
    private Location location;

    public Editor(String name, Location location){
        this.name = name;
        this.location = location;
    }

    public String getName(){
        return name;
    }

    public Location getLocation(){
        return location;
    }

    @Override
    public String toString() {
        return "Les éditions : " + name;
    }
}
