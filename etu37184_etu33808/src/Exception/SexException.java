package Exception;

public class SexException extends Exception {
    private char unavailableSex;

    public SexException(char unavailableSex) {
        this.unavailableSex = unavailableSex;
    }

    public String getMessage() {
        return "Code 2."  + "Values :" + unavailableSex;
    }
}
