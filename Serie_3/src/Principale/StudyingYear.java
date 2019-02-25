package Principale;

import javax.swing.*;
import Exception.YearException;

import java.io.*;

public class StudyingYear implements Serializable{
    private String labelYear;
    private int year;
    private String level;

    public StudyingYear(String labelYear, int year, String level) throws YearException {
        setLabelYear(level);
        setYear(year);
        this.labelYear = labelYear;
    }

    public void setLabelYear(String level){
        if(! level.equals("Master") && ! level.equals("Bac")){
            JOptionPane.showMessageDialog(null, "Level ",
                    "Level year used " + level + " is not available\nOption : bloc  or master\n",
                    JOptionPane.ERROR_MESSAGE);
        }
        this.level = level;
    }

    public void setYear(int year) throws YearException {
        if(year < 1 || year > 3){
            throw new YearException(year);
        }

        this.year = year;
    }

    @Override
    public String toString(){
        return level + " " + year + " en " + labelYear;
    }
}