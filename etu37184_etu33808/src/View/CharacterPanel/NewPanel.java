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
        formPanelRight = new FormPanelRight(buttonsPanel);
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
