package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FenetrePoints extends JFrame {

    private Billard billard;
    private Container container;
    private Compteur compteur;

    public FenetrePoints(Billard billard) {
        super("Point(s)"); //Title
        this.billard = billard;

        setBounds(320, 180, 100, 100); //Possition + taille
        addWindowListener(new WindowAdapter() { //Fermer la fenetre
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        compteur = new Compteur(billard);

        container = this.getContentPane(); //récupèrer le container de la fenêtre
        container.add(compteur); //ajout élément

        setVisible(true);
        setResizable(false);

    }
}
