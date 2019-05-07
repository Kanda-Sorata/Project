package Model;

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

    public void setNbCharacters(int nbCharacters) {
        this.nbCharacters = nbCharacters;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPurcent() {
        return purcent;
    }

    public void setPurcent(double purcent) {
        this.purcent = purcent;
    }
}
