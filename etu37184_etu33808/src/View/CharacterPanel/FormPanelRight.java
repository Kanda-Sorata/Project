package View.CharacterPanel;

import Controller.CharacterController;
import Exception.DataAccessException;
import Exception.DataException;
import Model.Character;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;

public class FormPanelRight extends JPanel {
    private JLabel nameLabel;
    private JLabel healthPointLabel;
    private JLabel isStuffedLabel;
    private JLabel creationDateLabel;
    private JLabel petNameLabel;
    private JLabel damagePerSecondActivatedLabel;
    private JLabel damagePerSecondLabel;

    private JTextField nameField;
    private JSlider healthPointSlider;
    private JCheckBox isStuffedCheckBox;
    private JSpinner creationDateSpinner;
    private JTextField petNameField;
    private JCheckBox damagePerSecondActivated;
    private JSlider damagePerSecondSlider;

    private Hashtable<Integer, JLabel> sliderLabels; //Key must be, Value must been an object
    private SpinnerDateModel spinnerModel;
    private JSpinner.DateEditor dateEditor;
    private GregorianCalendar dateChoice;
    private SpinnerListener spinnerListener;
    private Date initDate;
    private Date latestDate;
    private int healthPointMax;

    private ButtonsPanel buttonsPanel;

    private SliderListenerDamagePerSecond sliderListenerDamagePerSecond;
    private SliderListenerHealthPoint sliderListenerHealthPoint;
    private CheckBoxListener checkBoxListener;

    private JLabel requiredLabel;

    private CharacterController characterController;
    private Character character;

    private boolean isModifyPanel;
    private JLabel creationDateLabelValue;


    public FormPanelRight(ButtonsPanel buttonsPanel, boolean isModifyPanel){
        //Add properties
        setLayout(new GridLayout(8, 2, 5, 15));
        setBorder(new EmptyBorder(30, 0, 30, 40)); //Top, left, bottom, right

        //Add link panel
        this.buttonsPanel = buttonsPanel;

        //Init
        this.isModifyPanel = isModifyPanel;


        //Add Component
        nameLabel = new JLabel("<html>Name<font color = 'red'>*</font></html>");
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nameField = new JTextField(25);
        nameField.setToolTipText("Enter the name of your character (4 - 50 characters)");


        healthPointLabel = new JLabel("<html>Health point<font color = 'red'>*</font></html>");
        healthPointLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        healthPointSlider = new JSlider(JSlider.HORIZONTAL);
        healthPointSlider.setEnabled(false);
        setHealthPointSlider(Character.getMinHp(), Character.getMaxHp(), Character.getMinHp());

        isStuffedLabel = new JLabel("Is already stuffed");
        isStuffedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        isStuffedCheckBox = new JCheckBox();
        isStuffedCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
        isStuffedCheckBox.setToolTipText("False selected by default when unchecked");

        creationDateLabel = new JLabel("<html>Creational date<font color = 'red'>*</font></html>");
        creationDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        if(!isModifyPanel) {
            creationDateSpinner = new JSpinner();
            GregorianCalendar calendar = new GregorianCalendar();
            initDate = calendar.getTime();
            setCreationDateSpinnerNew(initDate);
            creationDateSpinner.setToolTipText("Date available " + getYearEarliestDate() + " to " + getYearLatestDate());
            spinnerListener = new SpinnerListener();
            creationDateSpinner.addChangeListener(spinnerListener);
        }
        else{
            GregorianCalendar calendar = new GregorianCalendar();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            creationDateLabelValue = new JLabel(dateFormat.format(calendar.getTime()));
            creationDateLabelValue.setToolTipText("Cannot be update");
        }

        petNameLabel = new JLabel("Pet name");
        petNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        petNameField = new JTextField(25);
        petNameField.setToolTipText("Name of your pet if you have one (4 - 50 characters)");

        damagePerSecondActivatedLabel = new JLabel("Set up damage per second");
        damagePerSecondActivatedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        damagePerSecondActivated = new JCheckBox();
        damagePerSecondActivated.setSelected(false);
        damagePerSecondActivated.setHorizontalTextPosition(SwingConstants.LEFT);
        checkBoxListener = new CheckBoxListener();
        damagePerSecondActivated.addItemListener(checkBoxListener);

        damagePerSecondLabel = new JLabel("Damage per second");
        damagePerSecondLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        damagePerSecondSlider = new JSlider(JSlider.HORIZONTAL);
        damagePerSecondSlider.setToolTipText("Damage/sec what the player going to deal at enemies");
        damagePerSecondSlider.setEnabled(false);
        setDamagePerSecondSlider(Character.getMinDmg(), Character.getMaxDmg(), Character.getMinDmg());

        requiredLabel = new JLabel("<html>(<font color = 'red'>*</font> required field)</html>");
        requiredLabel.setHorizontalAlignment(SwingConstants.RIGHT);


        //Add listener
        sliderListenerDamagePerSecond = new SliderListenerDamagePerSecond();
        sliderListenerHealthPoint = new SliderListenerHealthPoint();
        healthPointSlider.addChangeListener(sliderListenerHealthPoint);
        damagePerSecondSlider.addChangeListener(sliderListenerDamagePerSecond);

        add(nameLabel);
        add(nameField);
        add(healthPointLabel);
        add(healthPointSlider);
        add(isStuffedLabel);
        add(isStuffedCheckBox);
        add(creationDateLabel);
        if(!isModifyPanel) {
            add(creationDateSpinner);
        }else{
            add(creationDateLabelValue);
        }
        add(petNameLabel);
        add(petNameField);
        add(damagePerSecondActivatedLabel);
        add(damagePerSecondActivated);
        add(damagePerSecondLabel);
        add(damagePerSecondSlider);
        add(requiredLabel);
    }

    private int getYearEarliestDate() {
        return getEarliestDate().get(Calendar.YEAR);
    }

    private int getYearLatestDate() {
        return getLatestDate().get(Calendar.YEAR);
    }

    public void setHealthPointSlider(int minimum, int maximum, int value){
        setSliderLabels(minimum, maximum, value);
        healthPointSlider.setMinimum(minimum);
        healthPointSlider.setMaximum(maximum);
        healthPointSlider.setValue(value);
        healthPointSlider.setLabelTable(sliderLabels);
        healthPointSlider.setPaintLabels(true);
        healthPointSlider.setMajorTickSpacing(maximum/5);
        healthPointSlider.setMinorTickSpacing(maximum/50);
        healthPointSlider.setPaintTicks(true);
    }

    public void setSliderLabels(int minimum, int maximum, int value){
        sliderLabels = new Hashtable<>();
        sliderLabels.put(minimum, new JLabel(String.valueOf(minimum)));
        sliderLabels.put(maximum, new JLabel(String.valueOf(maximum)));
        sliderLabels.put(value, new JLabel(String.valueOf(value)));
    }

    public void setDamagePerSecondSlider(int minimum, int maximum, int value){
        setSliderLabels(minimum, maximum, value);
        damagePerSecondSlider.setMinimum(minimum);
        damagePerSecondSlider.setMaximum(maximum);
        damagePerSecondSlider.setValue(value);
        damagePerSecondSlider.setLabelTable(sliderLabels);
        damagePerSecondSlider.setPaintLabels(true);
        damagePerSecondSlider.setMajorTickSpacing(maximum/5);
        damagePerSecondSlider.setMinorTickSpacing(maximum/50);
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
        return dateChoice;
    }

    public String getPetNameField() {
        return petNameField.getText();
    }

    public int getDamagePerSecond() {
        return damagePerSecondSlider.getValue();
    }

    public GregorianCalendar getEarliestDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(initDate);
        return calendar;
    }

    public GregorianCalendar getLatestDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(latestDate);
        return calendar;
    }

    public void setDateChoice(){
        GregorianCalendar calendar = new GregorianCalendar();
        if (isModifyPanel) {
            calendar.setTime(character.getCreationDate().getTime());
        } else {
            Date date = (Date) creationDateSpinner.getValue();
            calendar.setTime(date);
        }
        this.dateChoice = calendar;
    }

    public boolean damagePerSecondIsAvailable(){
        return damagePerSecondActivated.isSelected();
    }

    //Set Form
    public void setNameField(String name) {
        this.nameField.setText(name);
    }

    public void setStuffedCheckBox(boolean isStuffed) {
        isStuffedCheckBox.setSelected(isStuffed);
    }

    public void setCreationDateSpinnerNew(Date initDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initDate);
        calendar.add(Calendar.DAY_OF_WEEK, -1);
        Date earliestDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 20);
        latestDate = calendar.getTime();
        spinnerModel = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.MONTH); //getPrevious & nextValue method
        creationDateSpinner.setModel(spinnerModel);
        dateEditor = new JSpinner.DateEditor(creationDateSpinner, "dd/MM/yyyy");
        creationDateSpinner.setEditor(dateEditor);
    }

    public void setCreationDateLabelValue(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        creationDateLabelValue.setText(dateFormat.format(date));
    }

    public void updateCreationDateSpinner(GregorianCalendar calendar){
        spinnerModel.setValue(calendar.getTime());
    }


    public void setPetNameField(String petName) {
        petNameField.setText(petName);
    }

    public void setDamagePerSecondSlider(int damagePerSecond) {
        if(damagePerSecondActivated.isSelected()) {
            setDamagePerSecondSlider(Character.getMinDmg(), Character.getMaxDmg(), damagePerSecond);
        }
    }

    public void setDamagePerSecondActivated(boolean isSelected){
        damagePerSecondActivated.setSelected(isSelected);
    }

    //Reset Form

    public void setNameFieldReset() {
        nameField.setText("");
    }

    public void setHealthPointSliderReset(){
        setHealthPointSlider(Character.getMinHp(), Character.getMaxHp(), Character.getMinHp());
    }

    public void setIsStuffedCheckBoxReset() {
        isStuffedCheckBox.setSelected(false);
    }


    public void setPetNameFieldReset() {
        petNameField.setText("");
    }

    public void setDamagePerSecondSliderReset() {
        setDamagePerSecondSlider(Character.getMinDmg(), Character.getMaxDmg(), Character.getMinDmg());
    }

    //Error form
    public void setNameLabelError(){
        nameLabel.setText("<html><font color = 'red'>Name*</font></html>");
    }

    public void setPetNameLabelError(){
        petNameLabel.setText("<html><font color = 'red'>Pet name</font></html>");
    }

    public void setCreationDateLabelError(){ creationDateLabel.setText("<html><font color = 'red'>Creational date*</font></html>"); }

    public void setDamagePerSecondLabelError(){ damagePerSecondLabel.setText("<html><font color = 'red'>Damage per second*</font></html>"); }

    public void setHealthPointLabelError(){ healthPointLabel.setText("<html><font color = 'red'>Health point*</font></html>"); }


    //Reset form
    public void setNameLabelReset(){
        nameLabel.setText("<html>Name<font color = 'red'>*</font></html>");
    }

    public void setPetNameLabelReset(){
        petNameLabel.setText("Pet name");
    }

    public void setCreationDateLabelReset(){
        creationDateLabel.setText("<html>Creational date<font color = 'red'>*</font></html>");
    }

    public void setDamagePerSecondLabelReset(){ damagePerSecondLabel.setText("<html>Damage per second<font color = 'red'>*</font></html>"); }

    public void setHealthPointLabelReset(){ healthPointLabel.setText("<html>Health point<font color = 'red'>*</font></html>");}


    //Listener
    private class SliderListenerDamagePerSecond implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            if (changeEvent.getSource() == damagePerSecondSlider) {
                if (!damagePerSecondSlider.getValueIsAdjusting()) {
                    setDamagePerSecondSlider(damagePerSecondSlider.getMinimum(), damagePerSecondSlider.getMaximum(), damagePerSecondSlider.getValue());
                }
            }
        }
    }

    private class SliderListenerHealthPoint implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            if(changeEvent.getSource() == healthPointSlider) {
                if (!healthPointSlider.getValueIsAdjusting()) {
                    setHealthPointSlider(healthPointSlider.getMinimum(), healthPointSlider.getMaximum(), healthPointSlider.getValue());
                }
            }
        }
    }

    private class SpinnerListener implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent changeEvent){
            if(changeEvent.getSource() == creationDateSpinner) {
                setDateChoice();
            }
        }
    }

    private class CheckBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if(damagePerSecondActivated.isSelected()){
                damagePerSecondSlider.setEnabled(true);
            }
            if(!damagePerSecondActivated.isSelected()){
                damagePerSecondSlider.setEnabled(false);
            }
        }
    }

    //Modify

    public void setFieldWithCharacterValues(String pseudo, int number, String game, String server, String characterClass, String characterName){
        characterController = new CharacterController();
        try {
            character = characterController.getOneCharacter(pseudo, number, game, server, characterClass, characterName);
            setNameField(character.getName());
            setHealthPointSlider(Character.getMinHp(), healthPointMax, character.getHealthPoints());
            healthPointSlider.setEnabled(true);
            setStuffedCheckBox(character.isStuffed());
            if(isModifyPanel) {
                setCreationDateLabelValue(character.getCreationDate().getTime());
            }else{
                GregorianCalendar calendar = new GregorianCalendar();
                setCreationDateSpinnerNew(calendar.getTime());
            }
            setPetNameField(character.getPetName());
            if(character.getDamagePerSecond() != null) {
                setDamagePerSecondActivated(true);
                setDamagePerSecondSlider(character.getDamagePerSecond());
            }else{
                setDamagePerSecondActivated(false);
                setDamagePerSecondSlider(0);
            }
        }catch (DataException dataException) {
            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DataAccessException dataAccessException) {
            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void unsetFieldWithCharacterValues(){
        setNameField("");
        setHealthPointSlider(Character.getMinHp(), Character.getMaxHp(), Character.getMinHp());
        setStuffedCheckBox(false);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.getTime();
        if(isModifyPanel) {
            setCreationDateLabelValue(calendar.getTime());
        }else{
            updateCreationDateSpinner(calendar);
        }
        setPetNameField("");
        setDamagePerSecondActivated(false);
        setDamagePerSecondSlider(0);
    }

    public int getHealthPointMax() {
        return healthPointMax;
    }

    public void setHealthPointMax(int healthPointMax) {
        this.healthPointMax = healthPointMax;
    }
}
