package Exception;

public class DivideException extends Exception {
    private Integer num;
    private Integer denom;

    public DivideException(Integer num, Integer denom) {
        this.num = num;
        this.denom = denom;
    }

    public String getMessage() {
        return "Sorry, an error occurred when you tried to access at the Top of Class.\n";
    }

    public String getMessageDetailed() {
        return "Divide by 0 can be done !\nNum : " + num + "\nDenom : " + denom + "\n";
    }
}
