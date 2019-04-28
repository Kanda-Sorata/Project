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
        return  "Désolé, une erreur c'est produite lors de l'accès  à la source de données.\n" + getInformation();
    }
}
