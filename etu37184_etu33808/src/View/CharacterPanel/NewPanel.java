package View.CharacterPanel;

import View.Frame;

import javax.swing.*;
import java.awt.*;

public class NewPanel extends JPanel {
    private FormPanelRight formPanelRight;
    private FormPanelLeftNew formPanelLeftNew;
    private ButtonsPanel buttonsPanel;
    private Frame frame;

    public NewPanel(Frame frame){
        //Add properties
        setLayout(new BorderLayout());

        this.frame = frame;
        frame.setTitle("");
        frame.setTitle(frame.getTitle() + "- Add a new character");

        //Add components
        buttonsPanel = new ButtonsPanel();
        formPanelRight = new FormPanelRight(buttonsPanel);
        formPanelLeftNew = new FormPanelLeftNew(formPanelRight);
        buttonsPanel.setFormPanelRight(formPanelRight);
        buttonsPanel.setFrame(frame);
        buttonsPanel.setFormPanelLeftModify(formPanelLeftNew);

        if(frame.getHaveSavedValue()){
            buttonsPanel.setFormValue();
        }

        add(formPanelLeftNew, BorderLayout.WEST);
        add(formPanelRight, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

}
