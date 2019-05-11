package View.CharacterPanel;

import Exception.DataAccessException;
import Exception.DataException;
import View.Frame;

import javax.swing.*;
import java.awt.*;

public class ModifyPanel extends JPanel {

    private FormPanelLeftModify formPanelLeftModify;
    private FormPanelRight formPanelRight;
    private ButtonsPanel buttonsPanel;
    private Frame frame;

    public ModifyPanel(Frame frame) throws DataAccessException, DataException {
        //Add properties
        setLayout(new BorderLayout());

        this.frame = frame;

        //Add components
        buttonsPanel = new ButtonsPanel();
        formPanelRight = new FormPanelRight(buttonsPanel);
        formPanelLeftModify = new FormPanelLeftModify(formPanelRight,true);
        buttonsPanel.setFormPanelRight(formPanelRight);
        buttonsPanel.setFrame(this.frame);
        buttonsPanel.setFormPanelLeftModify(formPanelLeftModify);

        if (frame.getSavedValueFormModify().getHaveSavedValue()) {
            buttonsPanel.setFormValue();
        }

        add(formPanelLeftModify, BorderLayout.WEST);
        add(formPanelRight, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}