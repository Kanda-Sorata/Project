package View.CharacterPanel;

import View.Frame;

import javax.swing.*;
import java.awt.*;

public class ModifyPanel extends JPanel {

    private FormPanelLeft formPanelLeft;
    private FormPanelRight formPanelRight;
    private ButtonsPanel buttonsPanel;
    private Frame frame;

    public ModifyPanel(Frame frame){
        //Add properties
        setLayout(new BorderLayout());

        this.frame = frame;

        //Add components
        buttonsPanel = new ButtonsPanel();
        formPanelRight = new FormPanelRight(buttonsPanel);
        formPanelLeft = new FormPanelLeft(formPanelRight);
        buttonsPanel.setFormPanelRight(formPanelRight);
        buttonsPanel.setFrame(frame);
        buttonsPanel.setFormPanelLeft(formPanelLeft);

        if(frame.getHaveSavedValue()){
            buttonsPanel.setFormValue();
        }

        add(formPanelLeft, BorderLayout.WEST);
        add(formPanelRight, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

}