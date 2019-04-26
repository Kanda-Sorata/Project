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

    //Add date picker

    private String [] pseudos;
    private String [] numberPlayers;
    private String [] characterNames;
    JLabel pseudo;
    JLabel number;
    JLabel characterName;
    JLabel dateEnd;

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
        //Date

        add(pseudo);
        add(pseudoPlayerCombo);
        add(number);
        add(numberPlayerCombo);
        add(characterName);
        add(characterNameCombo);
        add(dateEnd);
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
            ArrayList<AccountPlayer> players = accountPlayerController.getAllAccountPlayer();
            for (AccountPlayer player : players) {
                listPseudos.add(player.getPseudo());
            }
            return  listPseudos.stream().distinct().collect(Collectors.toList());
        }catch (AllAccountException allAccountException){
            JOptionPane.showMessageDialog(null, "Error", allAccountException.getMessage(), JOptionPane.ERROR_MESSAGE);;
        }
    }

    public void setNumbers(){

    }
}
