package View.CharacterPanel;

import Controller.CharacterController;
import Exception.DamagePerSecondException;
import Exception.DataAccessException;
import Exception.DataException;
import Exception.HealthPointsException;
import Model.Character;
import View.Frame;
import View.HomePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class ButtonsPanel extends JPanel {
    private JButton validation;
    private JButton reset;
    private JButton back;
    private ButtonListener buttonListener;

    private Character character;

    private FormPanelRight formPanelRight;
    private FormPanelLeftModify formPanelLeftModify;
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
            formPanelRight.setDateChoice();
            if(actionEvent.getSource() == validation){
                if(isFormValide()) {
                    resetLabel();
                    try {
                        String pseudo = formPanelLeftModify.getPseudoChoice();
                        int number = formPanelLeftModify.getNumberChoice();
                        String game = formPanelLeftModify.getGameChoice();
                        String server = formPanelLeftModify.getServerChoice();
                        String characterClass = formPanelLeftModify.getCharacterClassChoice();
                        character = new Character(formPanelRight.getNameField(), formPanelRight.getHealthPointSlider(),
                                formPanelRight.getIsStuffedCheckBox(), formPanelRight.getCreationDate(),
                                formPanelRight.getPetNameField(), null,
                                null, null);
                        if(formPanelRight.damagePerSecondIsAvailable()) {
                            character.setDamagePerSecond(formPanelRight.getDamagePerSecond());
                        }
                        int state;
                        String msg = "The caracter " + character.getName() + " has been ";
                        if(!formPanelLeftModify.isModifyPanel()) {
                            state = characterController.insertACharacter(character, pseudo, number, game, server, characterClass);
                            msg += "add ";
                        }else {
                            state = characterController.modifyACharacter(character, pseudo, number, game, server, characterClass);
                            msg  += "modify ";
                        }
                        msg += "to the player account " + pseudo + "#" + number + ".";
                        if(state > 0) {

                            JOptionPane.showMessageDialog(null, msg, "Information",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
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
                    if(noSelection(formPanelLeftModify.getPseudoChoice())){
                        formPanelLeftModify.setPlayerAccountLabelError();
                    }else{
                        formPanelLeftModify.setPlayerAccountLabelReset();
                    }
                    if(noSelection(formPanelLeftModify.getGameChoice())){
                        formPanelLeftModify.setGameLabelError();
                    }else{
                        formPanelLeftModify.setGameLabelReset();
                    }
                    if(noSelection(formPanelLeftModify.getServerChoice())){
                        formPanelLeftModify.setServerLabelError();
                    }else{
                        formPanelLeftModify.setServerLabelReset();
                    }
                    if(noSelection(formPanelLeftModify.getCharacterClassChoice())){
                        formPanelLeftModify.setCharacterClassLabelError();
                    }else{
                        formPanelLeftModify.setCharacterClassLabelReset();
                    }
                    if(formPanelLeftModify.isModifyPanel()){
                        if(noSelection(formPanelLeftModify.getCharacterChoice())){
                            formPanelLeftModify.setCharacterLabelError();
                        }else{
                            formPanelLeftModify.setCharacterLabelReset();
                        }
                    }
                    if (formPanelRight.getNameField().isEmpty() || !isNameValide(formPanelRight.getNameField())) {
                        formPanelRight.setNameLabelError();
                    }else{
                        formPanelRight.setNameLabelReset();
                    }
                    if(formPanelRight.getHealthPointSlider() > formPanelRight.getHealthPointMax()){
                        formPanelRight.setHealthPointLabelError();
                    }
                    else{
                        formPanelRight.setHealthPointLabelReset();
                    }
                    if (!formPanelRight.getPetNameField().isEmpty() && !isNameValide(formPanelRight.getPetNameField())) {
                        formPanelRight.setPetNameLabelError();
                    }else{
                        formPanelRight.setPetNameLabelReset();
                    }
                    if (formPanelRight.getCreationDate().before(formPanelRight.getEarliestDate()) ||
                                            formPanelRight.getCreationDate().after(formPanelRight.getLatestDate())) {
                        formPanelRight.setCreationDateLabelError();
                    }else{
                        formPanelRight.setCreationDateLabelReset();
                    }if(formPanelRight.damagePerSecondIsAvailable() && (formPanelRight.getDamagePerSecond() < Character.getMinDmg()
                            || formPanelRight.getDamagePerSecond() > Character.getMaxDmg())){
                        formPanelRight.setDamagePerSecondLabelError();
                    }else{
                        formPanelRight.setDamagePerSecondLabelReset();
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
                        frame.getContainer().add(new HomePanel(frame));
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
        formPanelRight.setDamagePerSecondActivated(false);
        formPanelRight.setDamagePerSecondSliderReset();
        formPanelLeftModify.setPlayerAccountCombo(0);
        formPanelLeftModify.setGameCombo(0);
        formPanelLeftModify.setServerCombo(0);
        formPanelLeftModify.setCharacterClassCombo(0);
    }

    public void setFormPanelRight(FormPanelRight formPanelRight){
        this.formPanelRight = formPanelRight;
    }

    public void setFormPanelLeftModify(FormPanelLeftModify formPanelLeftModify) { this.formPanelLeftModify = formPanelLeftModify; }

    public void setSaveValue() throws HealthPointsException, DamagePerSecondException{ //Back
        character = new Character(formPanelRight.getNameField(), formPanelRight.getHealthPointSlider(),
            formPanelRight.getIsStuffedCheckBox(), formPanelRight.getCreationDate(), formPanelRight.getPetNameField(),
            null, null, null);
        if(formPanelRight.damagePerSecondIsAvailable()){
            character.setDamagePerSecond(formPanelRight.getDamagePerSecond());
        }
        frame.setCharacterForm(character);
        frame.setIndexPlayerAccount(formPanelLeftModify.getIndexPlayerAccount());
        frame.setIndexGame(formPanelLeftModify.getIndexGame());
        frame.setIndexServer(formPanelLeftModify.getIndexServer());
        frame.setIndexCharacterClass(formPanelLeftModify.getIndexCharacterClass());
    }

    public void setFormValue(){
        character = frame.getCharacterForm();
        if(character != null) {
            formPanelRight.setNameField(character.getName());
            formPanelRight.setHealthPointSlider(Character.getMinHp(), Character.getMaxHp(), character.getHealthPoints());
            formPanelRight.setStuffedCheckBox(character.isStuffed());
            if(formPanelLeftModify.isModifyPanel()) {
                formPanelRight.setCreationDateSpinner(character.getCreationDate().getTime());
            }else{
                formPanelRight.setCreationDateSpinner();
            }
            formPanelRight.setPetNameField(character.getPetName());
            if(character.getDamagePerSecond() != null) {
                formPanelRight.setDamagePerSecondActivated(true);
                formPanelRight.setDamagePerSecondSlider(character.getDamagePerSecond());
            }else{
                formPanelRight.setDamagePerSecondActivated(false);
                formPanelRight.setDamagePerSecondSlider(0);
            }
        }
        formPanelLeftModify.setPlayerAccountCombo(frame.getIndexPlayerAccount());
        formPanelLeftModify.setGameCombo(frame.getIndexGame());
        formPanelLeftModify.setServerCombo(frame.getIndexServer());
        formPanelLeftModify.setCharacterClassCombo(frame.getIndexCharacterClass());
    }

    public void resetLabel() {
        formPanelLeftModify.setPlayerAccountLabelReset();
        formPanelLeftModify.setGameLabelReset();
        formPanelLeftModify.setServerLabelReset();
        formPanelLeftModify.setCharacterClassLabelReset();
        if(formPanelLeftModify.isModifyPanel()){ formPanelLeftModify.setCharacterLabelReset(); }
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
        return !noSelection(formPanelLeftModify.getPseudoChoice())
                && !noSelection(formPanelLeftModify.getGameChoice())
                && !noSelection(formPanelLeftModify.getGameChoice())
                && !noSelection(formPanelLeftModify.getCharacterClassChoice())
                && ((formPanelLeftModify.isModifyPanel() && !noSelection(formPanelLeftModify.getCharacterChoice())) || formPanelLeftModify.getCharacterChoice() == null)
                && !formPanelRight.getNameField().isEmpty() && isNameValide(formPanelRight.getNameField())
                && formPanelRight.getHealthPointSlider() >= Character.getMinHp()
                && formPanelRight.getHealthPointSlider() <= formPanelRight.getHealthPointMax()
                && formPanelRight.getIsStuffedCheckBox() != null
                && formPanelRight.getCreationDate().after(formPanelRight.getEarliestDate())
                && formPanelRight.getCreationDate().before(formPanelRight.getLatestDate())
                && (formPanelRight.getPetNameField().isEmpty() || isNameValide(formPanelRight.getPetNameField()))
                && formPanelRight.getDamagePerSecond() >= Character.getMinDmg()
                && formPanelRight.getDamagePerSecond() <= Character.getMaxDmg();
                //getCreationDate().before(date) true if date is after getCreationDate
    }

    public boolean isNameValide(String name){
        return Pattern.matches("^[a-zA-Z_-]{4,50}", name);
    }

}
