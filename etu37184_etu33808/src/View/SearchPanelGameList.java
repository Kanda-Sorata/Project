package View;

import Controller.*;
import Exception.*;

import javax.swing.*;
import java.awt.*;


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

    public SearchPanelGameList(){

        setLayout(new GridLayout(4, 2, 5, 15));

        pseudo = new JLabel("Pseudo");
        pseudo.setHorizontalAlignment(SwingConstants.RIGHT);
        number = new JLabel("Number");
        number.setHorizontalAlignment(SwingConstants.RIGHT);
        characterName = new JLabel("Character");
        characterName.setHorizontalAlignment(SwingConstants.RIGHT);
        dateEnd = new JLabel("Date de fin");
        dateEnd.setHorizontalAlignment(SwingConstants.RIGHT);

        //Fill tables
        setPseudos();
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
            int nbMaxPlayer = AccountPlayerController.getNbAccountPlayers();

            for (int iPseudo = 0; iPseudo < nbMaxPlayer; iPseudo++) {
                try {
                    pseudos[iPseudo] = Controller.AccountPlayerController.getAllAccountPlayer().get(iPseudo).getPseudo();
                } catch (SelectException selectException) {
                    JOptionPane.showMessageDialog(null, "Error request sql", selectException.getRequest(), JOptionPane.ERROR_MESSAGE);
                } catch (NameException nameException) {
                    JOptionPane.showMessageDialog(null, "Error name", nameException.getUnavailableName(), JOptionPane.ERROR_MESSAGE);
                } catch (SexException sexException) {
                    JOptionPane.showMessageDialog(null, "Error sex", sexException.getUnavailableSex()+"", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch(SelectException selectException){
            selectException.getRequest();
        }
    }
}
