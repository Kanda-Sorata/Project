package Exception;

public class ConnectionException extends Exception {
    private String message;
    private String SQLState;
    private int errorCode;

    public ConnectionException(String message, String SQLState, int errorCode) {
        this.message = message;
        this.SQLState = SQLState;
        this.errorCode = errorCode;
    }

    public String getInformation(){
        return "SQLException: " + message + "\nSQLState: " + SQLState + "\nVendorError: " + errorCode + "\n";
    }

    public String getMessage(){
        return  "Sorry an error has been occured, when you tried to access at the source of data";
    }
}
