package Model;

import java.text.DecimalFormat;

public class TopOfClass {
    private Integer nbCharacters;
    private String className;
    private String description;
    private Double purcent;

    public TopOfClass(Integer nbCharacters, String className, String description) {
        this.nbCharacters = nbCharacters;
        this.className = className;
        this.description = description;
    }

    public Integer getNbCharacters() {
        return nbCharacters;
    }

    public String getClassName() {
        return className;
    }


    public String getDescription() {
        return description;
    }


    public void setPurcent(Double purcent) {
        this.purcent = purcent;
    }

    public String getPurcentFormater() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(purcent) + "%";
    }
}
