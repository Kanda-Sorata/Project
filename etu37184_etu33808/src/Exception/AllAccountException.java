package Exception;

public class AllAccountException extends Exception{
    private int code;

    public AllAccountException(int code){
        this.code = code;
    }

    public String getMessage(){
        String output = "";
        switch(code) {
            case 0:
                output = "Désolé, il y a eu une erreur lors de l'accès à la source des données.";
                break;
            case 1: case 2:
                output = "Désolé, certaines données sont corrompues, impossible de continuer.";
                break;
        }
        return output + "\n";
    }
}
