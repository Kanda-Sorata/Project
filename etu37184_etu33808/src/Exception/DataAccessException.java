package Exception;

public class DataAccessException extends Exception {
    public String getMessage(){
        return "Sorry an error has been occured, when you tried to access at the data";
    }
}
