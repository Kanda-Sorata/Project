package View.SearchPanel;

import Controller.CharacterController;
import Controller.PlayerAccountController;
import Exception.DataAccessException;
import Exception.DataException;
import Model.Character;
import View.UtilitiesPanelMethod;

import javax.swing.*;
import javax.swing.JSpinner.DateEditor;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

    private ArrayList<String> playerAccounts;
    private ArrayList<String> charactersName;
    private ArrayList<Character> characters;
    private JLabel playerAccount;
    private JLabel characterName;
    private JLabel dateEnd;

    private String pseudoChoice;
    private int numberChoice;
    private String characterNameChoice;
    private GregorianCalendar dateChoice;

    private PlayerAccountController playerAccountController;
    private CharacterController characterController;
    private UtilitiesPanelMethod utilitiesPanelMethod;

    private ComboBoxListener comboBoxListener;
    private SpinnerListener spinnerListener;

    private ResultGamePanel resultGamePanel;
    private JLabel validationLabel;
    private JButton validation;
    private ButtonListener buttonListener;

    public SearchPanelGame(ResultGamePanel resultGamePanel) throws DataAccessException, DataException {
        //Add properties
        setLayout(new GridLayout(4, 2, 5, 15));
        setBorder(new EmptyBorder(150, 0, 250, 250)); //top, left, bottom, right

        //Init
        this.resultGamePanel = resultGamePanel;
        playerAccountController = new PlayerAccountController();
        characterController = new CharacterController();
        utilitiesPanelMethod = new UtilitiesPanelMethod();

        playerAccounts = utilitiesPanelMethod.setPlayerAccountsPseudo();

        //Add component
        playerAccount = new JLabel("Player Account");
        playerAccount.setHorizontalAlignment(SwingConstants.RIGHT);
        characterName = new JLabel("Character");
        characterName.setHorizontalAlignment(SwingConstants.RIGHT);
        dateEnd = new JLabel("Date of end");
        dateEnd.setHorizontalAlignment(SwingConstants.RIGHT);


        playerAccountCombo = new JComboBox(playerAccounts.toArray());
        playerAccountCombo.setSelectedIndex(0);
        playerAccountCombo.setMaximumRowCount(3);

        String[] temp = {"No selection"};
        characterNameCombo = new JComboBox(temp);
        characterNameCombo.setSelectedIndex(0);
        characterNameCombo.setMaximumRowCount(3);
        characterNameCombo.setEnabled(false);

        dateEndSpinner = new JSpinner();
        Calendar calendar = Calendar.getInstance();
        Date earliestDate = calendar.getTime();
        setJSpinner(earliestDate);

        //Add listener
        comboBoxListener = new ComboBoxListener();
        playerAccountCombo.addItemListener(comboBoxListener);
        characterNameCombo.addItemListener(comboBoxListener);
        spinnerListener = new SpinnerListener();
        dateEndSpinner.addChangeListener(spinnerListener);

        validationLabel = new JLabel("Validation");
        validationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        validation = new JButton("Validation");
        buttonListener = new ButtonListener();
        validation.addActionListener(buttonListener);

        add(playerAccount);
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
        calendar.add(Calendar.YEAR, 20);
        Date latestDate = calendar.getTime();

        spinnerModel = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.MONTH); //getPrevious & nextValue method
        dateEndSpinner.setModel(spinnerModel);
        dateEditor = new JSpinner.DateEditor(dateEndSpinner, "dd/MM/yyyy");
        dateEndSpinner.setEditor(dateEditor);
    }

    public String getPseudoChoice() {
        return pseudoChoice;
    }

    public int getNumberChoice() {
        return numberChoice;
    }

    public void setDateChoice(){
        Date date = (Date) dateEndSpinner.getValue();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        this.dateChoice = calendar;
    }

    public void setCharacterName() throws DataException, DataAccessException {
        characters = characterController.getAllCharacter(pseudoChoice, numberChoice);
        if(characters.size() > 0) {
            charactersName = new ArrayList<>();
            charactersName.add("No Selection");
            for (Character character : characters) {
                charactersName.add(character.getName());
            }
        }
    }

    private class ComboBoxListener implements ItemListener {

        @Override
        public void itemStateChanged (ItemEvent itemEvent) {
            if(itemEvent.getSource() == playerAccountCombo) {
                if (playerAccountCombo.getSelectedIndex() != 0) {
                    pseudoChoice = playerAccounts.get(playerAccountCombo.getSelectedIndex()).split("#")[0];
                    numberChoice = Integer.parseInt(playerAccounts.get(playerAccountCombo.getSelectedIndex()).split("#")[1]);
                    try {
                        setCharacterName();
                        characterNameCombo.setModel(new DefaultComboBoxModel(charactersName.toArray()));
                        characterNameCombo.setEnabled(true);
                    } catch (DataException dataException) {
                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (DataAccessException dataAccessException) {
                        JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    characterNameCombo.setSelectedIndex(0);
                    characterNameCombo.setEnabled(false);
                }
            }else{
                if (characterNameCombo.getSelectedIndex() > 0) {
                    characterNameChoice = charactersName.get(characterNameCombo.getSelectedIndex());
                    setJSpinner(getCharacterByName(characterNameChoice).getCreationDate().getTime());
                }
            }
        }
    }

    private class SpinnerListener implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent changeEvent){
            if(changeEvent.getSource() == dateEndSpinner) {
                setDateChoice();
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
                setDateChoice();
                if (playerAccountCombo.getSelectedIndex() > 0 && characterNameCombo.getSelectedIndex() > 0 && dateChoice != null) {
                    resultGamePanel.setJTable(pseudoChoice, numberChoice, characterNameChoice, dateChoice);
                }
            }catch(DataException dataException){
                JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }catch (DataAccessException dataAccessException){
                JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}