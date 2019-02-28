package Principal;

public class ThreadMouvement extends Thread{
    private Billard billard;

    public ThreadMouvement(Billard billard){
        this.billard = billard;
    }

    public void run(){
        while(true) { //Pac correct à modifier
            try {
                Thread.sleep(5);
                billard.getBalle().bouge();
                billard.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
