package Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import Exception.*;
import BusinessLogic.Utilitie;

public class Character {
    private String name;
    private Integer healthPoints;
    private boolean isStuffed;
    private GregorianCalendar creationDate;
    private String petName;
    private Integer damagePerSecond;

    private ArrayList<Server> servers;
    private CharacterClass characterClass;
    private PlayerAccount player;

    private static final int MIN_HP = 0;
    private static final int MAX_HP = 50000;

    public Character(String name, Integer healthPoints, boolean isStuffed, int year, int month, int day,
                     String petName, Integer damagePerSecond, CharacterClass characterClass, PlayerAccount player) throws NameException, HealthPointsException,
                     DamagePerSecondException, DateException {
        setName(name);
        setHealthPoints(healthPoints);
        this.isStuffed = isStuffed;
        setCreationDate(year, month-1, day);
        setName(petName);
        setDamagePerSecond(damagePerSecond);
        servers = new ArrayList<>();
        setCharacterClass(characterClass);
        setPlayer(player);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NameException{
        if(name.matches("[^a-zA-Z]")){
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

    public void setCreationDate(int year, int month, int day) throws DateException{ //Add validationTest
        if(!Utilitie.dateAvailable(year, month, day)){
            throw new DateException(year, month, day);
        }
        else{
            creationDate = new GregorianCalendar(year, month, day);
        }
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

    public PlayerAccount getPlayer() {
        return player;
    }

    public void setPlayer(PlayerAccount player) {
        this.player = player;
    }

    public void addServer(Server server){
        servers.add(server);
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
                ", servers=" + servers +
                ", characterClass=" + characterClass +
                ", player=" + player +
                '}';
    }
}
