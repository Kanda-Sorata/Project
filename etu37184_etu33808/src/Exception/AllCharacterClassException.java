package Exception;

public class AllCharacterClassException extends Exception {
    private int code;

    public AllCharacterClassException(int code){
        this.code = code;
    }

    public String getMessage(){
        String output = "";
        switch(code) {
            case 0:
                output = "Désolé, une erreur s'est produite lors de la tentative d'accès à la source de données.";
                break;
            case 1:
                output = "Désolé, certaines données sont corrompues, impossible de continuer.";
                break;
        }
        return output + "\n";
    }
}
