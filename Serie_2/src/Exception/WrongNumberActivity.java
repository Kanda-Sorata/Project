package Exception;

public class WrongNumberActivity extends Exception {

    private int wrongNumAct;
    private int place;

    public WrongNumberActivity(int wrongNumAct){
        this.wrongNumAct = wrongNumAct;
    }

    public WrongNumberActivity(int wrongNumAct, int place){
        this(wrongNumAct);
        this.place = place;
    }

    public String getMessage(){
        String msg = "";
        if(wrongNumAct <= 0)
            msg = "Le numéro d’activité d’apprentissage " + wrongNumAct + " que vous avez proposé est <=0";
         if(wrongNumAct >= place)
            msg =  "L’étudiant ne suit que " + place + " activités d’apprentissage. Le numéro " + wrongNumAct + " que vous avez proposé ne correspond donc pas à une activité d’apprentissage de cet étudiant";

         return msg;
    }
}
