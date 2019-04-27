package View;

import Controller.*;
import Exception.*;
import Model.AccountPlayer;
import Model.Character;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    private AccountPlayerController accountPlayerController;
    private CharacterController characterController;

    public SearchPanelGameList(){
        setLayout(new GridLayout(4, 2, 5, 15));
        setBorder(new EmptyBorder(150, 0, 250, 250)); //top, left, bottom, right
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
}
