package Exception;

public class ConnectionException extends Exception {
    public String getMessage(){
        return  "Désolé, une erreur c'est produite lors de l'accès  à la source de données.";
    }
}
