package View.CharacterPanel;

import Controller.CharacterController;
import Model.Character;
import View.HomePanel;
import View.Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Exception.*;

public class ButtonsPanel extends JPanel {
    private JButton validation;
    private JButton reset;
    private JButton back;
    private ButtonListener buttonListener;

    private Character character;

    private FormPanelRight formPanelRight;
    private FormPanelLeft formPanelLeft;
    private Frame frame;

    private CharacterController characterController;

    public ButtonsPanel(){
        //Add properties
        setLayout(new GridLayout(1, 3, 5, 15));
        setBorder(new EmptyBorder(0, 250, 50, 250)); //Top, left, bottom, right

        characterController = new CharacterController();

        //Add listener
        buttonListener = new ButtonListener();

        //Add component
        back = new JButton("Back (Home)");
        back.addActionListener(buttonListener);
        reset = new JButton("Reset");
        reset.addActionListener(buttonListener);
        validation = new JButton("Validation");
        validation.addActionListener(buttonListener);

        add(back);
        add(reset);
        add(validation);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == validation){
                formPanelRight.setDateChoice();
                if(isFormValide()) {
                    resetLabel();
                    try {
                        String pseudo = formPanelLeft.getPseudoChoice();
                        int number = formPanelLeft.getNumberChoice();
                        String game = formPanelLeft.getGameChoice();
                        String server = formPanelLeft.getServerChoice();
                        String characterClass = formPanelLeft.getCharacterClassChoice();
                        character = new Character(formPanelRight.getNameField(), formPanelRight.getHealthPointSlider(),
                                formPanelRight.getIsStuffedCheckBox(), formPanelRight.getCreationDate(),
                                formPanelRight.getPetNameField(), formPanelRight.getDamagePerSecond(),
                                null, null);
                        characterController.insertACharacter(character, pseudo, number, game, server, characterClass);
                    } catch (HealthPointsException healthPointsException) {
                        JOptionPane.showMessageDialog(null, healthPointsException.getMessage(), "Error",
                                                                                            JOptionPane.ERROR_MESSAGE);

                    } catch (DamagePerSecondException damagePerSecondException) {
                        JOptionPane.showMessageDialog(null, damagePerSecondException.getMessage(), "Error",
                                                                                            JOptionPane.ERROR_MESSAGE);
                    } catch (DataException dataException) {
                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error",
                                                                                            JOptionPane.ERROR_MESSAGE);
                    } catch (DataAccessException dataAccessException) {
                        JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error",
                                                                                            JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Some erroes has been found in the form, " +
                            "please correct this to continue.", "Error form", JOptionPane.ERROR_MESSAGE);
                    if(noSelection(formPanelLeft.getPseudoChoice())){
                        formPanelLeft.setPlayerAccountLabelError();
                    }else{
                        formPanelLeft.setPlayerAccountLabelReset();;
                    }
                    if(noSelection(formPanelLeft.getGameChoice())){
                        formPanelLeft.setGameLabelError();
                    }else{
                        formPanelLeft.setGameLabelReset();
                    }
                    if(noSelection(formPanelLeft.getServerChoice())){
                        formPanelLeft.setServerLabelError();
                    }else{
                        formPanelLeft.setServerLabelReset();
                    }
                    if(noSelection(formPanelLeft.getCharacterClassChoice())){
                        formPanelLeft.setCharacterClassLabelError();
                    }else{
                        formPanelLeft.setCharacterClassLabelReset();
                    }
                    if (formPanelRight.getNameField() == null || !isNameValide(formPanelRight.getNameField())) {
                        formPanelRight.setNameLabelError();
                    }else{
                        formPanelRight.setNameLabelReset();
                    }
                    if (!formPanelRight.getPetNameField().equals("") && !isNameValide(formPanelRight.getPetNameField())) {
                        formPanelRight.setPetNameLabelError();
                    }else{
                        formPanelRight.setPetNameLabelReset();
                    }
                    if (formPanelRight.getCreationDate().before(formPanelRight.getEarliestDate()) ||
                                            formPanelRight.getCreationDate().after(formPanelRight.getLatestDate())) {
                        formPanelRight.setCreationDateLabelError();
                    }else{
                        formPanelRight.setCreationDateLabelReset();
                    }
                }
            } else{
                if(actionEvent.getSource() == reset){
                    clearValueForm();
                    resetLabel();
                    frame.setHaveSavedValue(false);
                    try {
                        clearValueSaved();
                    }catch(HealthPointsException healthPointsException){}
                    catch(DamagePerSecondException damagePerSecondException){}
                }else{
                    try {
                        //filling values
                        setSaveValue();
                        frame.setHaveSavedValue(true);
                        //update UI
                        frame.getContainer().removeAll();
                        frame.getContainer().add(new HomePanel());
                        frame.getContainer().revalidate();
                        frame.getContainer().repaint();
                    }catch(HealthPointsException healthPointsException){
                        JOptionPane.showMessageDialog(null, healthPointsException.getMessage(), "Error",
                                                                                            JOptionPane.ERROR_MESSAGE);

                    }catch(DamagePerSecondException damagePerSecondException){
                        JOptionPane.showMessageDialog(null, damagePerSecondException.getMessage(), "Error",
                                                                                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public void clearValueSaved() throws HealthPointsException, DamagePerSecondException{
        frame.setIndexCharacterClass(0);
        frame.setIndexPlayerAccount(0);
        frame.setIndexServer(0);
        frame.setIndexGame(0);
        character = frame.getCharacterForm();
        if(character != null) {
            character.setHealthPoints(0);
            character.setName(null);
            character.setCreationDate(null);
            character.setPetName(null);
            character.setPlayer(null);
            character.setServer(null);
            character.setDamagePerSecond(null);
            frame.setCharacterForm(character);
        }
    }

    public void clearValueForm(){ //Reset
        formPanelRight.setNameFieldReset();
        formPanelRight.setHealthPointSliderReset();
        formPanelRight.setIsStuffedCheckBoxReset();
        formPanelRight.setPetNameFieldReset();
        formPanelRight.setDamagePerSecondSliderReset();
        formPanelLeft.setPlayerAccountCombo(0);
        formPanelLeft.setGameCombo(0);
        formPanelLeft.setServerCombo(0);
        formPanelLeft.setCharacterClassCombo(0);
    }

    public void setFormPanelRight(FormPanelRight formPanelRight){
        this.formPanelRight = formPanelRight;
    }

    public void setFormPanelLeft(FormPanelLeft formPanelLeft) { this.formPanelLeft = formPanelLeft; }

    public void setSaveValue() throws HealthPointsException, DamagePerSecondException{ //Back
        character = new Character(formPanelRight.getNameField(), formPanelRight.getHealthPointSlider(),
            formPanelRight.getIsStuffedCheckBox(), formPanelRight.getCreationDate(), formPanelRight.getPetNameField(),
            formPanelRight.getDamagePerSecond(), null, null);
        frame.setCharacterForm(character);
        frame.setIndexPlayerAccount(formPanelLeft.getIndexPlayerAccount());
        frame.setIndexGame(formPanelLeft.getIndexGame());
        frame.setIndexServer(formPanelLeft.getIndexServer());
        frame.setIndexCharacterClass(formPanelLeft.getIndexCharacterClass());
    }

    public void setFormValue(){
        character = frame.getCharacterForm();
        if(character != null) {
            formPanelRight.setNameField(character.getName());
            formPanelRight.setHealthPointSlider(character.getHealthPoints());
            formPanelRight.setStuffedCheckBox(character.isStuffed());
            formPanelRight.updateCreationDateSpinner(character.getCreationDate());
            formPanelRight.setPetNameField(character.getPetName());
            formPanelRight.setDamagePerSecondSlider(character.getDamagePerSecond());
        }
        formPanelLeft.setPlayerAccountCombo(frame.getIndexPlayerAccount());
        formPanelLeft.setGameCombo(frame.getIndexGame());
        formPanelLeft.setServerCombo(frame.getIndexServer());
        formPanelLeft.setCharacterClassCombo(frame.getIndexCharacterClass());
    }

    public void resetLabel() {
        formPanelLeft.setPlayerAccountLabelReset();
        formPanelLeft.setGameLabelReset();
        formPanelLeft.setServerLabelReset();
        formPanelLeft.setCharacterClassLabelReset();
        formPanelRight.setNameLabelReset();
        formPanelRight.setPetNameLabelReset();
        formPanelRight.setCreationDateLabelReset();
    }


    public boolean noSelection(String input){
        if(input != null) {
            return input.equals("No selection");
        }
        return true;
    }

    public boolean isFormValide(){
        return !noSelection(formPanelLeft.getPseudoChoice())
                && !noSelection(formPanelLeft.getGameChoice())
                && !noSelection(formPanelLeft.getGameChoice())
                && !noSelection(formPanelLeft.getCharacterClassChoice())
                && !noSelection(formPanelRight.getNameField())
                && formPanelRight.getHealthPointSlider() >= Character.getMinHp()
                && formPanelRight.getHealthPointSlider() <= Character.getMaxHp()
                && formPanelRight.getIsStuffedCheckBox() != null
                && formPanelRight.getCreationDate().after(formPanelRight.getEarliestDate())
                && formPanelRight.getCreationDate().before(formPanelRight.getLatestDate())
                && formPanelRight.getDamagePerSecond() >= Character.getMinDmg()
                && formPanelRight.getDamagePerSecond() <= Character.getMaxDmg();
                //getCreationDate().before(date) true if date est après celle du getCreationDate
    }

    public boolean isNameValide(String name){
        return name.matches("[a-zA-Z]+");
    }

}
