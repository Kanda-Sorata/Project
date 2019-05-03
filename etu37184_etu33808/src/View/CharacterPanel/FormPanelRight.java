package View.CharacterPanel;

import javax.swing.*;
import javax.swing.plaf.SliderUI;
import java.awt.*;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

public class FormPanelRight extends JPanel {
    private JLabel nameLabel;
    private JLabel healthPointLabel;
    private JLabel isStuffedLabel;
    private JLabel creationDateLabel;
    private JLabel petNameLabel;
    private JLabel damagePerSecondLabel;

    private JTextField nameField;
    private JSlider healthPointSlider;  //not editable and setValue
    private JCheckBox isStuffedCheckBox;
    private JSpinner creationDate;
    private JTextField petNameField;
    private JSlider damagePerSecond; //not editable & setValue

    private Hashtable<Integer, JLabel> sliderLabels; //Key must be, Value must been an object

    public FormPanelRight(){
        setLayout(new GridLayout(6, 2, 5, 15));

        nameLabel = new JLabel("Name");
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nameField = new JTextField("Enter the name of your character");
        nameField.setColumns(15);

        healthPointLabel = new JLabel("Health point");
        healthPointLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        healthPointSlider = new JSlider(JSlider.HORIZONTAL);
        setHealthPointSlider(0, 50000, 25000); //TODO SLIDERMETHOD

        isStuffedLabel = new JLabel("Is already stuffed");
        isStuffedCheckBox = new JCheckBox();
        creationDateLabel = new JLabel("Creational date");
        petNameLabel = new JLabel("Pet name");
        damagePerSecondLabel = new JLabel("Damage per second");

        add(nameLabel);
        add(nameField);
        add(healthPointLabel);
        add(healthPointSlider);
        /*add(isStuffedLabel);
        add(isStuffedCheckBox);
        add(creationDateLabel);
        add(creationDate);
        add(petNameLabel);
        add(petNameField);
        add(damagePerSecondLabel);
        add(damagePerSecond);*/
    }

    public void setHealthPointSlider(int minimum, int maximum, int value){
        sliderLabels = new Hashtable<>();
        sliderLabels.put(minimum, new JLabel("Minimum :" + minimum));
        sliderLabels.put(maximum, new JLabel("Maximum :" + maximum));
        sliderLabels.put(value, new JLabel("Value(current) :" + value));

        healthPointSlider.setMinimum(minimum);
        healthPointSlider.setMaximum(maximum);
        healthPointSlider.setValue(value);
        healthPointSlider.setLabelTable(sliderLabels);
        healthPointSlider.setPaintLabels(true);
        healthPointSlider.setMajorTickSpacing(10000);
        healthPointSlider.setMinorTickSpacing(5000);
        healthPointSlider.setPaintTicks(true);
        healthPointSlider.setEnabled(false);
    }

}
