package Exception;

public class SexeException extends Exception {
    private char sexError;

    public SexeException(char sexError){
        this.sexError = sexError;
    }

    public String getMessage(){
        return "Le sexe d'une personne doit être  féminin 'f' / 'F' ou masculin 'm' / 'M' !\nValeur utilisateur : " + sexError;
    }
}
