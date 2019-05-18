package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class DisplayCharacter {
    private String playerAccount;
    private String gameName;
    private String serverName;
    private String characterName;
    private String characterClassName;
    private Integer healthPoint;
    private Boolean isStuffed;
    private GregorianCalendar creationDate;
    private String petName;
    private Integer damagePerSecond;

    public DisplayCharacter(String playerAccount, String gameName, String serverName, String characterName, String characterClassName, Integer healthPoint, Boolean isStuffed, GregorianCalendar creationDate, String petName, Integer damagePerSecond) {
        this.playerAccount = playerAccount;
        this.gameName = gameName;
        this.serverName = serverName;
        this.characterName = characterName;
        this.characterClassName = characterClassName;
        this.healthPoint = healthPoint;
        this.isStuffed = isStuffed;
        this.creationDate = creationDate;
        this.petName = petName;
        this.damagePerSecond = damagePerSecond;
    }


    public String getPlayerAccount() {
        return playerAccount;
    }

    public void setCreationDate(GregorianCalendar creationDate) {
        this.creationDate = creationDate;
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

    public String getPetName() {
        return petName;
    }

    public Integer getDamagePerSecond() {
        return damagePerSecond;
    }

    public String getCreationDateFormatter() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(creationDate.getTime());
    }
}
