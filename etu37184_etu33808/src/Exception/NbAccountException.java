package Exception;

public class NbAccountException extends Exception {
    public String getMessage(){
        return "Désolé, une erreur c'est produite lors de la tentaive d'accès à la source de donnée.";
    }
}
