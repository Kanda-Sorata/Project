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
                output = "Sorry an error has been occured, when you tried to access at the source of data";
                break;
            case 2: case 3: case 4: case 5: case 6: case 7 :
                output = "Sorry, the data has not indexed or not available at this time, impossible to continue.";
                break;
        }
        return output + "\n";
    }
}
