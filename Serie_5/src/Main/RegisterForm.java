package Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegisterForm extends JPanel {
    private Frame frame;
    private FormPanel formPanel;
    private ButtonPanel buttonPanel;

    public RegisterForm(Frame frame) {
        setLayout(new BorderLayout());

        this.frame = frame;

        formPanel = new FormPanel();
        buttonPanel = new ButtonPanel(frame);

        formPanel.setBorder(new EmptyBorder(50, 250, 50, 350));
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public FormPanel getFormPanel() {
        return formPanel;
    }
}