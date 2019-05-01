package Exception;

public class DataAccessException extends Exception {
    public String getMessage(){
        return "Désolé, une erreur s'est produite lors de la tentative d'accès aux données.";
    }
}
