package Exception;

import java.io.*;
public class YearException extends Exception implements Serializable{

    private int year;

    public YearException(int year){
        this.year = year;
    }

    public String getMessage(){
        return "year used " + year + " is not available\nOption : 1, 2 or 3\n";
    }
}
