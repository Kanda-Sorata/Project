package Principal;

public class ThreadMouvement extends Thread{
    private Billard billard;

    public ThreadMouvement(Billard billard){
        this.billard = billard;
    }

    public void run(){
        while(true) {
            try {
                Thread.sleep(5);
                for(Balle b: billard.getBalles()) {
                    b.bouge();
                }
                billard.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
