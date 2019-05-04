package View.CharacterPanel;

import View.Frame;

import javax.swing.*;
import java.awt.*;

public class NewPanel extends JPanel {
    private FormPanelRight formPanelRight;
    private FormPanelLeft formPanelLeft;
    private ButtonsPanel buttonsPanel;
    private Frame frame;

    public NewPanel(Frame frame){
        //Add properties
        setLayout(new BorderLayout());

        this.frame = frame;

        //Add components
        formPanelLeft = new FormPanelLeft();
        buttonsPanel = new ButtonsPanel();
        buttonsPanel.setParentPanel(this);
        formPanelRight = new FormPanelRight(buttonsPanel);
        buttonsPanel.setFormPanelRight(formPanelRight);

        add(formPanelLeft, BorderLayout.WEST);
        add(formPanelRight, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public Frame getFrame(){
        return frame;
    }

}
