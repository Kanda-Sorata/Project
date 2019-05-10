package View;

import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel {
    private Frame frame;
    private String message;
    private JLabel messageLabel;

    public HelpPanel(Frame frame){
        this.frame = frame;
        setLayout(new FlowLayout());

        frame.setTitle("");
        frame.setTitle(frame.getTitle() + "- Help");

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
                "<h1 style = " + cssHeader + " >Welcome to you !</h1>" +
                "<p style = 'text-align:center'>This software has been realised by Heintz Kelly and Rousseaux Corentin.</p>" +
                "<p style = 'text-align:center'>To the intention of Dubisy Françoise and Bertrand Chantal.</p>" +
                "<h3 style = " + cssOffer + ">Here are the features that you can find into the menu \"Character\"</h3>" +
                "<ul><li>Add a character to a player</li>" +
                "<li>Modify a character from a player</li>" +
                "<li>Delete a character from a player</li> " +
                "<li>Display all character from a player</li></ul>" +
                "<h3 style = " + cssOffer + ">You can also find this search in the menu \"Search\"</h3>" +
                "<ul><li>Search for games based on an release date</li>" +
                "<li>Search for information on the spells of an player</li>" +
                "<li>Search for the effects of the skills available for the desired class</li></ul>" +
                "</html>";
    }
}
