package Model;

import Exception.DamagePerSecondException;
import Exception.HealthPointsException;

import java.util.GregorianCalendar;

public class Character {
    private String name;
    private Integer healthPoints;
    private Boolean isStuffed;
    private GregorianCalendar creationDate;
    private String petName;
    private Integer damagePerSecond;

    private static final int MIN_HP = 0;
    private static final int MAX_HP = 50000;
    private static final int MIN_DMG = 0;
    private static final int MAX_DMG = 5000;

    public Character(String name, Integer healthPoints, Boolean isStuffed, GregorianCalendar creationDate, String petName, Integer damagePerSecond) throws HealthPointsException, DamagePerSecondException {
        this.name = name;
        setHealthPoints(healthPoints);
        this.isStuffed = isStuffed;
        this.creationDate = creationDate;
        this.petName = petName;
        setDamagePerSecond(damagePerSecond);
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(Integer healthPoints) throws HealthPointsException {
        if(healthPoints < MIN_HP || healthPoints > MAX_HP){
            throw new HealthPointsException(healthPoints);
        } else {
            this.healthPoints = healthPoints;
        }
    }

    public Boolean isStuffed() {
        return isStuffed;
    }

    public GregorianCalendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(GregorianCalendar creationDate) { //Add validationTest
        this.creationDate = creationDate;
    }

    public String getPetName() {
        return petName;
    }

    public Integer getDamagePerSecond() { return damagePerSecond; }

    public void setDamagePerSecond(Integer damagePerSecond) throws DamagePerSecondException{
        if(damagePerSecond != null) {
            if (damagePerSecond < MIN_DMG || damagePerSecond > MAX_DMG) {
                throw new DamagePerSecondException(damagePerSecond);
            }
        }

        this.damagePerSecond = damagePerSecond;
    }

    public Boolean getStuffed() {
        return isStuffed;
    }

    public static int getMinHp() {
        return MIN_HP;
    }

    public static int getMaxHp() {
        return MAX_HP;
    }

    public static int getMinDmg() {
        return MIN_DMG;
    }

    public static int getMaxDmg() {
        return MAX_DMG;
    }
}
