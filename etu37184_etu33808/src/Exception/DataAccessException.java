package Exception;

public class DataAccessException extends Exception {
    private int code;

    public DataAccessException(int code) {
        this.code = code;
    }

    public String getMessage() {
        String output = "Sorry an error occurred ";
        switch (code) {
            case 1: output += "when you tried to access to the data.";
                break;
            case 2: output += "at closing connection at source of data. There is a risk about the privacy of your data.";
                break;
        }
        return output;
    }
}
