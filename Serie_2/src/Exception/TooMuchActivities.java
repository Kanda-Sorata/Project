package Exception;

public class TooMuchActivities extends Exception{

    public String getMessage(){
        return "L'activitée n'a pas été ajoutée, la liste des cours est complète.";
    }
}
