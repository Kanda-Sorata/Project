package View.CharacterPanel;

import Controller.CharacterController;
import Exception.*;
import Model.Character;
import Model.SavedValueForm;
import View.Frame;
import View.HomePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

    private ArrayList<String> avoidedNames;

    public ButtonsPanel(){
        //Add properties
        setLayout(new GridLayout(1, 3, 5, 15));
        setBorder(new EmptyBorder(0, 250, 50, 250)); //Top, left, bottom, right

        characterController = new CharacterController();
        setAvoidedNames();

        //Add listener
        buttonListener = new ButtonListener();

        //Add component
        back = new JButton("Back (Home)");
        back.addActionListener(buttonListener);
        back.setToolTipText("You can save your work for further");
        reset = new JButton("Reset");
        reset.addActionListener(buttonListener);
        validation = new JButton("Validation");
        validation.addActionListener(buttonListener);

        add(back);
        add(reset);
        add(validation);
    }

    private void setAvoidedNames() {
        avoidedNames = new ArrayList<>();
        avoidedNames.add("zigounette");
        avoidedNames.add("turlutte");
        avoidedNames.add("tirer_un_coup");
        avoidedNames.add("tirer-un-coup");
        avoidedNames.add("ta_gueule");
        avoidedNames.add("ta-geule");
        avoidedNames.add("sucer");
        avoidedNames.add("geule");
        avoidedNames.add("emmerder");
        avoidedNames.add("démerder");
        avoidedNames.add("roubignoles");
        avoidedNames.add("putes");
        avoidedNames.add("putain");
        avoidedNames.add("pisser");
        avoidedNames.add("nique-ta-mère");
        avoidedNames.add("nique_ta_mère");
        avoidedNames.add("merdique");
        avoidedNames.add("niquer");
        avoidedNames.add("merdier");
        avoidedNames.add("merder");
        avoidedNames.add("merde");
        avoidedNames.add("gueuler");
        avoidedNames.add("foutre");
        avoidedNames.add("pipes");
        avoidedNames.add("chier");
        avoidedNames.add("entuber");
        avoidedNames.add("enculer");
        avoidedNames.add("emmerdeur");
        avoidedNames.add("emmerdeuse");
        avoidedNames.add("emmerdant");
        avoidedNames.add("culs");
        avoidedNames.add("couillu");
        avoidedNames.add("couille");
        avoidedNames.add("couilles");
        avoidedNames.add("coucougnettes");
        avoidedNames.add("conne");
        avoidedNames.add("connerie");
        avoidedNames.add("chiottes");
        avoidedNames.add("chiotte");
        avoidedNames.add("burnes");
        avoidedNames.add("burne");
        avoidedNames.add("branlette");
        avoidedNames.add("bordel");
        avoidedNames.add("bander");
        avoidedNames.add("branler");
        avoidedNames.add("baiser");
        avoidedNames.add("baise");
        avoidedNames.add("bite");
        avoidedNames.add("bander");
        avoidedNames.add("connard");
        avoidedNames.add("connasse");
        avoidedNames.add("salop");
        avoidedNames.add("salope");
        avoidedNames.add("câtain");
        avoidedNames.add("catin");
        avoidedNames.add("proute");
        avoidedNames.add("alboche");
        avoidedNames.add("boche");
        avoidedNames.add("chleuh");
        avoidedNames.add("frisé");
        avoidedNames.add("fritz");
        avoidedNames.add("prussien");
        avoidedNames.add("schleu");
        avoidedNames.add("amerloque");
        avoidedNames.add("amerlok");
        avoidedNames.add("amerloc");
        avoidedNames.add("ricain");
        avoidedNames.add("yankee");
        avoidedNames.add("angliche");
        avoidedNames.add("rosbif");
        avoidedNames.add("rosbeef");
        avoidedNames.add("arbi");
        avoidedNames.add("arabe");
        avoidedNames.add("bougnoule");
        avoidedNames.add("bridé");
        avoidedNames.add("chinetoque");
        avoidedNames.add("chinetok");
        avoidedNames.add("chinetoc");
        avoidedNames.add("chinnetoc");
        avoidedNames.add("chinnetok");
        avoidedNames.add("chinnetoque");
        avoidedNames.add("face_de_jaune");
        avoidedNames.add("face-de-jaune");
        avoidedNames.add("face jaune");
        avoidedNames.add("black");
        avoidedNames.add("sale");
        avoidedNames.add("espece-de");
        avoidedNames.add("espece-de");
        avoidedNames.add("race-de");
        avoidedNames.add("race_de");
        avoidedNames.add("bamboula");
        avoidedNames.add("blakos");
        avoidedNames.add("blaquos");
        avoidedNames.add("blacos");
        avoidedNames.add("nègre");
        avoidedNames.add("negre");
        avoidedNames.add("negresse");
        avoidedNames.add("négresse");
        avoidedNames.add("négrese");
        avoidedNames.add("ruskoff");
        avoidedNames.add("niaque");
        avoidedNames.add("zizis");
        avoidedNames.add("pénis");
        avoidedNames.add("verge");
        avoidedNames.add("négros");
        avoidedNames.add("négro");
        avoidedNames.add("nezgro");
        avoidedNames.add("nezgros");
        avoidedNames.add("nezgros");
        avoidedNames.add("null");
        avoidedNames.add("no selection");
        avoidedNames.add("fuck");
        avoidedNames.add("fuck you");
        avoidedNames.add("slut");
        avoidedNames.add("bitche");
        avoidedNames.add("bitches");
        avoidedNames.add("sluty");
        avoidedNames.add("shut up");
        avoidedNames.add("fuck off");
    }
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            formPanelRight.setDateChoice();
            if(actionEvent.getSource() == validation){
                if (isFormValid()) {
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
                        String msg = "The character " + character.getName() + " has been ";
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
                    } catch (UniqueNameException uniqueNameException) {
                        JOptionPane.showMessageDialog(null, uniqueNameException.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Some error has been found in the form, " +
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
                    if (formPanelRight.getNameField().isEmpty() || !isNameValid(formPanelRight.getNameField())) {
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
                    if (!formPanelRight.getPetNameField().isEmpty() && !isNameValid(formPanelRight.getPetNameField())) {
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
                    if (formPanelLeftModify.isModifyPanel()) {
                        frame.getSavedValueFormModify().setHaveSavedValue(false);
                    } else {
                        frame.getSavedValueFormNew().setHaveSavedValue(false);
                    }
                    try {
                        clearValueSaved();
                    } catch (HealthPointsException healthPointsException) {
                        JOptionPane.showMessageDialog(null, healthPointsException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (DamagePerSecondException damagePerSecondException) {
                        JOptionPane.showMessageDialog(null, damagePerSecondException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    try {
                        //filling values
                        setSaveValue();
                        if (formPanelLeftModify.isModifyPanel()) {
                            frame.getSavedValueFormModify().setHaveSavedValue(true);
                        } else {
                            frame.getSavedValueFormNew().setHaveSavedValue(true);
                        }
                        //update UI
                        frame.getContainer().removeAll();
                        frame.setTitleFrame("Home");
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
        SavedValueForm savedValueForm = new SavedValueForm();

        savedValueForm.setIndexCharacterClass(0);
        savedValueForm.setIndexPlayerAccount(0);
        savedValueForm.setIndexServer(0);
        savedValueForm.setIndexGame(0);
        character = new Character(null, 0, false, null, null,
                null, null, null);
        savedValueForm.setCharacterForm(character);
        if (formPanelLeftModify.isModifyPanel()) {
            frame.setSavedValueFormModify(savedValueForm);
        } else {
            frame.setSavedValueFormNew(savedValueForm);
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
        SavedValueForm savedValueForm = new SavedValueForm();
        character = new Character(formPanelRight.getNameField(), formPanelRight.getHealthPointSlider(),
            formPanelRight.getIsStuffedCheckBox(), formPanelRight.getCreationDate(), formPanelRight.getPetNameField(),
            null, null, null);
        if(formPanelRight.damagePerSecondIsAvailable()){
            character.setDamagePerSecond(formPanelRight.getDamagePerSecond());
        }
        savedValueForm.setCharacterForm(character);
        savedValueForm.setIndexPlayerAccount(formPanelLeftModify.getIndexPlayerAccount());
        savedValueForm.setIndexGame(formPanelLeftModify.getIndexGame());
        savedValueForm.setIndexServer(formPanelLeftModify.getIndexServer());
        savedValueForm.setIndexCharacterClass(formPanelLeftModify.getIndexCharacterClass());
        if (formPanelLeftModify.isModifyPanel()) {
            frame.setSavedValueFormModify(savedValueForm);
        } else {
            frame.setSavedValueFormNew(savedValueForm);
        }
    }

    public void setFormValue(){
        SavedValueForm savedValueForm;
        if (formPanelLeftModify.isModifyPanel()) {
            savedValueForm = frame.getSavedValueFormModify();
        } else {
            savedValueForm = frame.getSavedValueFormNew();
        }
        character = savedValueForm.getCharacterForm();
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
        formPanelLeftModify.setPlayerAccountCombo(savedValueForm.getIndexPlayerAccount());
        formPanelLeftModify.setGameCombo(savedValueForm.getIndexGame());
        formPanelLeftModify.setServerCombo(savedValueForm.getIndexServer());
        formPanelLeftModify.setCharacterClassCombo(savedValueForm.getIndexCharacterClass());
    }

    public void resetLabel() {
        formPanelLeftModify.setPlayerAccountLabelReset();
        formPanelLeftModify.setGameLabelReset();
        formPanelLeftModify.setServerLabelReset();
        formPanelLeftModify.setCharacterClassLabelReset();
        if(formPanelLeftModify.isModifyPanel()){ formPanelLeftModify.setCharacterLabelReset(); }
        formPanelRight.setNameLabelReset();
        formPanelRight.setHealthPointLabelReset();
        formPanelRight.setPetNameLabelReset();
        formPanelRight.setCreationDateLabelReset();
    }


    public boolean noSelection(String input){
        if(input != null) {
            return input.equals("No selection");
        }
        return true;
    }

    public boolean isFormValid() {
        return isComboValid()
                && !formPanelRight.getNameField().isEmpty()
                && formPanelRight.getNameField().length() <= 50
                && textFieldDifferToNull(formPanelRight.getNameField())
                && isNameValid(formPanelRight.getNameField())
                && formPanelRight.getHealthPointSlider() >= Character.getMinHp()
                && formPanelRight.getHealthPointSlider() <= formPanelRight.getHealthPointMax()
                && formPanelRight.getCreationDate().before(formPanelRight.getLatestDate())
                && (formPanelRight.getPetNameField().isEmpty() || testFieldNullable(formPanelRight.getPetNameField()))
                && formPanelRight.getDamagePerSecond() >= Character.getMinDmg()
                && formPanelRight.getDamagePerSecond() <= Character.getMaxDmg();
                //getCreationDate().before(date) true if date is after getCreationDate
    }

    private boolean isNameValid(String name) {

        return Pattern.matches("^[a-zA-Z_-]{4,50}", name)
                && !avoidedNames.contains(name.toLowerCase())
                && !Pattern.matches("(.)\\1{2,}", name);
    }

    private boolean isComboValid() {
        return !noSelection(formPanelLeftModify.getPseudoChoice())
                && !noSelection(formPanelLeftModify.getGameChoice())
                && !noSelection(formPanelLeftModify.getGameChoice())
                && !noSelection(formPanelLeftModify.getCharacterClassChoice())
                && ((formPanelLeftModify.isModifyPanel() && !noSelection(formPanelLeftModify.getCharacterChoice()))
                || formPanelLeftModify.getCharacterChoice() == null);
    }

    private boolean textFieldDifferToNull(String value) {
        return !value.toLowerCase().equals("null");
    }

    private boolean testFieldNullable(String value) {
        return value.length() <= 50
                && textFieldDifferToNull(value)
                && isNameValid(formPanelRight.getPetNameField());
    }
}
