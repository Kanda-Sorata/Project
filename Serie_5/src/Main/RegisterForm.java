package Main;

import javax.swing.*;
import java.awt.*;

public class RegisterForm extends JPanel {

    public RegisterForm() {
        setLayout(new BorderLayout());
        FormPanel formPanel = new FormPanel();
        ButtonPanel buttonPanel = new ButtonPanel();

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

}