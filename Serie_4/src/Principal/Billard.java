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

    private ArrayList<Balle> arrayBalles = new ArrayList();
    private List<Balle> balles = Collections.synchronizedList(arrayBalles);

    private ArrayList<Piege> arrayPieges = new ArrayList<>();
    private List<Piege> pieges = Collections.synchronizedList(arrayPieges);

    private int totalPoints;

    public Billard(){

        //borders
        paroisHorizontales.add(new Paroi(240, 60, 800, 5, 0));
        paroisHorizontales.add(new Paroi(240, 660, 800, 5, 0));
        paroisVerticales.add(new Paroi(1040, 60, 5, 605, 0));
        paroisVerticales.add(new Paroi(240, 60, 5, 600, 0));

        //obstacles
        paroisVerticales.add(new Paroi(400, 315, 5, 120, 2));
        paroisHorizontales.add(new Paroi(400, 310, 5, 5, 2));
        paroisHorizontales.add(new Paroi(400, 435, 5, 5, 2));

        paroisVerticales.add(new Paroi(821, 185, 5, 120, 2));
        paroisHorizontales.add(new Paroi(821, 180, 5, 5, 2));
        paroisHorizontales.add(new Paroi(821, 305, 5, 5, 2));

        paroisHorizontales.add(new Paroi(480, 580, 210, 5, 2));
        paroisVerticales.add(new Paroi(475, 580, 5, 5, 2));
        paroisVerticales.add(new Paroi(690, 795, 5, 5, 2));

        paroisHorizontales.add(new Paroi(556, 452, 120, 5, 2));
        paroisVerticales.add(new Paroi(551, 452, 5, 5, 2));
        paroisVerticales.add(new Paroi(676, 452, 5, 5,2));


        //Balles
        ThreadMouvement move = new ThreadMouvement(this);
        move.start();

        //Points
        totalPoints = 0;

        //Color balle
        Thread colorBale = new ThreadCouleur(this);
        colorBale.start();

        //Pieges
        pieges.add(new Piege(856, 522, 35, 35));
        pieges.add(new Piege(286, 157, 35, 35));
        pieges.add(new Piege(583, 185, 35,35));
    }

    public List<Balle> getBalles(){
        return balles;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        for(Paroi p: paroisVerticales){
            p.dessine(g);
        }
        for(Paroi p: paroisHorizontales) {
            p.dessine(g);
        }

        for(Balle b: balles) {
            b.dessine(g);
        }

        for(Piege p: pieges){
            p.dessine(g);
        }

    }

    public List<Paroi> getParoisVerticales(){
        return paroisVerticales;
    }

    public List<Paroi> getParoisHorizontales(){
        return paroisHorizontales;
    }

    public void setTotalPoint(int points){
        totalPoints += points;
    }

    public String getTotalPoints(){
        return String.valueOf(totalPoints);
    }

    public List<Piege> getPieges(){
        return pieges;
    }

    public void ajoutBalle() {
        balles.add(new Balle(this, 952, 523, 24, 24));
    }
}
