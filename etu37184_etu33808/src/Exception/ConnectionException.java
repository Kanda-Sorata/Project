package Exception;

public class ConnectionException extends Exception {
    public String getMessage(){
        return "Sorry an error occurred when you tried to access to the source of data";
    }
}
