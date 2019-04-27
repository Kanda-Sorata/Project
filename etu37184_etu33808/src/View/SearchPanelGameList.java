package View;

import Controller.*;
import Exception.*;
import Model.AccountPlayer;
import Model.Character;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.List;


public class SearchPanelGameList extends JPanel {
    private JComboBox pseudoPlayerCombo;
    private JComboBox characterNameCombo;
    private JSpinner.DateEditor dateEndSpinner;

    private String [] pseudos = {"Test", "TestDeux", "TestTrois", "TestQuattre"};;
    private String [] characterNames = {"Character Un", "Character Deux", "Character Trois", "Character Quattre"};;
    private JLabel pseudo;
    private JLabel characterName;
    private JLabel dateEnd;

    private String pseudoChoice;
    private String numberChoice;
    private String characterNameChoice;
    private GregorianCalendar dateChoice;

    private AccountPlayerController accountPlayerController;
    private CharacterController characterController;

    private ComboBox comboBoxListener;

    public SearchPanelGameList(){
        //Add propeties
        setLayout(new GridLayout(4, 2, 5, 15));
        setBorder(new EmptyBorder(150, 0, 250, 250)); //top, left, bottom, right

        //add component
        pseudo = new JLabel("Pseudo");
        pseudo.setHorizontalAlignment(SwingConstants.RIGHT);
        characterName = new JLabel("Character");
        characterName.setHorizontalAlignment(SwingConstants.RIGHT);
        dateEnd = new JLabel("Date of end");
        dateEnd.setHorizontalAlignment(SwingConstants.RIGHT);

        //setPseudos();
        pseudoPlayerCombo = new JComboBox(pseudos);
        pseudoPlayerCombo.setSelectedIndex(0);
        pseudoPlayerCombo.setMaximumRowCount(3);
        //setCharacterName();
        characterNameCombo = new JComboBox(characterNames);
        characterNameCombo.setSelectedIndex(0);
        characterNameCombo.setMaximumRowCount(3);
        setJSpinner();

        //Add listener
        comboBoxListener = new ComboBox();
        pseudoPlayerCombo.addItemListener(comboBoxListener);
        characterNameCombo.addItemListener(comboBoxListener);
        dateEndSpinner.getSpinner().addChangeListener(new SpinnerListener());

        add(pseudo);
        add(pseudoPlayerCombo);
        add(characterName);
        add(characterNameCombo);
        add(dateEnd);
        add(dateEndSpinner);
        setVisible(true);
    }

    public void setJSpinner(){
        SpinnerDateModel mod = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
        dateEndSpinner = new JSpinner.DateEditor(new JSpinner(mod), "DD / MMMM / YYYY");
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

    public void setPseudos() {
        try {
            int nbMaxPlayer = accountPlayerController.getNbAccountPlayers();
            ArrayList<AccountPlayer> players = accountPlayerController.getAllAccountPlayer();
            for (int iPseudo = 0; iPseudo < nbMaxPlayer; iPseudo++) {
                pseudos[iPseudo] = players.get(iPseudo).getPseudo() + "#" + players.get(iPseudo).getNumber();
            }
        } catch (NbAccountException nbAccountException){
            JOptionPane.showMessageDialog(null, "Error", nbAccountException.getMessage(), JOptionPane.ERROR_MESSAGE);;
        }catch(AllAccountException allAccountException){
            JOptionPane.showMessageDialog(null, "Error", allAccountException.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setCharacterName(){
        try{
            ArrayList<Character> characters = characterController.getAllCharacter(pseudoChoice, numberChoice);
            for(int iCharacter = 0; iCharacter < characters.size(); iCharacter++){
                characterNames[iCharacter] = characters.get(iCharacter).getName();
            }
        }catch (AllCharacterException allCharacterException){
            JOptionPane.showMessageDialog(null, "Error", allCharacterException.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private class ComboBox implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if(itemEvent.getItem() == pseudoPlayerCombo){
                setPseudoChoice(pseudoPlayerCombo.getSelectedItem().toString().split("#")[0]);
                setNumberChoice(pseudoPlayerCombo.getSelectedItem().toString().split("#")[2]);
            }
            else{
                setCharacterNameChoice(characterNameCombo.getSelectedItem().toString());
            }
        }
    }

    private class SpinnerListener implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            Date date = ((SpinnerDateModel)changeEvent.getSource()).getDate();
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            setDateChoice(calendar);
        }
    }
}
