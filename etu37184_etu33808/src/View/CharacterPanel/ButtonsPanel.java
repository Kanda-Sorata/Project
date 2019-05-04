package View.CharacterPanel;

import Model.Character;
import View.HomePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import Exception.*;

public class ButtonsPanel extends JPanel {
    private JButton validation;
    private JButton reset;
    private JButton back;
    private ButtonListener buttonListener;

    private Character character;

    private String name;
    private int healthPoint;
    private Boolean isStuffed;
    private GregorianCalendar creationDate;
    private String petName;
    private Integer damagePerSecond;

    private FormPanelRight formPanelRight;
    private NewPanel parentPanel;

    public ButtonsPanel(){
        //Add properties
        setLayout(new GridLayout(1, 3, 5, 15));
        setBorder(new EmptyBorder(0, 250, 0, 250)); //Top, left, bottom, right

        //Add components
        buttonListener = new ButtonListener();

        validation = new JButton("Validation");
        validation.addActionListener(buttonListener);
        reset = new JButton("Reset");
        reset.addActionListener(buttonListener);
        back = new JButton("Back (Home)");
        back.addActionListener(buttonListener);

        add(validation);
        add(reset);
        add(back);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == validation){

            }else{
                if(actionEvent.getSource() == reset){
                    clearValue();
                }else{
                    setSaveValue();
                    parentPanel.getFrame().setHaveSavedValue(true);
                    try {
                        character = new Character(name, healthPoint, isStuffed, creationDate, petName, damagePerSecond, null, null);
                        parentPanel.getFrame().setCharacterForm(character);
                        parentPanel.getFrame().setHaveSavedValue(true);
                        removeAll();
                        add(new HomePanel());
                        revalidate();
                        repaint();
                    }catch(HealthPointsException healthPointsException){
                        JOptionPane.showMessageDialog(null, healthPointsException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                    }catch(DamagePerSecondException damagePerSeondException){
                        JOptionPane.showMessageDialog(null, damagePerSeondException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    public void clearValue(){
        formPanelRight.setNameFieldReset();
        formPanelRight.setIsStuffedCheckBoxReset();
        formPanelRight.setPetNameFieldReset();
        formPanelRight.setDamagePerSecondSliderReset();
    }

    public void setFormPanelRight(FormPanelRight formPanelRight){
        this.formPanelRight = formPanelRight;
    }

    public void setSaveValue(){
        setName(getName());
        setHealthPoint(getHealthPoint());
        setStuffed(getStuffed());
        setCreationDate(getCreationDate());
        setPetName(getPetName());
        setDamagePerSecond(getDamagePerSecond());
    }

    public void setFormValue(){
        character = parentPanel.getFrame().getCharacterForm();
        formPanelRight.setNameField(character.getName());
        formPanelRight.setHealthPointSlider(character.getHealthPoints());
        formPanelRight.setStuffedCheckBox(character.isStuffed());
        formPanelRight.setCreationDateSpinner(character.getCreationDate());
        formPanelRight.setPetName(character.getPetName());
        formPanelRight.setDamagePerSecond(character.getDamagePerSecond());
    }

    public String getName() {
        return formPanelRight.getNameField();
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoint() {
        return formPanelRight.getHealthPointSlider();
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public Boolean getStuffed() {
        return formPanelRight.getIsStuffedCheckBox();
    }

    public void setStuffed(Boolean stuffed) {
        isStuffed = stuffed;
    }

    public GregorianCalendar getCreationDate() {
        return formPanelRight.getCreationDate();
    }

    public void setCreationDate(GregorianCalendar creationDate) {
        this.creationDate = creationDate;
    }

    public String getPetName() {
        return formPanelRight.getPetNameField();
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Integer getDamagePerSecond() {
        return formPanelRight.getDamagePerSecond();
    }

    public void setDamagePerSecond(Integer damagePerSecond) {
        this.damagePerSecond = damagePerSecond;
    }

    public void setParentPanel(NewPanel newPanel){
        this.parentPanel = newPanel;
    }

    public NewPanel getParentPanel() {
        return parentPanel;
    }
}
