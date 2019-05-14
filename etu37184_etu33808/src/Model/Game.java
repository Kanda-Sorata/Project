package Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Game {

    private String name;
    private GregorianCalendar releaseDaze;
    private Boolean haveMultiLanguages;
    private Double price;

    private ArrayList<Server> servers;

    public Game(String name, GregorianCalendar releaseDaze, Boolean haveMultiLanguages) {
        this.name = name;
        this.releaseDaze = releaseDaze;
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
