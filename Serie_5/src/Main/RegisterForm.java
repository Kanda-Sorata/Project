package Main;

import javax.swing.*;
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

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public FormPanel getFormPanel() {
        return formPanel;
    }
}