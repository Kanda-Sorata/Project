package Model;

import java.text.DecimalFormat;

public class TopOfClass {
    private Integer nbCharacters;
    private String className;
    private String description;
    private Double percent;

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


    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public String getPercentFormatter() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(percent) + "%";
    }
}
