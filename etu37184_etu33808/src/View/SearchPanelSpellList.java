package View;

import javax.swing.*;
import java.awt.*;

public class SearchPanelSpellList extends JPanel {
    private JComboBox pseudoCombo;
    private  JComboBox numberCombo;
    private String [] pseudos;
    private String [] numbers;
    private JLabel pseudo;
    private JLabel number;

    public SearchPanelSpellList(){
        setLayout(new GridLayout(4, 2, 5, 15));

        pseudo = new JLabel("Pseudo");
        pseudo.setHorizontalAlignment(SwingConstants.RIGHT);
        number = new JLabel("Number");
        number.setHorizontalAlignment(SwingConstants.RIGHT);

        //fill table

        pseudoCombo = new JComboBox(pseudos);
        numberCombo = new JComboBox(numbers);

        add(pseudo);
        add(pseudoCombo);
        add(number);
        add(numberCombo);
        setVisible(true);
    }
}
