package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameThread extends JFrame {
    private Container container;
    private Frame frame;
    private PanelThread panelThread;

    public FrameThread(Frame frame){
        container = this.getContentPane();
        this.frame = frame;
        setFrame();
        panelThread = new PanelThread();
        setTitle("Counters");
        try {
            //Set the required look and feel
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //Update the component tree - associate the look and feel with the given frame.
            SwingUtilities.updateComponentTreeUI(this);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        container.add(panelThread);
        setVisible(true);
    }

    public void setFrame(){
        //set the frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        pack();
        setSize(width/4, height/4);

        // here's the part where i put the jframe on the other one
        setLocationRelativeTo(null);

        setResizable(false);

        addWindowListener(new WindowAdapter() { //Fermer la fenetre
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }
}
