package Model;

public class Spell {
    private String name;
    private Integer cooldown;
    private Boolean atRange;
    private Integer damageValue;

    public Spell(String name, Integer cooldown, Boolean atRange, Integer damageValue) {
        this.name = name;
        this.cooldown = cooldown;
        this.atRange = atRange;
        this.damageValue = damageValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
