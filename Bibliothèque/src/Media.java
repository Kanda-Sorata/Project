import com.sun.org.apache.bcel.internal.classfile.ExceptionTable;
import org.omg.IOP.ExceptionDetailMessage;

public class Media {
    private String title;
    private final int NB_GENRE = 3;
    private Genre [] genres = new Genre[NB_GENRE];
    private  double rate;
    private String format;
    private static final int NB_FORMATS = 3; //Have to sue at the least
    private static final String [] formats = {".mp4", ".mkv", ".flv"}; //TODO

    public Media(String title, String format, double rate){
        this.title = title;
        setFormat(format);
        this.rate = rate;
    }

    public void setGenre(Genre genre) throws Exception{
        int ind = inFreeLoc();
        if(ind > 2)
            throw new RuntimeException("genres[] is full");
        genres[ind] = genre;
    }

    private boolean isAvailableFormat(String formatRech){
        int iForm = 0;
        while(iForm < NB_FORMATS && formatRech.equals(formats[iForm])){ iForm++; }
        return iForm == NB_FORMATS;
    }

    public void setFormat(String formatValue){
        formatValue.toLowerCase();
        if(!isAvailableFormat(formatValue)) {
            System.out.println("Format not available, default format use \".mp4\".");
            System.out.println("Media : \n" + title + "\n" + formatValue+ "\n");
            format = formats[0];
        }
        else
            format = formatValue;
    }

    public void setRate(double rateValue){
        rate = rateValue;
    }

    public int inFreeLoc(){
        int iPlace = 0;
        while(iPlace < NB_GENRE && genres[iPlace] != null){iPlace++;}
        return iPlace;
    }
    public String getNom(){ return title; };

    public String toString(){
        return title+ "(" + rate + "/10)\n";
    }

}
