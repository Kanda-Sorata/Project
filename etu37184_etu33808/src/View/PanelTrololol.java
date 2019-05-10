package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//to delete

public class PanelTrololol extends JPanel {

    public PanelTrololol(FrameTrololol frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;

        setLayout(new FlowLayout());

        frame.setTitle("");
        frame.setTitle(frame.getTitle() + "- COUCOU PAKI");

        //Image
        try{
            Image image = ImageIO.read(new File("./Paki.png"));
            Image imageResize = image.getScaledInstance(width/4, height/4, Image.SCALE_DEFAULT);
            JLabel label = new JLabel (new ImageIcon(imageResize));
            add(label);
        } catch (IOException exception){
            //joption pane
        }

    }
}
