package Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import Exception.NameException;
import Exception.SexException;

public class AccountPlayer {
    private Integer id;
    private String pseudo;
    private Integer number;
    private String sex;
    private GregorianCalendar creationDate;
    private String city;
    private String country;
    private static int nbPlayers = 0;

    public AccountPlayer(Integer id, String pseudo, Integer number, String sex, GregorianCalendar creationDate,
                         String country) throws NameException, SexException {
        setId(id);
        setPseudo(pseudo);
        setNumber(number);
        setSex(sex);
        setCreationDate(creationDate);
        setCity(null);
        setCountry(country);
        setNbPlayers();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) throws NameException {
        if(pseudo.matches("^[a-zA-Z0-9]")){
            this.pseudo = pseudo;
        }
        else {
            throw new NameException(pseudo);
        }
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSex() { return sex; }

    public void setSex(String sex) throws SexException{ //Add tolower
        if(sex.charAt(0) != 'f' || sex.charAt(0) != 'm'){
            throw new SexException(sex.charAt(0));
        }
        else {
            this.sex = sex;
        }
    }

    public GregorianCalendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(GregorianCalendar creationDate) { //Add validationTest
        this.creationDate = creationDate;
    }

    public String getCity() {
        return city != null ? city : null;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static int getNbPlayers() {
        return nbPlayers;
    }

    public static void setNbPlayers() {
        nbPlayers += 1;
    }

    @Override
    public String toString() {
        return "AccountPlayer{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", number=" + number +
                ", sex=" + sex +
                ", creationDate=" + creationDate +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

}
