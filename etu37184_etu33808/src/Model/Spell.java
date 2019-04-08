package Model;

import java.util.ArrayList;
import Exception.NameException;

public class Spell {
    private String name;
    private Integer cooldown;
    private boolean atRange;
    private Integer damageValue;
    private ArrayList<String> effects;

    public Spell(String name, Integer cooldown, boolean atRange, Integer damageValue) throws NameException{
        setName(name);
        setCooldown(cooldown);
        setAtRange(atRange);
        setDamageValue(damageValue);
        effects = new ArrayList<>();
    }

    public Spell(String name, boolean atRange, Integer damageValue) throws NameException{
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

    public boolean isAtRange() {
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

    public String getEffects(int index) {
        return effects.get(index);
    }

    public String getAllEffects(){
        String output="";
        for (String effect: effects) {
            output += effect + "\n";
        }
        return output;
    }

    public void setEffects(String effect) {
        this.effects = effects;
    }
}
