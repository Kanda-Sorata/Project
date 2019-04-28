package Model;

import BusinessLogic.Utilitie;
import Exception.UniqueNameException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class SearchGameList {
    private String name;
    private GregorianCalendar releaseDate;
    private String server;

    public SearchGameList(String name, GregorianCalendar releaseDate, String server) throws UniqueNameException{
        setName(name);
        setReleaseDate(releaseDate);
        setServer(server);
    }

    public void setName(String name)throws UniqueNameException {
        if(!Utilitie.estUnique("Server", name)){
            throw new UniqueNameException(name);
        }
        else{
            this.name =  name;
        }
    }

    public void setReleaseDate(GregorianCalendar releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getName() {
        return name;
    }

    public GregorianCalendar getReleaseDate() {
        return releaseDate;
    }

    public String getServer() {
        return server;
    }

    public String getReleaseDateStringFormat(){
        DateFormat dateFormat = new SimpleDateFormat("DD/MMMM/YYYY");
        return dateFormat.format(releaseDate.getTime());
    }
}
