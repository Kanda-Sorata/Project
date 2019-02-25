package Exo1;
import Exception.*;
import javax.swing.*;

public class Principale {
    public static void main(String[] args) {

        //Exercice 1
        /*
        try{
            Student e = new Student("Rousseaux", "Corentinn", 'm', 1997, 9, 2, 2, "infoGestion");
        }
        catch(SexeException sex) {
            JOptionPane.showMessageDialog(null, sex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch(YearBlocException year){
            JOptionPane.showMessageDialog(null, year.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch(SectionException section){
            JOptionPane.showMessageDialog(null, section.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        */

        //Exercice 2
        /*
        try{
            Student e = new Student("Rousseaux", "Corentinn", 'm', 1997, 9, 2, 2, "infoGestion");
            JOptionPane.showMessageDialog(null, e, "Confirmation d'inscription", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SexeException sex) {
            JOptionPane.showMessageDialog(null, sex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch(YearBlocException year){
            JOptionPane.showMessageDialog(null, year.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch(SectionException section){
            JOptionPane.showMessageDialog(null, section.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }*/

        //Exercice 3
        /*
        String lastName = "";
        String firstName = "";
        String sex = "";
        String year = "";
        String month = "";
        String day = "";
        String yearBloc = "";
        String section = "";
        Student e;

        String confirmation;
        do{
            lastName = JOptionPane.showInputDialog("Last name : ");
            firstName = JOptionPane.showInputDialog("First name : ");
            sex = JOptionPane.showInputDialog("sex : ");
            year = JOptionPane.showInputDialog("Year born : ");
            month = JOptionPane.showInputDialog("Month born : ");
            day = JOptionPane.showInputDialog("Day born : ");
            yearBloc = JOptionPane.showInputDialog("bloc year : ");
            section = JOptionPane.showInputDialog("Section : ");
            confirmation = JOptionPane.showInputDialog("Do you want to continue ? ");
            try{
                e = new Student(lastName, firstName, sex.charAt(0), Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(yearBloc), section);
                JOptionPane.showMessageDialog(null, e, "Information étudiant", JOptionPane.INFORMATION_MESSAGE);
                //Exercice 5
                JOptionPane.showMessageDialog(null, e.age(), "Age", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(SexeException sexException) {
                JOptionPane.showMessageDialog(null, sexException.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            catch(YearBlocException yearException){
                JOptionPane.showMessageDialog(null, yearException.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            catch(SectionException sectionException) {
                JOptionPane.showMessageDialog(null, sectionException.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        }while(confirmation.equals("O"));
            */
        //Exercie 4
        //JOptionPane.showMessageDialog(null, Student.pourcGirls() + "%", "Pourcentage filles", JOptionPane.INFORMATION_MESSAGE);

        //Exercice 6


        try {
            Student e = new Student("Rousseaux", "Corentinn", 'm', 1997, 9, 2, 2, "infoGestion");

            Teacher teacherCSharp = new Teacher("Lecler", "Christophe", 'm', 1985, 5, 2);
            Teacher teacherJavascript = new Teacher("Pirotte", "Cécile", 'f', 1987, 7, 3);
            Teacher teacherJava = new Teacher("Derwa", "Corine", 'f', 1985, 4, 3);

            LearningActivity[] activities = new LearningActivity[3];
            activities[0] = new LearningActivity("CSharp", teacherCSharp);
            activities[1] = new LearningActivity("Javascript", teacherJavascript);
            activities[2] = new LearningActivity("Java", teacherJava);

            for(int i = 0;  i < 3;  i++){
                e.addActivities(activities[i]);
                //JOptionPane.showMessageDialog(null, e.getLearningActivitie(i), "Learning activitie " + i, JOptionPane.INFORMATION_MESSAGE);
            }
           // JOptionPane.showMessageDialog(null, e.getLearningActivitie(4), "Learning activitie " + 4, JOptionPane.INFORMATION_MESSAGE);
           // JOptionPane.showMessageDialog(null, e.getLearningActivitie(-1), "Learning activitie " + -1, JOptionPane.INFORMATION_MESSAGE);
            // JOptionPane.showMessageDialog(null, e.getLearningActivitie(0), "Learning activitie " + 0, JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, teacherCSharp, "Teacher C#", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SexeException sex){
            JOptionPane.showMessageDialog(null, sex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch(YearBlocException year){
            JOptionPane.showMessageDialog(null, year.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch(SectionException section){
            JOptionPane.showMessageDialog(null, section.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        /*catch(WrongNumberActivity wrongNumAct){
            JOptionPane.showMessageDialog(null, wrongNumAct, "Erreur", JOptionPane.ERROR_MESSAGE);
        }*/
        catch(TooMuchActivities tooMuchAct){
            JOptionPane.showMessageDialog(null, tooMuchAct.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }


        System.exit(0);
    }
}
