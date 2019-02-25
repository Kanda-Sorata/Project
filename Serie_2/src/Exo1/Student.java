package Exo1;

import Exception.*;

public class Student extends Personne {

    private int yearBloc;
    private String section;
    private static int nbSection;
    private static String [] sections = {"comtpta", "droit", "market", "infoGestion", "technoInfo"};
    private static int nbGirls;
    private static int nbTotals;
    private static final  int NB_LEARNACT_MAX = 5;
    private LearningActivity[] learningActivities = new LearningActivity[NB_LEARNACT_MAX];
    static{
        nbSection= 5;
        nbGirls = 0;
        nbTotals = 0;
    }

    public Student(String lastName, String firstName, char sex, int year, int month, int day, int yearBloc, String section) throws SexeException, YearBlocException, SectionException {
        super(lastName, firstName, sex, year, month, day);
        setYearBloc(yearBloc);
        setSection(section);
        if(sex == 'f' || sex == 'F')
            nbGirls++;
        nbTotals++;
    }

    //Settors

    public void setYearBloc(int yearBloc) throws YearBlocException {
        if(yearBloc < 1 || yearBloc > 3)
            throw new YearBlocException(yearBloc);
        else
            this.yearBloc = yearBloc;
    }

    public void setSection(String section) throws SectionException {
        if(!isSectionAvailable(section))
            throw new SectionException(section);
        else
            this.section = section;
    }

    public void addActivities(LearningActivity act) throws TooMuchActivities{
        int place = searchPlaceAvailable();
        if(place >= NB_LEARNACT_MAX)
            throw new TooMuchActivities();
        else
            learningActivities[place] = act;
    }

    //Gettors

    public LearningActivity getLearningActivitie(int ind) throws WrongNumberActivity {
        if(ind <= 0)
            throw new WrongNumberActivity(ind);
        else {
            int place = searchPlaceAvailable();
            if (ind >= place)
                throw new WrongNumberActivity(ind, place);
            else
                return learningActivities[ind];
        }
    }

    public static double pourcGirls(){
        return (double)(nbGirls / nbTotals) * 100;
    }

    public String getUserName(){
        return section.substring(0, 2) + yearBloc + getLastName() + getFirstName().charAt(0);
    }

    //Others

    public int searchPlaceAvailable(){
        int iR = 0;

        while(iR < NB_LEARNACT_MAX && learningActivities[iR] != null){
            iR++;
        }

        return iR;
    }

    public boolean isSectionAvailable(String section){
        int iRech = 0;

        while(iRech < nbSection && (!sections[iRech].equals(section))){ iRech++; }

        return iRech != nbSection;
    }
    public static String listingSection(){
        String output = "";

        for(int i = 0; i < nbSection; i++){
            output += i+1 + ". " + sections[i] + "\n";
        }

        return output;
    }

    @Override
    public String toString(){
        String msg = getSex() == 'f' || getSex() == 'F' ? "inscrite":"inscrit";

        return super.toString() + " " + msg + " en " + yearBloc + " " + section + "\nNom utilisateur: " + getUserName();
    }
}
