package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameHelp extends JFrame {
    private Container container;
    private BackButtonPanel backButtonPanel;

    public FrameHelp(){
        //Generale
        super("My beautiful aide!");

        // make the frame half the height and width
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        setSize(width/3, height/3);

        // here's the part where i center the jframe on screen
        setLocationRelativeTo(null);

        //Contenu
        container = getContentPane();
        addWindowListener(new WindowAdapter() { //Fermer la fenetre sans ferme le programme principal
            @Override
            public void windowClosing(WindowEvent windowEvent) {
               dispose();
            }
        });

        BackButtonPanel panelButton = new BackButtonPanel();
        PanelGeneralHelp panelGeneralHelp = new PanelGeneralHelp();
        container.add(panelGeneralHelp, BorderLayout.CENTER);
        container.add(panelButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class PanelGeneralHelp extends JPanel{
        private JLabel labHelp;

        public PanelGeneralHelp() {
            setLayout(new BorderLayout());
            labHelp = new JLabel("<html><h1><b>Aide</b></h1><p>Help about SHD Network</p></html>");
            labHelp.setHorizontalAlignment(SwingConstants.CENTER);
            add(labHelp);
        }
    }

    private class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            dispose();
        }
    }

    private class BackButtonPanel extends JPanel{
        private JButton back;

        public BackButtonPanel(){
            setLayout(new FlowLayout());
            back = new JButton("Back to home window");
            back.addActionListener(new BackListener());
            add(back);

        }
    }
}
