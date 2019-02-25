package Principale;

import Principale.Student;
import java.io.*;

public class Consommateur extends Thread {
    private ZoneCommune z;

    public Consommateur(ZoneCommune z) {
        this.z = z;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep((int) (Math.random() * 3000));
            } catch (InterruptedException iException) {
                iException.printStackTrace();
            }
            System.out.println(z.getStudent());
        }
    }
}