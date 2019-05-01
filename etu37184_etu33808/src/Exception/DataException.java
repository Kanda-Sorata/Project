package Exception;

public class DataException extends Exception {
    private int code;

    public DataException(int code) {
        this.code = code;
    } //0 : connection, 1 : SQLException, 2 : HealthPoint, 3 : NameException 4 : SexException, 5 : UniqueNameException, 6 : DamagePerSecond

    public String getMessage() {
        String output = "";
        switch (code) {
            case 0:
                output = "Désolé, une erreur s'est produite lors de la tentative d'accès à la source de données.";
                break;
            case 2: case 3: case 4: case 5: case 6: case 7 :
                output = "Désolé, des données sont non répertoriées ou non accessibles, impossible de continuer.";
                break;
        }
        return output + "\n";
    }
}
