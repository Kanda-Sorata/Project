import java.util.GregorianCalendar;

public class Film extends Media{
    private String author;
    private int durée; // hh : mm //TODO

    public Film(String title, String author, int durée, double rate, String format){
        super(title, format, rate);
        this.author = author;
        this.durée = durée;
    }

    @Override
    public String toString(){ return super.toString() + "Author:" + author + "(" + durée + ")\n"; }

}
