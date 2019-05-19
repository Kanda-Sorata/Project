package Exception;

public class DataException extends Exception {
    private int code;

    public DataException(int code) {
        this.code = code;
    }  //1 : SQLException, 2 : HealthPoint, 3 : NameException 4 : SexException, 5 : DamagePerSecond 6 : Insert 7 : DeleteCharacter 8 : Modify

    public String getMessage() {
        String output = "Sorry an error occurred when you tried ";
        switch (code) {
            case 1:
                output += "to access to the data. They were not available at this time, impossible to continue.";
                break;
            case 2:
                output += "to set the health points.";
                break;
            case 3:
                output += "to set the name.";
                break;
            case 4:
                output += "to set the sex.";
                break;
            case 5:
                output += "to set the damages per second.";
                break;
            case 6:
                output += "to add a new character. The add has been cancelled.";
                break;
            case 7:
                output += "to delete a character. The delete has been cancelled.";
                break;
            case 8:
                output += "to modify a character. The modify has been cancelled.";
        }
        return output + "\n";
    }
}
