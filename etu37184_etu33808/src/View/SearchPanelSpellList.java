package View;

import Controller.AccountPlayerController;

import javax.swing.*;
import java.awt.*;

public class SearchPanelSpellList extends JPanel {
    private JComboBox playerAcocuntCombo;
    private String[] pseudos;
    private JLabel playerAcocunt;

    private UtilitiesPanelMethode utilitiesPanelMethode;

    private AccountPlayerController accountPlayerController;

    public SearchPanelSpellList() {
        utilitiesPanelMethode = new UtilitiesPanelMethode();
        accountPlayerController = new AccountPlayerController();

        setLayout(new GridLayout(4, 2, 5, 15));

        playerAcocunt = new JLabel("Player Account");
        playerAcocunt.setHorizontalAlignment(SwingConstants.RIGHT);



        //utilitiesPanelMethode.setPseudos(pseudos);
        playerAcocuntCombo = new JComboBox(pseudos);

        add(playerAcocunt);
        add(playerAcocuntCombo);
    }
}
