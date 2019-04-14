package Exception;

public class SelectException extends Exception{
    private String request;

    public SelectException(String request) {
        this.request = request;
    }

    public String getMessage() {
        return request;
    }
}
