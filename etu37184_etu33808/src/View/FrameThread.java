package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameThread extends JFrame {
    private Container container;
    private PanelThread panelThread;
    private Frame frameParent;
    private ThreadCounts threadCounts;

    public FrameThread(Frame frameParent) {
        //Add properties
        setTitle("Counters");

        //Init
        container = this.getContentPane();
        panelThread = new PanelThread();
        this.frameParent = frameParent;
        setFrame();

        //Thread
        threadCounts = new ThreadCounts(panelThread);
        threadCounts.start();

        //Add components
        container.add(panelThread);

        setVisible(true);
    }

    private void setFrame() {
        //set the frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        pack();
        setSize(width / 5 - 50, height / 5);

        setLocation(frameParent.getLocationThreadX(), frameParent.getLocationThreadY());
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                SingletonThread.close();
                dispose(); //Close only the window selected by the mouse
            }
        });
    }
}
