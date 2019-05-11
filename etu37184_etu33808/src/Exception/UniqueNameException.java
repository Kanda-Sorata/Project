package Exception;

public class UniqueNameException extends Exception {
    private String unavailableName;

    public UniqueNameException(String unavailableName) {
        this.unavailableName = unavailableName;
    }

    public String getMessage() {
        return "Sorry an error has been occurred, when you tried  to add a new character.\n" +
                "The add has been cancelled.\n" + "Your already have a character with the same name.\n" +
                "\" " + unavailableName + " \"";
    }
}
