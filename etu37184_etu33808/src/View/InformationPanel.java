package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformationPanel extends JPanel {
    private JLabel label;
    private JButton backHome;
    private ButtonListener buttonListener;
    private Frame frame;

    public InformationPanel(Frame frame) {
        //Add properties
        setLayout(new GridLayout(2, 2));
        setBorder(new EmptyBorder(150, 100, 80, 100)); //Top, left, bottom, right

        this.frame = frame;

        //Add components
        label = new JLabel("<html><p>The feature you would like to access got a trouble. Perhaps itt will fix further.</p>" +
                "<p>For the moment, you can come bach home by Menu \"Application\" > \"Home\" " +
                "or click on the button bellow.</p><p>We apologise for this inconvenient.</p></html>");

        backHome = new JButton("Home");

        buttonListener = new ButtonListener();
        backHome.addActionListener(buttonListener);

        add(label);
        add(backHome);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            frame.getContainer().removeAll();
            frame.getContainer().add(new HomePanel(frame));
            frame.getContainer().revalidate();
            frame.getContainer().repaint();
        }
    }
}
