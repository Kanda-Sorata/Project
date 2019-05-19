package Exception;

public class UniqueNameException extends Exception {
    private String unavailableName;

    public UniqueNameException(String unavailableName) {
        this.unavailableName = unavailableName;
    }

    public String getMessage() {
        return "Sorry an error occurred when you tried to add a new character.\n" +
                "The add has been cancelled.\n" + "An other character already exists on this server with the same name.\n" +
                "\" " + unavailableName + " \"";
    }
}
