package Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import Exception.NameException;
import Exception.SexException;
import Exception.DateException;

import BusinessLogic.Utilitie;

public class PlayerAccount {
    private Integer id;
    private String pseudo;
    private Integer number;
    private char sex;
    private GregorianCalendar creationDate;
    private String city;
    private String country;
    private static int nbPlayers = 0;

    private ArrayList<Game> games;

    public PlayerAccount(String pseudo, char sex, int year, int month, int day, String city, String country) throws NameException, SexException, DateException {
        setId();
        setPseudo(pseudo);
        setNbPlayers();
        setSex(sex);
        setCreationDate(year, month-1, day);
        setCity(city);
        setCountry(country);
        setNbPlayers();
        games = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId() {
        this.id = getNbPlayers();
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) throws NameException {
            if(pseudo.matches("[^a-zA-Z]")){
                this.pseudo = pseudo;
            }
            else {
                throw new NameException(pseudo);
            }
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber() {
        this.number = Utilitie.random(0, 9000);
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) throws SexException{
        if(sex != 'f' || sex != 'm'){
            throw new SexException(sex);
        }
        else {
            this.sex = sex;
        }
    }

    public GregorianCalendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int year, int month, int day) throws DateException { //Add validationTest
        if(!Utilitie.dateAvailable(year, month, day)){
            throw new DateException(year, month, day);
        }
        else{
            creationDate = new GregorianCalendar(year, month, day);
        }
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

    public void addGame(Game game){
        games.add(game);
    }

    public Game getGame(int index){
        return games.get(index);
    }

    public String getGames(){
        String output = new String();
        for (Game game :games) {
            output += game.toString() + "\n";
        };
        return output;
    }

    @Override
    public String toString() {
        return "PlayerAccount{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", number=" + number +
                ", sex=" + sex +
                ", creationDate=" + creationDate +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", games=" + getGames() +
                '}';
    }

}
