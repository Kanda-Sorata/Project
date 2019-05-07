package Exception;

public class DataException extends Exception {
    private int code;

    public DataException(int code) {
        this.code = code;
    }  //1 : SQLException, 2 : HealthPoint, 3 : NameException 4 : SexException, 5 : UniqueNameException, 6 : DamagePerSecond 8 : Insert

    public String getMessage() {
        String output = "Sorry an error has been occured, when you tried ";
        switch (code) {
            case 1:
                output += "to access, the data has not indexed or not available at this time, impossible to continue.";
                break;
            case 2:
                output += "to set HealthPoint";
                break;
            case 3:
                output += "to set the Name";
                break;
            case 4:
                output += "to set the Sex";
                break;
            case 5:
                output += "UniqueName";
                break;
            case 6:
                output += "Damage per second";
                break;
            case 7:
                output += "to add a new charcater. The add has been cancelled.";
                break;
            case 8:
                output += "to delete a chatacer. The delete has been cancelled.";
        }
        return output + " - Exception\n";
    }
}
