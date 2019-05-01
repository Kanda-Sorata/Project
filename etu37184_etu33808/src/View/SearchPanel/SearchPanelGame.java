package View.SearchPanel;
//todo changer les tableaux et mettre arraylist.toArray()

import Controller.AccountPlayerController;
import Controller.CharacterController;
import Exception.DataException;
import Model.Character;

import javax.swing.*;
import javax.swing.JSpinner.DateEditor;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class SearchPanelGame extends JPanel {
    private JComboBox playerAccountCombo;
    private JComboBox characterNameCombo;
    private JSpinner dateEndSpinner;
    private SpinnerModel spinnerModel;
    private DateEditor dateEditor;

    private String [] playerAccounts;
    private String [] characterNames;
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

    private ComboBoxPlayer comboBoxPlayerListener;
    private ComboBoxCharacter comboBoxCharacterListener;
    private SpinnerListener spinnerListener;

    private ArrayList<Character> characters;

    private GamePanel gamePanel;
    private JLabel validationLabel;
    private JButton validation;
    private ButtonListener buttonListener;

    public SearchPanelGame(GamePanel gamePanel)throws DataException {
        this.gamePanel = gamePanel;
        accountPlayerController = new AccountPlayerController();
        characterController = new CharacterController();
        utilitiesPanelMethode = new UtilitiesPanelMethode();

        //Add propeties
        setLayout(new GridLayout(4, 2 , 5, 15));
        setBorder(new EmptyBorder(150, 0, 250, 250)); //top, left, bottom, right

        //add component
        playerAcocunt = new JLabel("Player Account");
        playerAcocunt.setHorizontalAlignment(SwingConstants.RIGHT);
        characterName = new JLabel("Character");
        characterName.setHorizontalAlignment(SwingConstants.RIGHT);
        dateEnd = new JLabel("Date of end");
        dateEnd.setHorizontalAlignment(SwingConstants.RIGHT);

        //playerAccounts = utilitiesPanelMethode.setPlayerAccountsPseudo();
        playerAccountCombo = new JComboBox(playerAccounts);
        playerAccountCombo.setSelectedIndex(0);
        playerAccountCombo.setMaximumRowCount(3);

        String [] temp = {"Aucune selection"};
        characterNameCombo = new JComboBox(temp);
        characterNameCombo.setSelectedIndex(0);
        characterNameCombo.setMaximumRowCount(3);
        characterNameCombo.setEnabled(false);

        dateEndSpinner = new JSpinner();
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.YEAR, -5);
        Date earliestDate = calendar.getTime();
        setJSpinner(earliestDate);

        //Add listener
        comboBoxPlayerListener = new ComboBoxPlayer();
        playerAccountCombo.addActionListener(comboBoxPlayerListener);
        comboBoxCharacterListener = new ComboBoxCharacter();
        characterNameCombo.addActionListener(comboBoxCharacterListener);
        spinnerListener = new SpinnerListener();
        dateEndSpinner.addChangeListener(spinnerListener);

        validationLabel = new JLabel("Validation");
        validationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        validation = new JButton("Validation");
        buttonListener = new ButtonListener();
        validation.addActionListener(buttonListener);

        add(playerAcocunt);
        add(playerAccountCombo);
        add(characterName);
        add(characterNameCombo);
        add(dateEnd);
        add(dateEndSpinner);
        add(validationLabel);
        add(validation);
    }

    public void setJSpinner(Date earliestDate){
        Calendar calendar = Calendar.getInstance();
        Date initDate = calendar.getTime();
        calendar.add(calendar.YEAR, 20);
        Date latestDate = calendar.getTime();

        spinnerModel = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.MONTH);
        dateEndSpinner.setModel(spinnerModel);
        dateEndSpinner.setEditor(new JSpinner.DateEditor(dateEndSpinner, "dd/MM/yyyy"));
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
        return  dateChoice;
    }

    public void setDateChoice(GregorianCalendar dateChoice){
        this.dateChoice = dateChoice;
    }

    public void setCharacterName() throws DataException {
        characters = characterController.getAllCharacter(pseudoChoice, numberChoice);
        if(characters.size() > 0) {
            characterNames = new String[characters.size()+1];
            characterNames[0] = "No selection";
            for (int iCharacter = 0; iCharacter < characters.size(); iCharacter++) {
                characterNames[iCharacter+1] = characters.get(iCharacter).getName();
            }
        }
    }

    private class ComboBoxPlayer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (playerAccountCombo.getSelectedItem() != null) {
                setPseudoChoice(playerAccounts[playerAccountCombo.getSelectedIndex()].split("#")[0]);
                setNumberChoice(playerAccounts[playerAccountCombo.getSelectedIndex()].split("#")[1]);
                try {
                    setCharacterName();
                    if (characterNames.length > 0) {
                        characterNameCombo.setModel(new DefaultComboBoxModel(characterNames));
                        repaint();
                    }
                } catch (DataException dataException) {
                    JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                characterNameCombo.setEnabled(true);
            }
        }
    }

    private class ComboBoxCharacter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (!characterNames[characterNameCombo.getSelectedIndex()].equals(characterNames[0])) {
                setCharacterNameChoice(characterNames[characterNameCombo.getSelectedIndex()]);
                setJSpinner(getCharacterByName(characterNameChoice).getCreationDate().getGregorianChange());
            }
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
        while(iChar < characters.size() && !name.equals(characters.get(iChar).getName())){ iChar++; }
        return characters.get(iChar);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if(getPseudoChoice() != null && getCharacterNameChoice() != null && dateEndSpinner.getValue() != null) {
                    gamePanel.setJtable();
                }
            }catch(DataException dataException){
                JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}