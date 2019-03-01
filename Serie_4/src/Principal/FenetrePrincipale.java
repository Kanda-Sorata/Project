package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FenetrePrincipale extends JFrame {
    private Billard billard;
    private Container containeur;
    private FenetrePoints fenCompteur;
    private JPanel panel;
    private JButton buttonStart;

    public FenetrePrincipale() {
        super("Billard"); //Title
        setBounds(320, 180, 1280, 760); //Possition + taille
        addWindowListener(new WindowAdapter() { //Fermer la fenetre
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        billard = new Billard(); //panel billard

        createPanel();

        fenCompteur = new FenetrePoints(billard); //fenetre compteur

        containeur = this.getContentPane(); //Recuperer le conteneur de la fenetre

        containeur.add(billard, BorderLayout.CENTER); //Ajoyter le billard au conteneur
        containeur.add(panel, BorderLayout.SOUTH);

        setVisible(true); //Rends la fenetre visible

        setResizable(false); //Suprimer la redimension
    }

    public void createPanel() {
        buttonStart = new JButton("Start");
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                billard.ajoutBalle();
            }
        });
        buttonStart.setToolTipText("Envoyer une balle");

        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(buttonStart, BorderLayout.SOUTH);
    }
}
