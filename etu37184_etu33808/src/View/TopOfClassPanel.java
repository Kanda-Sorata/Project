package View;

import Controller.CharacterClassController;
import Exception.DataAccessException;
import Exception.DataException;
import Exception.DivideException;
import Model.TopOfClass;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class TopOfClassPanel extends JPanel {
    private CharacterClassController characterClassController;
    private JTable table;
    private TopOfClassModel topOfClassModel;
    private JScrollPane scrollPane;
    private ArrayList<TopOfClass> topOfClasses;

    public TopOfClassPanel() throws DataAccessException, DataException, DivideException {
        //Add properties
        setLayout(new FlowLayout());
        setBorder(new EmptyBorder(150, 0, 100, 0)); //Top, left, bottom, right

        characterClassController = new CharacterClassController();

        //Add components
        topOfClasses = characterClassController.getAllCharacterClassOrderClass();
        topOfClassModel = new TopOfClassModel(topOfClasses);
        table = new JTable(topOfClassModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 200));
        add(scrollPane);

    }

}
