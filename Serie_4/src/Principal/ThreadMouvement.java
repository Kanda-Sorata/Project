package Principal;

public class ThreadMouvement extends Thread{
    private Billard billard;

    public ThreadMouvement(Billard billard){
        this.billard = billard;
    }

    public void run(){
        while(true) {
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for(Balle b: billard.getBalles()) {
                b.bouge();
            }

            for(int i = 0; i < billard.getBalles().size(); i++){
                if(billard.getBalles().get(i).getASupprimer()){
                    billard.getBalles().remove(i);
                }
            }

            billard.repaint();
        }
    }
}
