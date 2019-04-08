package Exception;

import java.util.GregorianCalendar;

public class DateException extends  Exception{
    private int year;
    private int month;
    private int day;

    public DateException(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
