package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HomePanel extends JPanel {
    private String message;
    private JLabel messageLabel;

    public HomePanel(){
        setLayout(new FlowLayout());
        setBorder(new EmptyBorder(20, 250, 250, 250));

        //Add component and set them
        setMessage();
        messageLabel = new JLabel(message);
        add(messageLabel);
    }

    public void setMessage(){
        String cssHeader = "font-size: 32px;" +
                "font-family:Arial, Helvetica, sans-serif; " +
                "text-align:center; ";
        String cssOffer = "text-align: center;";
        message = "<html>" +
                "<h1 style = " + cssHeader + " >Bienvenue à vous!</h1>" +
                "<p style = 'text-align:center'>Ce programme a été réalisé par Heintz Kelly et Rousseaux Corentin.</p>" +
                "<p style = 'text-align:center'>À l'intention de Dubisy Françoise et Bertrand Chantal.</p>" +
                "<h3 style = " + cssOffer + ">Voici les fonctionnalités que vous pouvez trouver dans le menu \"Character\"</h3>" +
                "<ul><li>Ajouter un personnage à un joueur</li><li>Modifier un personnage d'un joueur</li>" +
                "<li>Supprimer un personnage d'un joueur</li><li>Afficher les personnages d'un joueur</li></ul>" +
                "<h3 style = " + cssOffer + ">Vous pouvez également trouver ces recherches dans le menu \"Search\"</h3>" +
                "<ul><li>Rechercher des jeux en fonction de leur date de sortie</li>"+
                "<li>Rechercher les informations sur les pouvoirs d'un compte joueur</li>" +
                "<li>Rechercher les effets des pouvoirs disponibles pour la classe désirée</li></ul>" +
                "</html>";
    }
}