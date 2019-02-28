package Principal;

public class ThreadComptagePoints extends Thread{
    private Compteur compteur;

    public ThreadComptagePoints(Compteur compteur){
        this.compteur = compteur;
    }

    public void run(){
       while(true){
           try{
               Thread.sleep(1);
                compteur.repaint();
           } catch (InterruptedException e){
               e.printStackTrace();
           }
       }
    }
}
