package Principal;

import javax.swing.*;
import java.awt.*;

public class Compteur extends JPanel {
    private Billard billard;

    public Compteur(Billard billard){
        this.billard = billard;

        Thread compteur = new ThreadComptagePoints(this);
        compteur.start();
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.drawString("Points obtenus : ", 30, 40);
        g.setColor(Color.BLACK);
        g.drawString(billard.getTotalPoints(), 60, 55);
    }


}
