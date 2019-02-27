package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FenetrePrincipale extends JFrame {
    private Billard billard;
    private Container containeur;

    public FenetrePrincipale(){
        super("Billard"); //Titre
        setBounds(320, 180, 1280, 720); //Possition + taille
        addWindowListener(new WindowAdapter() { //Fermer la fenetre
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        billard = new Billard(); //panel billard

        containeur = this.getContentPane(); //Recuperer le conteneur de la fenetre

        containeur.add(billard); //Ajoyter le billard au conteneur

        setVisible(true); //Rends la fenetre visible

        setResizable(false); //Suprimer la redimension
    }
}
