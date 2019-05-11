package View.CharacterPanel;

import Exception.DataAccessException;
import Exception.DataException;
import View.Frame;

import javax.swing.*;
import java.awt.*;

public class NewPanel extends JPanel {
    private FormPanelRight formPanelRight;
    private FormPanelLeftNew formPanelLeftNew;
    private ButtonsPanel buttonsPanel;
    private Frame frame;

    public NewPanel(Frame frame) throws DataAccessException, DataException {
        //Add properties
        setLayout(new BorderLayout());

        this.frame = frame;

        //Add components
        buttonsPanel = new ButtonsPanel();
        formPanelRight = new FormPanelRight(buttonsPanel);
        formPanelLeftNew = new FormPanelLeftNew(formPanelRight);
        buttonsPanel.setFormPanelRight(formPanelRight);
        buttonsPanel.setFrame(this.frame);
        buttonsPanel.setFormPanelLeftModify(formPanelLeftNew);

        if (frame.getSavedValueFormNew().getHaveSavedValue()) {
            buttonsPanel.setFormValue();
        }

        add(formPanelLeftNew, BorderLayout.WEST);
        add(formPanelRight, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}
