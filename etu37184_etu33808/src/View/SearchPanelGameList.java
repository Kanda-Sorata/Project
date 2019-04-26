package View;

import Controller.*;
import Exception.*;
import Model.AccountPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SearchPanelGameList extends JPanel {
    private JComboBox pseudoPlayerCombo;
    private JComboBox numberPlayerCombo;
    private JComboBox characterNameCombo;
    private JSpinner dateEndSpinner;

    private String [] pseudos;
    private String [] numberPlayers;
    private String [] characterNames;
    private JLabel pseudo;
    private JLabel number;
    private JLabel characterName;
    private JLabel dateEnd;

    private ArrayList<AccountPlayer> players;
    private List<String> listPseudos;
    private String pseudoChoice;

    private AccountPlayerController accountPlayerController;

    public SearchPanelGameList(){

        setLayout(new GridLayout(4, 2, 5, 15));

        pseudo = new JLabel("Pseudo");
        pseudo.setHorizontalAlignment(SwingConstants.RIGHT);
        number = new JLabel("Number");
        number.setHorizontalAlignment(SwingConstants.RIGHT);
        characterName = new JLabel("Character");
        characterName.setHorizontalAlignment(SwingConstants.RIGHT);
        dateEnd = new JLabel("Date of end");
        dateEnd.setHorizontalAlignment(SwingConstants.RIGHT);

        //Fill tables
        setPseudos();
        pseudoPlayerCombo = new JComboBox(pseudos);
        setNumbers(pseudoChoice);
        numberPlayerCombo = new JComboBox(numberPlayers);
        characterNameCombo = new JComboBox(characterNames);
        dateEndSpinner = new JSpinner();

        add(pseudo);
        add(pseudoPlayerCombo);
        add(number);
        add(numberPlayerCombo);
        add(characterName);
        add(characterNameCombo);
        add(dateEnd);
        add(dateEndSpinner);
        setVisible(true);
    }

    public void setPseudoChoice(String pseudo){
        this.pseudoChoice = pseudo;
    }

    public void setPseudos() {
        try {
            int nbMaxPlayer = accountPlayerController.getNbAccountPlayers();

            setListPseudo();

            for (int iPseudo = 0; iPseudo < nbMaxPlayer; iPseudo++) {
                pseudos[iPseudo] = listPseudos.get(iPseudo);
            }
        } catch (NbAccountException nbAccountException){
            JOptionPane.showMessageDialog(null, "Error", nbAccountException.getMessage(), JOptionPane.ERROR_MESSAGE);;
        }
    }

    public void setListPseudo(){
        listPseudos = new ArrayList<>();
        try {
           players = accountPlayerController.getAllAccountPlayer();
            for (AccountPlayer player : players) {
                listPseudos.add(player.getPseudo());
            }
            listPseudos.stream().distinct().collect(Collectors.toList()); //Need documentation
        }catch (AllAccountException allAccountException){
            JOptionPane.showMessageDialog(null, "Error", allAccountException.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setNumbers(String pseudo){
        try{
            ArrayList<Integer> numbers = new ArrayList<>();
            ArrayList<AccountPlayer> players = accountPlayerController.getAllAccountPlayer();
            for(AccountPlayer player : players){
                if(player.getPseudo().equals(pseudo)){
                    numbers.add(player.getNumber());
                }
            }
            for(int iNumbers = 0; iNumbers < numbers.size(); iNumbers++){
                numberPlayers[iNumbers] = numbers.get(iNumbers).toString();
            }
        }catch(AllAccountException allAccountException){
            JOptionPane.showMessageDialog(null, "Error", allAccountException.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }
}
