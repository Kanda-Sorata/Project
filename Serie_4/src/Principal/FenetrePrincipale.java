package Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FenetrePrincipale extends JFrame implements ActionListener {
    private Billard billard;
    private Container containeur;
    private FenetrePoints fenCompteur;
    private JPanel panel;
    private JButton button;

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

        button = new JButton("Start");

        createPanel();

        fenCompteur = new FenetrePoints(billard); //fenetre compteur

        containeur = this.getContentPane(); //Recuperer le conteneur de la fenetre

        containeur.add(billard, BorderLayout.CENTER); //Ajoyter le billard au conteneur
        containeur.add(panel, BorderLayout.SOUTH);

        setVisible(true); //Rends la fenetre visible

        setResizable(false); //Suprimer la redimension
    }

    public void createPanel(){
        button = new JButton(" Start ");
        button.addActionListener(this);

        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(button, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e){
        billard.getBalles().add(new Balle(billard, 800, 540, 24, 24));
    }
}
