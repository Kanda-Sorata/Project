package Model;

public class SearchSpellList {
    private String spellName;
    private Integer spellCooldown;
    private String characterName;

    public SearchSpellList(String spellName, Integer spellCooldown, String characterName) {
        setSpellName(spellName);
        setSpellCooldown(spellCooldown);
        setCharacterName(characterName);
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public Integer getSpellCooldown() {
        return spellCooldown;
    }

    public void setSpellCooldown(Integer spellCooldown) {
        this.spellCooldown = spellCooldown;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
}
