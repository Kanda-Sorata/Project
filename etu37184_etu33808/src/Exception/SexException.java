package Exception;

public class SexException extends Exception {
    private char unavailableSex;

    public SexException(char unavailableSex) {
        this.unavailableSex = unavailableSex;
    }

    public String getMessage() {
        return "Sex get a bad value (" + unavailableSex + ")";
    }
}
