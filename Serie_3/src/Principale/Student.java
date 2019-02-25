package Principale;
import java.io.*;
public class Student implements Serializable{
    private String lastName;
    private String firstName;
    private StudyingYear year;
    private char sexe;

    public Student(String lastName, String firstName, char sexe, StudyingYear year) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.year = year;
        this.sexe = sexe;
    }

    @Override
    public String toString(){
        String msg = sexe == 'f' || sexe == 'F' ? " inscrite ": " inscrit ";
        return firstName + " " + lastName + " " + msg + year;
    }


}
