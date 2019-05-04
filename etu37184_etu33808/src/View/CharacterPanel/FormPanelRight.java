package View.CharacterPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.*;

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
    private JSpinner creationDateSpinner;
    private JTextField petNameField;
    private JSlider damagePerSecondSlider; //not editable & setValue

    private Hashtable<Integer, JLabel> sliderLabels; //Key must be, Value must been an object
    private SpinnerDateModel dateModel;
    private JSpinner.DateEditor dateEditor;
    private Date date;

    private ButtonsPanel buttonsPanel;

    private SliderListener sliderListener;


    public FormPanelRight(ButtonsPanel buttonsPanel){
        //Add propetiers
        setLayout(new GridLayout(6, 2, 5, 15));
        setBorder(new EmptyBorder(80, 0, 80, 50)); //Top, left, bottom, right

        //Add link panel
        this.buttonsPanel = buttonsPanel;

        //Add Component
        nameLabel = new JLabel("Name");
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nameField = new JTextField();
        nameField.setColumns(15);
        nameField.setToolTipText("Enter the name of your character");


        healthPointLabel = new JLabel("Health point");
        healthPointLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        healthPointSlider = new JSlider(JSlider.HORIZONTAL);
        setHealthPointSlider(0, 50000, 25000); //TODO SLIDERMETHOD

        isStuffedLabel = new JLabel("");
        isStuffedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        isStuffedCheckBox = new JCheckBox("Is already stuffed");
        isStuffedCheckBox.setHorizontalAlignment(SwingConstants.RIGHT);

        creationDateLabel = new JLabel("Creational date");
        creationDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        creationDateSpinner = new JSpinner();
        date = new GregorianCalendar().getTime();
        dateModel = new SpinnerDateModel(date, date, date, Calendar.MONTH);
        creationDateSpinner.setModel(dateModel);
        dateEditor = new JSpinner.DateEditor(creationDateSpinner, "dd/MM/yyyy");
        creationDateSpinner.setEditor(dateEditor);
        creationDateSpinner.setEnabled(false);

        petNameLabel = new JLabel("Pet name");
        petNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        petNameField = new JTextField();
        petNameField.setColumns(15);
        petNameField.setToolTipText("Name of your pet if you have one");

        damagePerSecondLabel = new JLabel("Damage per second");
        damagePerSecondLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        damagePerSecondSlider = new JSlider(JSlider.HORIZONTAL);
        sliderListener = new SliderListener();
        damagePerSecondSlider.addChangeListener(sliderListener);
        setDamagePerSecondSlider(0, 5000, 0);

        if(buttonsPanel.getParentPanel().getFrame().getHaveSavedValue()){
            buttonsPanel.setFormValue();
        }

        add(nameLabel);
        add(nameField);
        add(healthPointLabel);
        add(healthPointSlider);
        add(isStuffedCheckBox);
        add(isStuffedLabel); //TODO JLABEL
        add(creationDateLabel);
        add(creationDateSpinner);
        add(petNameLabel);
        add(petNameField);
        add(damagePerSecondLabel);
        add(damagePerSecondSlider);
    }

    public void setHealthPointSlider(int minimum, int maximum, int value){
        setSliderLabels(minimum, maximum, value);
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

    public void setSliderLabels(int minimum, int maximum, int value){
        sliderLabels = new Hashtable<>();
        sliderLabels.put(minimum, new JLabel(String.valueOf(minimum)));
        sliderLabels.put(maximum, new JLabel(String.valueOf(maximum)));
        sliderLabels.put(value, new JLabel("(current):" + value));
    }

    public void setDamagePerSecondSlider(int minimum, int maximum, int value){
        setSliderLabels(minimum, maximum, value);
        damagePerSecondSlider.setMinimum(minimum);
        damagePerSecondSlider.setMaximum(maximum);
        damagePerSecondSlider.setValue(value);
        damagePerSecondSlider.setLabelTable(sliderLabels);
        damagePerSecondSlider.setPaintLabels(true);
        damagePerSecondSlider.setMajorTickSpacing(1000);
        damagePerSecondSlider.setMinorTickSpacing(500);
        damagePerSecondSlider.setPaintTicks(true);
    }

    public String getNameField() {
        return nameField.getText();
    }

    public int getHealthPointSlider() {
        return healthPointSlider.getValue();
    }

    public Boolean getIsStuffedCheckBox() {
        return isStuffedCheckBox.isSelected();
    }

    public GregorianCalendar getCreationDate() {
        GregorianCalendar calendar = new  GregorianCalendar();
        Date date = (Date) creationDateSpinner.getValue();
        calendar.setTime(date);
        return calendar;
    }

    //Set Form

    public String getPetNameField() {
        return petNameField.getText();
    }

    public int getDamagePerSecond() {
        return damagePerSecondSlider.getValue();
    }

    public void setNameField(String name) {
        this.nameField.setText(name);
    }

    public void setHealthPointSlider(int healthPoint) {
        setHealthPointSlider(0, 50000, healthPoint); //TODO
    }

    public void setStuffedCheckBox(Boolean isStuffed) {
        isStuffedCheckBox.setSelected(isStuffed);
    }

    public void setCreationDateSpinner(GregorianCalendar creationDate) {

        date = creationDate.getTime();
        dateModel = new SpinnerDateModel(date, date, date, Calendar.MONTH);
        creationDateSpinner.setModel(dateModel);
        dateEditor = new JSpinner.DateEditor(creationDateSpinner, "dd/MM/yyyy");
        creationDateSpinner.setEditor(dateEditor);
    }

    public void setPetName(String petName) {
        petNameField.setText(petName);
    }

    public void setDamagePerSecond(int damagePerSecond) {
        setDamagePerSecondSlider(0, 5000, damagePerSecond); //TODO
    }


    //Reset

    public void setNameFieldReset() {
        nameField.setText("Enter the name of your character"); ;
    }

    public void setIsStuffedCheckBoxReset() {
        isStuffedCheckBox.setSelected(false);
    }

    public void setPetNameFieldReset() {
        petNameField.setText("Name of your pet");
    }

    public void setDamagePerSecondSliderReset() {
        damagePerSecondSlider.setValue(0);
    }

    //Listener

    private class SliderListener implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            if(!damagePerSecondSlider.getValueIsAdjusting()){
                setDamagePerSecondSlider(0, 5000, damagePerSecondSlider.getValue());
            }
        }
    }
}
