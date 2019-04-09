package Exception;

public class UniqueNameException extends Exception {
    private String unavailableName;

    public UniqueNameException(String unavailableName) {
        this.unavailableName = unavailableName;
    }

    public String getMessage() {
        return unavailableName;
    }
}
