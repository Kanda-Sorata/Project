package Model;

import Exception.SexException;

import java.util.GregorianCalendar;

public class PlayerAccount {
    private Integer id;
    private String pseudo;
    private Integer number;
    private String sex;
    private GregorianCalendar creationDate;
    private String city;
    private String country;
    private static int nbPlayers = 0;

    public PlayerAccount(Integer id, String pseudo, Integer number, String sex, GregorianCalendar creationDate, String city, String country) throws SexException {
        this.id = id;
        this.pseudo = pseudo;
        this.number = number;
        setSex(sex);
        this.creationDate = creationDate;
        this.city = city;
        this.country = country;
        setNbPlayers();
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
            this.pseudo = pseudo;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setSex(String sex) throws SexException {
        if(sex.equals("f") || sex.equals("m")){
          this.sex = sex;
        } else {
           throw new SexException(sex.charAt(0));
        }
    }

    public void setCreationDate(GregorianCalendar creationDate) { //Add validationTest
        this.creationDate = creationDate;
    }

    public static void setNbPlayers() {
        nbPlayers += 1;
    }

}
