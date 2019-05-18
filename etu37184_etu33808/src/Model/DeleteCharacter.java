package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class DeleteCharacter {
    private String name;
    private GregorianCalendar creationDate;
    private String server;

    public DeleteCharacter(String name, GregorianCalendar creationDate, String server) {
        this.name = name;
        this.creationDate = creationDate;
        this.server = server;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationDate(GregorianCalendar creationDate) {
        this.creationDate = creationDate;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getName() {
        return name;
    }

    public String getCreationDateFormatter() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(creationDate.getTime());
    }

    public String getServer() {
        return server;
    }
}
