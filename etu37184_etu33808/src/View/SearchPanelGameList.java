package View;

import Controller.AccountPlayerController;
import Controller.CharacterController;
import Exception.AllCharacterException;
import  Exception.AllAccountException;
import Exception.NbAccountException;
import Model.Character;

import javax.swing.*;
import javax.swing.JSpinner.DateEditor;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;


public class SearchPanelGameList extends JPanel {
    private JComboBox playerAccountCombo;
    private JComboBox characterNameCombo;
    private JSpinner dateEndSpinner;
    private SpinnerModel spinnerModel;
    private DateEditor dateEditor;

    private String [] playerAccounts = {"Test", "TestDeux", "TestTrois", "TestQuattre"};
    private String [] characterNames = {"Character Un", "Character Deux", "Character Trois", "Character Quattre"};
    private JLabel playerAcocunt;
    private JLabel characterName;
    private JLabel dateEnd;

    private String pseudoChoice;
    private String numberChoice;
    private String characterNameChoice;
    private GregorianCalendar dateChoice;

    private AccountPlayerController accountPlayerController;
    private CharacterController characterController;
    private UtilitiesPanelMethode utilitiesPanelMethode;

    private ComboBox comboBoxListener;
    private SpinnerListener spinnerListener;

    ArrayList<Character> characters;

    public SearchPanelGameList()throws AllCharacterException, NbAccountException, AllAccountException {
        accountPlayerController = new AccountPlayerController();
        characterController = new CharacterController();
        utilitiesPanelMethode = new UtilitiesPanelMethode();

        //Add propeties
        setLayout(new GridLayout(4, 2, 5, 15));
        setBorder(new EmptyBorder(150, 0, 250, 250)); //top, left, bottom, right

        //add component
        playerAcocunt = new JLabel("Player Account");
        playerAcocunt.setHorizontalAlignment(SwingConstants.RIGHT);
        characterName = new JLabel("Character");
        characterName.setHorizontalAlignment(SwingConstants.RIGHT);
        dateEnd = new JLabel("Date of end");
        dateEnd.setHorizontalAlignment(SwingConstants.RIGHT);

        //playerAccounts = utilitiesPanelMethode.setPseudos();
        playerAccountCombo = new JComboBox(playerAccounts);
        playerAccountCombo.setSelectedIndex(0);
        playerAccountCombo.setMaximumRowCount(3);
        //setCharacterName();
        characterNameCombo = new JComboBox(characterNames);
        characterNameCombo.setSelectedIndex(0);
        characterNameCombo.setMaximumRowCount(3);

        dateEndSpinner = new JSpinner();
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.YEAR, -5);
        Date earliestDate = calendar.getTime();
        setJSpinner(earliestDate);

        //Add listener
        comboBoxListener = new ComboBox();
        playerAccountCombo.addItemListener(comboBoxListener);
        characterNameCombo.addItemListener(comboBoxListener);
        spinnerListener = new SpinnerListener();
        dateEndSpinner.addChangeListener(spinnerListener);


        add(playerAcocunt);
        add(playerAccountCombo);
        add(characterName);
        add(characterNameCombo);
        add(dateEnd);
        add(dateEndSpinner);
    }

    public void setJSpinner(Date earliestDate){
        Calendar calendar = Calendar.getInstance();
        Date initDate = calendar.getTime();
        calendar.add(calendar.YEAR, 20);
        Date latestDate = calendar.getTime();

        spinnerModel = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.MONTH);
        dateEndSpinner.setModel(spinnerModel);
        dateEndSpinner.setEditor(new JSpinner.DateEditor(dateEndSpinner, "dd/MMMM/yyyy"));
    }

    public void setPseudoChoice(String pseudoChoice){
        this.pseudoChoice = pseudoChoice;
    }

    public void setNumberChoice(String numberChoice){
        this.numberChoice = numberChoice;
    }

    public String getPseudoChoice() {
        return pseudoChoice;
    }

    public String getNumberChoice() {
        return numberChoice;
    }

    public String getCharacterNameChoice() {
        return characterNameChoice;
    }

    public void setCharacterNameChoice(String characterNameChoice) {
        this.characterNameChoice = characterNameChoice;
    }

    public GregorianCalendar getDateEnd(){
        return  dateChoice; //
    }

    public void setDateChoice(GregorianCalendar dateChoice){
        this.dateChoice = dateChoice;
    }

    public void setCharacterName() throws AllCharacterException {
            characters = characterController.getAllCharacter(pseudoChoice, numberChoice);
            for (int iCharacter = 0; iCharacter < characters.size(); iCharacter++) {
                characterNames[iCharacter] = characters.get(iCharacter).getName();
            }
    }

    private class ComboBox implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if(itemEvent.getItem() == playerAccountCombo){
                setPseudoChoice(playerAccounts[playerAccountCombo.getSelectedIndex()].split("#")[0]);
                setNumberChoice(playerAccounts[playerAccountCombo.getSelectedIndex()].split("#")[1]);
            }
            else{
                setCharacterNameChoice(characterNames[characterNameCombo.getSelectedIndex()]);
            }
            //setJSpinner(getCharacterByName(characterNameChoice).getCreationDate().getTime());
        }
    }

    private class SpinnerListener implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent changeEvent){
            if(changeEvent.getSource() == dateEndSpinner) {
                Date date = (Date) dateEndSpinner.getValue();
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(date);
                setDateChoice(calendar);
            }
        }
    }

    public Character getCharacterByName(String name){
        int iChar = 0;
        while(iChar < characters.size() && !name.equals(characters.get(iChar))){ iChar++; }

        return characters.get(iChar);
    }
}
