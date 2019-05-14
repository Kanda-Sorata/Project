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
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 100, 80, 100)); //Top, left, bottom, right

        this.frame = frame;

        //Add components
        label = new JLabel("<html><p>The feature you would like to access got a trouble. Perhaps it will be fix " +
                "later.</p> <p>For the moment, you can come back home by the Menu \"Application\" > \"Home\" " +
                "or click on the button bellow.</p><p>We apologise for this inconvenient.</p></html>");

        backHome = new JButton("Home");
        backHome.setFont(new Font("Arial", Font.BOLD, 12));
        buttonListener = new ButtonListener();
        backHome.addActionListener(buttonListener);

        add(label, BorderLayout.CENTER);
        add(backHome, BorderLayout.SOUTH);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            frame.getContainer().removeAll();
            frame.setTitleFrame("Home");
            frame.getContainer().add(new HomePanel());
            frame.getContainer().revalidate();
        }
    }
}
