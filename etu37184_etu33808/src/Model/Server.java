package Model;

import java.util.GregorianCalendar;

import BusinessLogic.Utilitie;
import Exception.UniqueNameException;
import Exception.DateException;

public class Server {
    private String name;
    private GregorianCalendar creationDate;
    private boolean playerVersusPlayer;
    private Integer nbPlayersMax;
    private static final int MIN_PLAYER = 0;
    private static final int MAX_PLAYER = 10;

    public Server(String name, int year, int month, int day, boolean playerVersusPlayer, Integer nbPlayersMax) throws UniqueNameException, DateException {
        setName(name);
        setCreationDate(year, month-1, day);
        setPlayerVersusPlayer(playerVersusPlayer);
        setNbPlayersMax(nbPlayersMax);
    }

    public String getName(){
        return name;
    }

    public void setName(String name)throws UniqueNameException{
        if(!Utilitie.estUnique("Server", name)) {
            throw new UniqueNameException(name);
        }
        else{
            this.name =  name;
        }
    }

    public GregorianCalendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int year, int month, int day) throws DateException{
        if(!Utilitie.dateAvailable(year, month, day)){
            throw new DateException(year, month, day);
        }
        else{
            creationDate = new GregorianCalendar(year, month, day);
        }
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