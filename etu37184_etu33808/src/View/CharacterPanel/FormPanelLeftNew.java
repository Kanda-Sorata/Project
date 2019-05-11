package View.CharacterPanel;

import Exception.DataAccessException;
import Exception.DataException;

public class FormPanelLeftNew extends FormPanelLeftModify {

    public FormPanelLeftNew(FormPanelRight formPanelRight) throws DataAccessException, DataException {
        super(formPanelRight, false);
        setIsModifyPanel(false);
    }
}
