package Exception;

public class NameException extends Exception {
    private String unavailableName;

    public NameException(String unavailableName) {
        this.unavailableName = unavailableName;
    }

    public String getMessage() {
        return "Code 1. " + "values :" + unavailableName;
    }
}
