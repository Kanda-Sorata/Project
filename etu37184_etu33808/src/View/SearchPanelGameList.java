package View;

import Controller.*;
import Exception.*;
import Model.AccountPlayer;
import Model.Character;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class SearchPanelGameList extends JPanel {
    private JComboBox pseudoPlayerCombo;
    private JComboBox characterNameCombo;
    private JSpinner dateEndSpinner;

    private String [] pseudos;
    private String [] characterNames;
    private JLabel pseudo;
    private JLabel characterName;
    private JLabel dateEnd;

    private String pseudoChoice;
    private String numberChoice;

    private AccountPlayerController accountPlayerController;
    private CharacterController characterController;

    public SearchPanelGameList(){
        setLayout(new GridLayout(4, 2, 5, 15));

        pseudo = new JLabel("Pseudo");
        pseudo.setHorizontalAlignment(SwingConstants.RIGHT);
        characterName = new JLabel("Character");
        characterName.setHorizontalAlignment(SwingConstants.RIGHT);
        dateEnd = new JLabel("Date of end");
        dateEnd.setHorizontalAlignment(SwingConstants.RIGHT);


        setPseudos();
        pseudoPlayerCombo = new JComboBox(pseudos);
        setCharacterName();
        characterNameCombo = new JComboBox(characterNames);
        dateEndSpinner = new JSpinner();

        add(pseudo);
        add(pseudoPlayerCombo);
        add(characterName);
        add(characterNameCombo);
        add(dateEnd);
        add(dateEndSpinner);
        setVisible(true);
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
