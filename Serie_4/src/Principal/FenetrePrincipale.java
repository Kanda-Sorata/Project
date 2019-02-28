package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FenetrePrincipale extends JFrame {
    private Billard billard;
    private Container containeur;
    private FenetrePoints fenCompteur;

    public FenetrePrincipale(){
        super("Billard"); //Title
        setBounds(320, 180, 1280, 760); //Possition + taille
        addWindowListener(new WindowAdapter() { //Fermer la fenetre
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        billard = new Billard(); //panel billard

        fenCompteur = new FenetrePoints(billard); //fenetre compteur

        containeur = this.getContentPane(); //Recuperer le conteneur de la fenetre

        containeur.add(billard); //Ajoyter le billard au conteneur


        setVisible(true); //Rends la fenetre visible

        setResizable(false); //Suprimer la redimension
    }
}
