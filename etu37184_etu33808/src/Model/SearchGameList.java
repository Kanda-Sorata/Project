package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class SearchGameList {
    private String name;
    private GregorianCalendar releaseDate;
    private String server;

    public SearchGameList(String name, GregorianCalendar releaseDate, String server){
        setName(name);
        setReleaseDate(releaseDate);
        setServer(server);
    }

    public void setName(String name) {
        this.name =  name;
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
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(releaseDate.getTime());
    }

}
