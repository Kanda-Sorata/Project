package Exception;

public class DamagePerSecondException extends Exception {
    private Integer DamagePerSecondException;

    public DamagePerSecondException(Integer damagePerSecondException) {
        DamagePerSecondException = damagePerSecondException;
    }

    public String getMessage() { return  "Damage per second get a bad value, please check it. - code(4)"; }
}
