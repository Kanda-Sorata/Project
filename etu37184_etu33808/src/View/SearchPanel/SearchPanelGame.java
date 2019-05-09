package View.SearchPanel;

import Controller.AccountPlayerController;
import Controller.CharacterController;
import Exception.DataAccessException;
import Exception.DataException;
import Model.Character;
import View.UtilitiesPanelMethode;

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

    private AccountPlayerController accountPlayerController;
    private CharacterController characterController;
    private UtilitiesPanelMethode utilitiesPanelMethode;

    private ComboBoxListener comboBoxListener;
    private SpinnerListener spinnerListener;

    private ResultGamePanel resultGamePanel;
    private JLabel validationLabel;
    private JButton validation;
    private ButtonListener buttonListener;

    public SearchPanelGame(ResultGamePanel resultGamePanel) {
        try {
            this.resultGamePanel = resultGamePanel;
            accountPlayerController = new AccountPlayerController();
            characterController = new CharacterController();
            utilitiesPanelMethode = new UtilitiesPanelMethode();

            playerAccounts = utilitiesPanelMethode.setPlayerAccountsPseudo();

            //Add propeties
            setLayout(new GridLayout(4, 2, 5, 15));
            setBorder(new EmptyBorder(150, 0, 250, 250)); //top, left, bottom, right

            //add component
            playerAccount = new JLabel("Player Account");
            playerAccount.setHorizontalAlignment(SwingConstants.RIGHT);
            characterName = new JLabel("Character");
            characterName.setHorizontalAlignment(SwingConstants.RIGHT);
            dateEnd = new JLabel("Date of end");
            dateEnd.setHorizontalAlignment(SwingConstants.RIGHT);


            playerAccountCombo = new JComboBox(playerAccounts.toArray());
            playerAccountCombo.setSelectedIndex(0);
            playerAccountCombo.setMaximumRowCount(3);

            String[] temp = {"Aucune selection"};
            characterNameCombo = new JComboBox(temp);
            characterNameCombo.setSelectedIndex(0);
            characterNameCombo.setMaximumRowCount(3);
            characterNameCombo.setEnabled(false);

            dateEndSpinner = new JSpinner();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -5);
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
            setVisible(true);
        }catch(DataException dataException){
            JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch(DataAccessException dataAccessException){
            JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setJSpinner(Date earliestDate){
        Calendar calendar = Calendar.getInstance();
        Date initDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 20);
        Date latestDate = calendar.getTime();

        spinnerModel = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.MONTH); //getPrevious & nextvalue method
        dateEndSpinner.setModel(spinnerModel);
        dateEditor = new JSpinner.DateEditor(dateEndSpinner, "dd/MM/yyyy");
        dateEndSpinner.setEditor(dateEditor);
    }

    public void setPseudoChoice(String pseudoChoice){
        this.pseudoChoice = pseudoChoice;
    }

    public void setNumberChoice(int numberChoice){
        this.numberChoice = numberChoice;
    }

    public String getPseudoChoice() {
        return pseudoChoice;
    }

    public int getNumberChoice() {
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
                    setPseudoChoice(playerAccounts.get(playerAccountCombo.getSelectedIndex()).split("#")[0]);
                    setNumberChoice(Integer.parseInt(playerAccounts.get(playerAccountCombo.getSelectedIndex()).split("#")[1]));
                    try {
                        setCharacterName();
                        if (charactersName.size() > 0) {
                            characterNameCombo.setModel(new DefaultComboBoxModel(charactersName.toArray()));
                            repaint();
                        }
                    } catch (DataException dataException) {
                        JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (DataAccessException dataAccexException) {
                        JOptionPane.showMessageDialog(null, dataAccexException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    characterNameCombo.setEnabled(true);
                } else {
                    characterNameCombo.setSelectedIndex(0);
                    characterNameCombo.setEnabled(false);
                }
            }else{
                if (!charactersName.get(characterNameCombo.getSelectedIndex()).equals(charactersName.get(0))) {
                    setCharacterNameChoice(charactersName.get(characterNameCombo.getSelectedIndex()));
                    setJSpinner(getCharacterByName(characterNameChoice).getCreationDate().getGregorianChange());
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
                if(playerAccountCombo.getSelectedIndex() != 0 && characterNameCombo.getSelectedIndex() != 0 && dateChoice != null) {
                    resultGamePanel.setJtable(pseudoChoice, numberChoice, characterNameChoice, dateChoice);
                }
            }catch(DataException dataException){
                JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }catch (DataAccessException dataAccessException){
                JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}