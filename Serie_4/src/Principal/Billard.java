package Principal;

import javax.swing.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import java.awt.*;

public class Billard  extends JPanel {
    private ArrayList<Paroi> arrayVerticales = new ArrayList();
    private List<Paroi> paroisVerticales = Collections.synchronizedList(arrayVerticales);
    private ArrayList<Paroi> arrayHorizontales = new ArrayList();
    private List<Paroi> paroisHorizontales = Collections.synchronizedList(arrayHorizontales);
    private int totalPoints;

    private Balle balle;

    public Billard(){

        //borders
        paroisHorizontales.add(new Paroi(240, 60, 800, 5, 0));
        paroisHorizontales.add(new Paroi(240, 660, 800, 5, 0));
        paroisVerticales.add(new Paroi(1040, 60, 5, 605, 0));
        paroisVerticales.add(new Paroi(240, 60, 5, 600, 0));

        //obstacles
        paroisVerticales.add(new Paroi(400, 300, 5, 120, 2));
        paroisHorizontales.add(new Paroi(400, 295, 5, 5, 2));
        paroisHorizontales.add(new Paroi(400, 420, 5, 5, 2));

        paroisVerticales.add(new Paroi(720, 450, 5, 120, 2));
        paroisHorizontales.add(new Paroi(720, 445, 5, 5, 2));
        paroisHorizontales.add(new Paroi(720, 570, 5, 5, 2));

        paroisHorizontales.add(new Paroi(680, 336, 210, 5, 2));
        paroisVerticales.add(new Paroi(675, 336, 5, 5, 2));
        paroisVerticales.add(new Paroi(890, 336, 5, 5, 2));


        //Balles
        balle = new Balle(this, 750, 610, 24, 24);
        ThreadMouvement move = new ThreadMouvement(this);
        move.start();

        //Points
        totalPoints = 0;
    }

    public Balle getBalle(){
        return balle;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        for(Paroi p: paroisVerticales){
            p.dessine(g);
        }
        for(Paroi p: paroisHorizontales){
            p.dessine(g);
        }
        balle.dessine(g);
    }

    public List<Paroi> getParoisVerticales(){
        return paroisVerticales;
    }

    public List<Paroi> getParoisHorizontales(){
        return paroisHorizontales;
    }
}
