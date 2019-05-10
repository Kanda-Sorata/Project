package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HomePanel extends JPanel {

    public HomePanel(Frame frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;

        setLayout(new FlowLayout());

        frame.setTitle("");
        frame.setTitle(frame.getTitle() + "- Home");

        //Image
        try{
            Image image = ImageIO.read(new File("./image.png"));
            Image imageResize = image.getScaledInstance(width/2+100, height/2+30, Image.SCALE_DEFAULT);
            JLabel label = new JLabel (new ImageIcon(imageResize));
            add(label);
        } catch (IOException exception){
            JOptionPane.showMessageDialog(null, "The background didn't load, we apologize for this.\nThis error is not going to impact your experience", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}