package Exo1;

import Exception.*;

public class Teacher extends Personne {

    public Teacher(String lastName, String firstName, char sex, int year, int month, int day) throws SexeException  {
        super(lastName,firstName, sex, year, month-1, day);
    }

    @Override
    public String toString(){
        return super.toString() + "(" + age() +  ")";
    }
}
