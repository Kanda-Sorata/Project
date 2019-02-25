package Exo1;

import Exception.SexeException;

import java.util.GregorianCalendar;

public class Personne {
    private String lastName;
    private String firstName;
    private char sex;
    private GregorianCalendar birthDay;



    public Personne(String lastName, String firstName, char sex, int year, int month, int day) throws SexeException {
        this.lastName = lastName;
        this.firstName = firstName;
        setSexe(sex);
        birthDay = new GregorianCalendar(year, month-1, day);
    }

    //Gettors

    public char getSex(){
        return sex;
    }

    public String getLastName(){
        return lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public String age(){
        GregorianCalendar now = new GregorianCalendar();
        int diffYear = now.get(GregorianCalendar.YEAR) - birthDay.get(birthDay.YEAR);
        int diffMonth = now.get(GregorianCalendar.MONTH) - birthDay.get(birthDay.MONTH);
        String msg = "";

        if(diffMonth < 0)   diffMonth *= -1;

        if(diffYear > 0 && diffMonth > 0)
            msg = diffYear + " ans " + " et " + diffMonth +" mois";
        else
            msg = "Pas de modif";

        return msg;
    }

    //Settors1

    private void setSexe(char sex) throws SexeException {
        if(sex != 'f' && sex != 'F' && sex != 'm' && sex != 'M')
            throw new SexeException(sex);
        else
            this.sex = sex;
    }

    @Override
    public String toString(){
        return lastName + " " + firstName;
    }
}