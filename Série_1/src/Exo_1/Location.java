package Exo_1;

public class Location {
    private String label;
    private int nbCivilians;

    public Location(String label, int nbCivilians){
        this.label = label;
        this.nbCivilians = nbCivilians;
    }

    public String getLabel(){
        return label;
    }

}
