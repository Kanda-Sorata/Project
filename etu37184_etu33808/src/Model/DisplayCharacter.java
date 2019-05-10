package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class DisplayCharacter {
    private String gameName;
    private String serverName;
    private String characterName;
    private String characterClassName;
    private Integer healthPoint;
    private Boolean isStuffed;
    private GregorianCalendar creationDate;
    private String petName;
    private Integer damagePerSecond;

    public DisplayCharacter(String gameName, String serverName, String characterName, String characterClassName, Integer healthPoint, Boolean isStuffed, GregorianCalendar creationDate, String petName, Integer damagePerSecond) {
        setGameName(gameName);
        setServerName(serverName);
        setCharacterName(characterName);
        setCharacterClassName(characterClassName);
        setHealthPoint(healthPoint);
        setIsStuffed(isStuffed);
        setCreationDate(creationDate);
        setPetName(petName);
        setDamagePerSecond(damagePerSecond);
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setCharacterClassName(String characterClassName) {
        this.characterClassName = characterClassName;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void setIsStuffed(boolean isStuffed) {
        this.isStuffed = isStuffed;
    }

    public void setCreationDate(GregorianCalendar creationDate) {
        this.creationDate = creationDate;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setDamagePerSecond(Integer damagePerSecond) {
        this.damagePerSecond = damagePerSecond;
    }

    public String getGameName() {
        return gameName;
    }

    public String getServerName() {
        return serverName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getCharacterClassName() {
        return characterClassName;
    }

    public Integer getHealthPoint() {
        return healthPoint;
    }

    public Boolean getIsStuffed() {
        return isStuffed;
    }

    public GregorianCalendar getCreationDate() {
        return creationDate;
    }

    public String getPetName() {
        return petName;
    }

    public Integer getDamagePerSecond() {
        return damagePerSecond;
    }

    public String getCreationDateFormater() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(creationDate.getTime());
    }
}
