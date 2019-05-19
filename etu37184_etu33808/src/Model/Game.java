package Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Game {

    private String name;
    private GregorianCalendar releaseDate;
    private Boolean haveMultiLanguages;
    private ArrayList<Server> servers;

    public Game(String name, GregorianCalendar releaseDate, Boolean haveMultiLanguages) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.haveMultiLanguages = haveMultiLanguages;
        servers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
