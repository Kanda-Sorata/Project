package View.CharacterPanel;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormPanelLeftNew extends FormPanelLeftModify {

    public FormPanelLeftNew(FormPanelRight formPanelRight) {
        super(formPanelRight, false);
        setIsModifyPanel(false);
        setLayout(new GridLayout(4, 2, 5, 15));
        setBorder(new EmptyBorder(150, 1, 175, 0));
        revalidate();
        repaint();
    }
}
