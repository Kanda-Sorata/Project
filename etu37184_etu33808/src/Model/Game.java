package Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Game {

    private String name;
    private GregorianCalendar releaseDaze;
    private boolean haveMultiLanguages;
    private Double price;

    private ArrayList<Server> servers;

    public Game(String name, GregorianCalendar releaseDaze, boolean haveMultiLanguages) {
        setName(name);
        setReleaseDate(releaseDaze);
        setHaveMultiLanguages(haveMultiLanguages);
        servers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GregorianCalendar getReleaseDaze() {
        return releaseDaze;
    }

    public void setReleaseDate(GregorianCalendar releaseDaze) { //Add validationTest
        this.releaseDaze = releaseDaze;
    }


    public boolean isHaveMultiLanguages() {
        return haveMultiLanguages;
    }

    public void setHaveMultiLanguages(boolean haveMultiLanguages) {
        this.haveMultiLanguages = haveMultiLanguages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
