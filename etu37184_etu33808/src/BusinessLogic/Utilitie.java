package BusinessLogic;

import java.util.GregorianCalendar;

public class Utilitie {
    public static boolean estUnique(String table, String name){
        ///To create new collection from bd and check if the name is equal to another name into this collection by businessLogic
        String request = "";
        return  Integer.parseInt(request) == 0;
    }

    public static int random(int min, int max){
        if(min > -1 && max > 0){
            return min + (int)(Math.random() * ((max - min) + 1));
        }
        else {
            return -1;
        }
    }

    public static boolean dateAvailable(int year, int month, int day){
        boolean isLeap = (year % 4 == 0 && year % 100 != 0) && (year % 400 == 0);
        GregorianCalendar calendar = new GregorianCalendar();
        boolean isDate = false;

        return isDate;
    }

}
