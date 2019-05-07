package View.CharacterPanel;

import View.Frame;

import javax.swing.*;
import java.awt.*;

public class ModifyPanel extends JPanel {

    private FormPanelLeftModify formPanelLeftModify;
    private FormPanelRight formPanelRight;
    private ButtonsPanel buttonsPanel;
    private Frame frame;

    public ModifyPanel(Frame frame){
        //Add properties
        setLayout(new BorderLayout());

        this.frame = frame;
        frame.setTitle("");
        frame.setTitle(frame.getTitle() + "- Modify a character");
        //Add components
        buttonsPanel = new ButtonsPanel();
        formPanelRight = new FormPanelRight(buttonsPanel);
        formPanelLeftModify = new FormPanelLeftModify(formPanelRight,true);
        buttonsPanel.setFormPanelRight(formPanelRight);
        buttonsPanel.setFrame(frame);
        buttonsPanel.setFormPanelLeftModify(formPanelLeftModify);

        if(frame.getHaveSavedValue()){
            buttonsPanel.setFormValue();
        }

        add(formPanelLeftModify, BorderLayout.WEST);
        add(formPanelRight, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

}