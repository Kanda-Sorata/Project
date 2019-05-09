package View;

import Controller.CharacterClassController;
import Exception.DataAccessException;
import Exception.DataException;
import Model.TopOfClass;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TopOfClassPanel extends JPanel {
    private CharacterClassController characterClassController;
    private JTable table;
    private TopOfClassModel topOfClassModel;
    private JScrollPane scrollPane;
    private ArrayList<TopOfClass> topOfClasses;

    public TopOfClassPanel(Frame frame) {
        //Add properties
        setLayout(new FlowLayout());
        frame.setTitle("");
        frame.setTitle(frame.getTitle() + "- Top of Class");


        characterClassController = new CharacterClassController();

        try {
            topOfClasses = characterClassController.getAllCharacterClassOrderClass(); //TODO ASSERT
            topOfClassModel = new TopOfClassModel(topOfClasses);
            table = new JTable(topOfClassModel);
            scrollPane = new JScrollPane(table);

            add(scrollPane);
        }catch (DataAccessException dataAccessException){
            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch(DataException dataException) {
            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
