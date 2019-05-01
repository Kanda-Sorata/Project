package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HomePanel extends JPanel {
    private String message;
    private JLabel messageLabel;

    public HomePanel(){
        setLayout(new FlowLayout());
        setBorder(new EmptyBorder(50, 250, 250, 250));

        //Add component and set them
        setMessage();
        messageLabel = new JLabel(message);
        add(messageLabel);
    }

    public void setMessage(){
        String css = "font-size: 32px;" +
        "font-family:Arial, Helvetica, sans-serif; " +
        "text-align:center; ";

        message = "<html>" +
                    "<h1 style = " + css + " >Bienvenue à vous!</h1>" +
                    "<p>Ce programma a été réalisé par Heintz Kelly et Rousseaux Corentin.</p>" +
                    "<p>À l'intention de Dubisy Françoise et Bertrand Chantal.</p>" +
                "</html>";
    }
}
