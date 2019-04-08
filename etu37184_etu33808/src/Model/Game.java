package Model;

import java.util.Date;
import java.util.GregorianCalendar;

import BusinessLogic.Utilitie;
import Exception.UniqueNameException;
import Exception.DateException;

public class Game {

    private String name;
    private GregorianCalendar releaseDaze;
    private boolean haveMultiLanguages;
    private Double price;

    public Game(String name, int year, int month, int day, boolean haveMultiLanguages, double price) throws UniqueNameException, DateException {
        setName(name);
        setReleaseDaze(year, month, day);
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

    public void setReleaseDaze(int year, int month, int day) throws DateException {
        if(!Utilitie.dateAvailable(year, month, day)){
            throw new DateException(year, month, day);
        }
        else{
            releaseDaze = new GregorianCalendar(year, month, day);
        }
    }

    public boolean isHaveMultiLanguages() {
        return haveMultiLanguages;
    }

    public void setHaveMultiLanguages(boolean haveMultiLanguages) {
        this.haveMultiLanguages = haveMultiLanguages;
    }

    public double getPrice() {
        return price != null ? price : null;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
