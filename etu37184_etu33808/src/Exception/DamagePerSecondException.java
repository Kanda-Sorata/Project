package Exception;

public class DamagePerSecondException extends Exception {
    private Integer DamagePerSecondException;

    public DamagePerSecondException(Integer damagePerSecondException) {
        DamagePerSecondException = damagePerSecondException;
    }

    public String getMessage() { return DamagePerSecondException + ""; }
}
