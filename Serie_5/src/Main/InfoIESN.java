package Main;

import javax.swing.*;
import java.awt.*;

public class InfoIESN extends JPanel {
    private JLabel label;

    public InfoIESN(){
        setLayout(new FlowLayout());

        label = new JLabel("<html><p>Rue Joseph Calozet 19<p>5000 Namur</p>" +
                "<p>Tél : +32 (0)81 46 86 10</p><p>Fax: +32 (0)81 73 32 17</p>" +
                "<p><a href='info.iesn@henallux.be'>Henallux IESN</a></p></html>");
        this.add(label);
    }
}
