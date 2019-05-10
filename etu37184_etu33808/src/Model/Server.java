package Model;

import java.util.GregorianCalendar;

public class Server {
    private String name;
    private GregorianCalendar creationDate;
    private boolean playerVersusPlayer;
    private Integer nbPlayersMax;
    private static final int MIN_PLAYER = 0;
    private static final int MAX_PLAYER = 10;

    public Server(String name, GregorianCalendar creationDate, boolean playerVersusPlayer, Integer nbPlayersMax) {
        setName(name);
        setCreationDate(creationDate);
        setPlayerVersusPlayer(playerVersusPlayer);
        setNbPlayersMax(nbPlayersMax);
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GregorianCalendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(GregorianCalendar creationDate) { //Add validationTest
        this.creationDate = creationDate;
    }


    public boolean isPlayerVersusPlayer() {
        return playerVersusPlayer;
    }

    public void setPlayerVersusPlayer(boolean playerVersusPlayer) {
        this.playerVersusPlayer = playerVersusPlayer;
    }

    public Integer getNbPlayersMax() {
        return nbPlayersMax;
    }

    public void setNbPlayersMax(Integer nbPlayersMax) {
        if(nbPlayersMax < MIN_PLAYER || nbPlayersMax > MAX_PLAYER)
        this.nbPlayersMax = nbPlayersMax;
    }

}