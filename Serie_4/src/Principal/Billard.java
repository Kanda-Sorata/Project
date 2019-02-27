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

    public Billard(){
        arrayVerticales.add(new Paroi(150, 100, 1, 1));
        arrayVerticales.add(new Paroi(150, 900, 1, 1));
        arrayHorizontales.add(new Paroi(150, 100, 1, 1));
        arrayHorizontales.add(new Paroi(950, 100, 1, 1));
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        for(Paroi p: arrayVerticales){
            p.dessine(g);
        }

        for(Paroi p: arrayHorizontales){
            p.dessine(g);
        }
    }
}
