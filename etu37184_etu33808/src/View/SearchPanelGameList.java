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
    private int [] numberPlayers;
    private String [] characterNames;
    JLabel pseudo;
    JLabel number;
    JLabel characterName;
    JLabel dateEnd;

    ArrayList<AccountPlayer> players;

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
        setNumbers();
        pseudoPlayerCombo = new JComboBox(pseudos);
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

    public void setPseudos() {
        try {
            int nbMaxPlayer = accountPlayerController.getNbAccountPlayers();

            List<String> listPseudos = setListPseudo();

            for (int iPseudo = 0; iPseudo < nbMaxPlayer; iPseudo++) {
                pseudos[iPseudo] = listPseudos.get(iPseudo);
            }
        } catch (NbAccountException nbAccountException){
            JOptionPane.showMessageDialog(null, "Error", nbAccountException.getMessage(), JOptionPane.ERROR_MESSAGE);;
        }
    }

    public List<String> setListPseudo(){
        List<String> listPseudos = new ArrayList<>();
        try {
           players = accountPlayerController.getAllAccountPlayer();
            for (AccountPlayer player : players) {
                listPseudos.add(player.getPseudo());
            }
            return  listPseudos.stream().distinct().collect(Collectors.toList());
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
                numberPlayers[iNumbers] = numbers.get(iNumbers);
            }
        }catch(AllAccountException allAccountException){
            JOptionPane.showMessageDialog(null, "Error", allAccountException.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }
}
