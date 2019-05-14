package Exception;

public class HealthPointsException extends Exception {
    private Integer unavailableHealthPoints;

    public HealthPointsException(Integer unavailableHealthPoints) {
        this.unavailableHealthPoints = unavailableHealthPoints;
    }

    public String getMessage() {
        return "HealthPoint get a bad value (" + unavailableHealthPoints + "), please check it.";
    }
}
