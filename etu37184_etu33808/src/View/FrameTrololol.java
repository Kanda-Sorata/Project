package View;

import javax.swing.*;
import java.awt.*;
//to delete

public class FrameTrololol extends JFrame {

        private Container conteneur;
        private PanelTrololol panel;

        public FrameTrololol(){
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            setSize(width/4, height/4);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            panel = new PanelTrololol(this);
            conteneur = getContentPane();

            conteneur.add(panel, BorderLayout.CENTER);
            setVisible(true);
        }
}
