package Model;

import java.text.DecimalFormat;

public class TopOfClass {
    private int nbCharacters;
    private String className;
    private String description;
    private double purcent;

    public TopOfClass(int nbCharacters, String className, String description) {
        this.nbCharacters = nbCharacters;
        this.className = className;
        this.description = description;
    }

    public int getNbCharacters() {
        return nbCharacters;
    }

    public String getClassName() {
        return className;
    }


    public String getDescription() {
        return description;
    }


    public void setPurcent(double purcent) {
        this.purcent = purcent;
    }

    public String getPurcentFormater() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(purcent) + "%";
    }
}
