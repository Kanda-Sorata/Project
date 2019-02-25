package Exception;

import Exo1.Student;

public class SectionException extends Exception {

    private String sectionError;

    public SectionException(String sectionError){
        this.sectionError = sectionError;
    }

    public String getMessage(){
        return "Les valeurs possible sont \n" + Student.listingSection() + "\nValeur utilisateur: " + sectionError;
    }
}
