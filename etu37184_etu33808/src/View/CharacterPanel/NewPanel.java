package View.CharacterPanel;

import javax.swing.*;
import java.awt.*;

public class NewPanel extends JPanel {
    private FormPanelRight formPanelRight;
    private FormPanelLeft formPanelLeft;
    private ButtonsPanel buttonsPanel;

    public NewPanel(){
        //Add properties
        setLayout(new GridLayout(2, 2, 5, 15));
        //Add components
        formPanelRight = new FormPanelRight();
        formPanelLeft = new FormPanelLeft();
        buttonsPanel = new ButtonsPanel();

        add(formPanelRight);
        add(formPanelLeft);
        add(buttonsPanel);
    }

}
