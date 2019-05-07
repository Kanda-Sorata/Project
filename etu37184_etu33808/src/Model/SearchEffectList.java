package Model;

public class SearchEffectList {
    private String labelEffect;
    private String spellName;
    private Integer cooldown;

    public SearchEffectList(String labelEffect, String spellName, Integer cooldown) {
        this.labelEffect = labelEffect;
        this.spellName = spellName;
        this.cooldown = cooldown;
    }

    public String getLabelEffect() {
        return labelEffect;
    }

    public String getSpellName() {
        return spellName;
    }

    public Integer getCooldown() {
        return cooldown;
    }

    public void setLabelEffect(String labelEffect) {
        this.labelEffect = labelEffect;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public void setCooldown(Integer cooldown) {
        this.cooldown = cooldown;
    }
}
