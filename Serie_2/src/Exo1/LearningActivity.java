package Exo1;

public class LearningActivity {
    private String label;
    private Teacher teacher;

    public LearningActivity(String label, Teacher teacher){
        this.label = label;
        this.teacher = teacher;
    }

    @Override
    public String toString(){
        return "L'activitée d'apprentissage intitulée " + label + " donnée par " + teacher;
    }
}
