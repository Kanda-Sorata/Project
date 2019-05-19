package Model;

import java.util.GregorianCalendar;

public class Server {
    private String name;
    private GregorianCalendar creationDate;
    private Boolean playerVersusPlayer;
    private Integer nbPlayersMax;
    private static final int MIN_PLAYER = 0;
    private static final int MAX_PLAYER = 10;

    public Server(String name, GregorianCalendar creationDate, Boolean playerVersusPlayer, Integer nbPlayersMax) {
        this.name = name;
        this.creationDate = creationDate;
        this.playerVersusPlayer = playerVersusPlayer;
        setNbPlayersMax(nbPlayersMax);
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNbPlayersMax(Integer nbPlayersMax) {
        if(nbPlayersMax < MIN_PLAYER || nbPlayersMax > MAX_PLAYER)
        this.nbPlayersMax = nbPlayersMax;
    }
}