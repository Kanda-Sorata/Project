package Principal;

public class ThreadCouleur extends Thread {
    private Billard billard;

    public ThreadCouleur(Billard billard){
        this.billard = billard;
    }

    public void run(){
        while(true){
            try {
                Thread.sleep(200);
                for(Balle b: billard.getBalles()) {
                    b.changeColor();
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
