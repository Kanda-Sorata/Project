package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        InfoIESN infoIESN = new InfoIESN();
        Menu menu = new Menu(infoIESN);
        Frame frame = new Frame(menu);
        JOptionPane.showMessageDialog(null, "Bienvenu(e) Agent!\nNous devons reprendre Washington D.C.!\n", "I.S.A.C", JOptionPane.INFORMATION_MESSAGE);

    }
}
