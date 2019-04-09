package Model;

import java.util.GregorianCalendar;

import BusinessLogic.Utilitie;
import Exception.UniqueNameException;

public class Game {

    private String name;
    private GregorianCalendar releaseDaze;
    private boolean haveMultiLanguages;
    private Double price;

    public Game(String name, GregorianCalendar releaseDaze, boolean haveMultiLanguages, double price) throws UniqueNameException {
        setName(name);
        setReleaseDaze(releaseDaze);
        setHaveMultiLanguages(haveMultiLanguages);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name)throws UniqueNameException {
        if(!Utilitie.estUnique("Game", name)) {
            throw new UniqueNameException(name);
        }
        else{
            this.name =  name;
        }
    }

    public GregorianCalendar getReleaseDaze() {
        return releaseDaze;
    }

    public void setReleaseDaze(GregorianCalendar releaseDaze) { //Add validationTest
        this.releaseDaze = releaseDaze;
    }


    public boolean isHaveMultiLanguages() {
        return haveMultiLanguages;
    }

    public void setHaveMultiLanguages(boolean haveMultiLanguages) {
        this.haveMultiLanguages = haveMultiLanguages;
    }

    public Double getPrice() {
        return price != null ? price : null;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
