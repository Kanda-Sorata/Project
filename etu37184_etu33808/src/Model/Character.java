package Model;

import Exception.DamagePerSecondException;
import Exception.HealthPointsException;
import Exception.NameException;

import java.util.GregorianCalendar;

public class Character {
    private String name;
    private Integer healthPoints;
    private boolean isStuffed;
    private GregorianCalendar creationDate;
    private String petName;
    private Integer damagePerSecond;

    private CharacterClass characterClass;
    private AccountPlayer player;

    private static final int MIN_HP = 0;
    private static final int MAX_HP = 50000;

    public Character(String name, Integer healthPoints, boolean isStuffed, GregorianCalendar creationDate, String petName,
                     Integer damagePerSecond, CharacterClass characterClass, AccountPlayer player) throws NameException,
                     HealthPointsException, DamagePerSecondException {
        setName(name);
        setHealthPoints(healthPoints);
        this.isStuffed = isStuffed;
        setCreationDate(creationDate);
        setName(petName);
        setDamagePerSecond(damagePerSecond);
        setCharacterClass(characterClass);
        setPlayer(player);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NameException{
        if(name.matches("^[a-zA-Z0-9]")){
            this.name = name;
        }
        else {
            throw new NameException(name);
        }
    }

    public Integer getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(Integer healthPoints) throws HealthPointsException {
        if(healthPoints < MIN_HP || healthPoints > MAX_HP){
            throw new HealthPointsException(healthPoints);
        }
        else{
            this.healthPoints = healthPoints;
        }
    }

    public boolean isStuffed() {
        return isStuffed;
    }

    public GregorianCalendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(GregorianCalendar creationDate) { //Add validationTest
        this.creationDate = creationDate;
    }

    public String getPetName() {
        return petName != null ? petName : null;
    }

    public void setPetName(String petName) throws NameException{
        if(petName != null && !name.matches("[^a-zA-Z]")) {
            throw new NameException(petName);
        }
        else{
            this.petName = petName;
        }
    }

    public Integer getDamagePerSecond() { return damagePerSecond != null ? damagePerSecond : null; }

    public void setDamagePerSecond(Integer damagePerSecond) throws DamagePerSecondException{
        if(damagePerSecond < MIN_HP || damagePerSecond > MAX_HP){
            throw new DamagePerSecondException(damagePerSecond);
        }
        else{
            this.damagePerSecond = damagePerSecond;
        }
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public AccountPlayer getPlayer() {
        return player;
    }

    public void setPlayer(AccountPlayer player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", healthPoints=" + healthPoints +
                ", isStuffed=" + isStuffed +
                ", creationDate=" + creationDate +
                ", petName='" + petName + '\'' +
                ", damagePerSecond=" + damagePerSecond +
                ", characterClass=" + characterClass +
                ", player=" + player +
                '}';
    }
}
