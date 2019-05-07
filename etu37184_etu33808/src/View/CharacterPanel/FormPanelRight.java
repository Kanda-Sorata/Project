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
    private Date earliestDate;
    private int healthPointValue;

    private ButtonsPanel buttonsPanel;

    private SliderListenerDamagePerSecond sliderListenerDamagePerSecond;
    private SliderListenerHealthPoint sliderListenerHealthPoint;
    private CheckBoxListener checkBoxListener;

    private JLabel requiredLabel;

    private CharacterController characterController;


    public FormPanelRight(ButtonsPanel buttonsPanel){
        //Add propetiers
        setLayout(new GridLayout(8, 2, 5, 15));
        setBorder(new EmptyBorder(30, 0, 30, 40)); //Top, left, bottom, right

        //Add link panel
        this.buttonsPanel = buttonsPanel;


        //Add Component
        nameLabel = new JLabel("<html>Name<font color = 'red'>*</font></html>");
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nameField = new JTextField(25);
        nameField.setToolTipText("Enter the name of your character");


        healthPointLabel = new JLabel("<html>Health point<font color = 'red'>*</font></html>");
        healthPointLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        healthPointSlider = new JSlider(JSlider.HORIZONTAL);
        healthPointSlider.setEnabled(false);
        this.setHealthPointValue(0);
        setHealthPointSlider(healthPointValue, Character.getMaxHp(), Character.getMinHp());

        isStuffedLabel = new JLabel("<html>Is already stuffed<font color = 'red'>*</font></html>");
        isStuffedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        isStuffedCheckBox = new JCheckBox();
        isStuffedCheckBox.setHorizontalAlignment(SwingConstants.LEFT);

        creationDateLabel = new JLabel("<html>Creational date<font color = 'red'>*</font></html>");
        creationDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        creationDateSpinner = new JSpinner();
        setCreationDateSpinner();
        creationDateSpinner.setToolTipText("Date available today to 2039");
        spinnerListener = new SpinnerListener();
        creationDateSpinner.addChangeListener(spinnerListener);

        petNameLabel = new JLabel("Pet name");
        petNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        petNameField = new JTextField(25);
        petNameField.setToolTipText("Name of your pet if you have one");

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
        add(creationDateSpinner);
        add(petNameLabel);
        add(petNameField);
        add(damagePerSecondActivatedLabel);
        add(damagePerSecondActivated);
        add(damagePerSecondLabel);
        add(damagePerSecondSlider);
        add(requiredLabel);
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
        sliderLabels.put(value, new JLabel("(current):" + value));
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
        calendar.setTime(earliestDate);
        return calendar;
    }

    public GregorianCalendar getLatestDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(latestDate);
        return calendar;
    }

    public void setDateChoice(){
        Date date = (Date) creationDateSpinner.getValue();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        this.dateChoice = calendar;
    }

    public boolean damagePerSecondIsAvailable(){
        return damagePerSecondActivated.isSelected();
    }

    public void setHealthPointValue(int healthPointValue) {
        this.healthPointValue = healthPointValue;
    }

    //Set Form

    public void setNameField(String name) {
        this.nameField.setText(name);
    }

    public void setHealthPointSlider(int healthPoint){ setHealthPointSlider(Character.getMinHp(), Character.getMaxHp(), healthPoint);}

    public void setStuffedCheckBox(boolean isStuffed) {
        isStuffedCheckBox.setSelected(isStuffed);
    }

    public void setCreationDateSpinner(Date date){
        Calendar calendar = Calendar.getInstance();
        initDate = calendar.getTime();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_WEEK, -1);
        date = calendar.getTime();
        calendar.add(Calendar.YEAR, 20);
        latestDate = calendar.getTime();
        spinnerModel = new SpinnerDateModel(initDate, date, latestDate, Calendar.MONTH); //getPrevious & nextvalue method
        creationDateSpinner.setModel(spinnerModel);
        dateEditor = new JSpinner.DateEditor(creationDateSpinner, "dd/MM/yyyy");
        creationDateSpinner.setEditor(dateEditor);
    }

    public void setCreationDateSpinner() {
        Calendar calendar = Calendar.getInstance();
        calendar.getTime();
        calendar.add(Calendar.DAY_OF_WEEK, -1);
        earliestDate = calendar.getTime();
        setCreationDateSpinner(earliestDate);
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

    public void setCreationDateLabelError(){
        creationDateLabel.setText("<html><font color = 'red'>Creational date*</font></html>");
    }
    public void setDamagePerSecondLabelError(){ damagePerSecondLabel.setText("<html><font color = 'red'>Damage per second*</font></html>"); }


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


    //Listener

    private class SliderListenerDamagePerSecond implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            if (changeEvent.getSource() == damagePerSecondSlider) {
                if (!damagePerSecondSlider.getValueIsAdjusting()) {
                    setDamagePerSecondSlider(damagePerSecondSlider.getValue());
                }
            }
        }
    }

    private class SliderListenerHealthPoint implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            if(changeEvent.getSource() == healthPointSlider) {
                if (!healthPointSlider.getValueIsAdjusting()) {
                    setHealthPointSlider(healthPointSlider.getValue());
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
            Character character;
            character = characterController.getOneCharacter(pseudo, number, game, server, characterClass, characterName);
            setNameField(character.getName());
            setHealthPointSlider(character.getHealthPoints());
            setStuffedCheckBox(character.isStuffed());
            setCreationDateSpinner(character.getCreationDate().getTime());
            setPetNameField(character.getPetName());
            if(character.getDamagePerSecond() != null) {
                setDamagePerSecondActivated(true);
                setDamagePerSecondSlider(character.getDamagePerSecond());
            }else{
                setDamagePerSecondActivated(false);
                setDamagePerSecondSlider(0);
            }
        }catch (DataException dataException) {
            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error",
                                                                                            JOptionPane.ERROR_MESSAGE);
        } catch (DataAccessException dataAccessException) {
            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error",
                                                                                            JOptionPane.ERROR_MESSAGE);
        }
    }
    public void unsetFieldWithCharacterValues(){
        setNameField("");
        setHealthPointSlider(0);
        setStuffedCheckBox(false);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.getTimeZone();
        updateCreationDateSpinner(calendar);
        setPetNameField("");
        setDamagePerSecondActivated(false);
        setDamagePerSecondSlider(0);
    }
}
