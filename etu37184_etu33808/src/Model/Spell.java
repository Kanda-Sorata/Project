package Model;

public class Spell {
    private String name;
    private Integer cooldown;
    private Boolean atRange;
    private Integer damageValue;

    public Spell(String name, Integer cooldown, Boolean atRange, Integer damageValue) {
        setName(name);
        setCooldown(cooldown);
        setAtRange(atRange);
        setDamageValue(damageValue);
    }

    public Spell(String name, boolean atRange, Integer damageValue) {
       this(name, null, atRange, damageValue);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCooldown() {
        return cooldown;
    }

    public void setCooldown(Integer cooldown) {
        this.cooldown = cooldown;
    }

    public Boolean isAtRange() {
        return atRange;
    }

    public void setAtRange(boolean atRange) {
        this.atRange = atRange;
    }

    public Integer getDamageValue() {
        return damageValue;
    }

    public void setDamageValue(Integer damageValue) {
        this.damageValue = damageValue;
    }
}
