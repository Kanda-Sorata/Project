package Exception;

public class DamagePerSecondException extends Exception {
    private Integer damagePerSecondException;

    public DamagePerSecondException(Integer damagePerSecondException) {
        damagePerSecondException = damagePerSecondException;
    }

    public String getMessage() {
        return "Damage per second get a bad value (" + damagePerSecondException + "), please check it.";
    }
}
